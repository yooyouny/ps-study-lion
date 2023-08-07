package java.pg._230807_92341_주차요금계산;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//https://school.programmers.co.kr/learn/courses/30/lessons/92341
//신규_프로그래머스_Lv2_92341_주차요금계산
public class Solution_92341 {

    int freeTime, defaultFee, unitTime, unitFee;
    public int[] solution(int[] fees, String[] records) {
        freeTime = fees[0];     // 기본 제공 시간
        defaultFee = fees[1];   // 기본 요금
        unitTime = fees[2];     // 단위 시간
        unitFee = fees[3];      // 단위 요금

        //주차장 입장 시간 Map
        Map<String, Integer> parkTime = new HashMap<>();
        //총 주차 시간 Map
        Map<String, Integer> timeSum = new TreeMap<>();

        for(String recode : records){
            String[] temp = recode.split(" ");
            if(temp[2].equals("IN")){   // 입장
                parkTime.put(temp[1], toMinutes(temp[0]));
            }
            else{   // 퇴장
                // 퇴장시간 - 입장시간으로 총 머무른 시간을 구해 넣어준다.
                int minutes = toMinutes(temp[0]) - parkTime.get(temp[1]);
                timeSum.put(temp[1], timeSum.getOrDefault(temp[1], 0)+minutes);
                // 나갈 때 Map에서도 삭제
                parkTime.remove(temp[1]);
            }
        }
        // 23:59까지 남아있는 경우 23:59을 퇴장시간으로 하여 계산해준다.
        if(!parkTime.isEmpty()){
            int outTime = toMinutes("23:59");
            //Map 에 남아있는 차량 계산
            for (Map.Entry<String, Integer> entry : parkTime.entrySet()) {
                int minutes = outTime - entry.getValue();
                timeSum.put(entry.getKey(), timeSum.getOrDefault(entry.getKey(), 0)+minutes);
            }
        }

        // 금액 계산
        for (Map.Entry<String, Integer> entry : timeSum.entrySet()) {
            timeSum.put(entry.getKey(), calcFee(timeSum.get(entry.getKey())));
        }

        // Map 을 Array 로 변환
        return timeSum.values().stream().mapToInt(Integer::intValue).toArray();
    }

    public int calcFee(int minutes){
        minutes -= freeTime;     // 이용시간에서 기본시간을 빼고
        if(minutes >= 0){        // 남은 시간을 단위 시간으로 나눠 비용으로 좁하여 추가 금액을 계산한다.
            return (int) (defaultFee + Math.ceil((double) minutes / unitTime) * unitFee);
        }
        return defaultFee;
    }
    public int toMinutes(String time){
        String[] inTime = time.split(":");
        return Integer.parseInt(inTime[0])*60 + Integer.parseInt(inTime[1]);
    }
}
