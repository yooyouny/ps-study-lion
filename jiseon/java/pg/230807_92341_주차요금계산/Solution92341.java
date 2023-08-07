package com.example.javaproject3.psstudy;

import java.util.*;

public class Solution92341 {
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> recordMap = new HashMap<>(); // 차량 입/출차 기록
        Map<Integer, Integer> total = new HashMap<>();  // < 차량, 누적 주차 시간 >
        Set<Integer> car = new HashSet<>();

        for(String record : records){
            String[] str = record.split(" ");
            String[] times = str[0].split(":");
            int min = (Integer.parseInt(times[0]) * 60) + Integer.parseInt(times[1]);  // 시각을 분으로 계산
            int num = Integer.parseInt(str[1]);  // 차량 번호

            if (!recordMap.containsKey(num)) {  // 없는 차량 이라면
                car.add(num); // 차량 번호 저장
                recordMap.put(num, min); // 주차 시작 시간 기록
                if(!total.containsKey(num)) total.put(num, 0);  // 누적 주차 시간 map 에 존재하지 않으면, <차량 , 누적 시간 초깃값> 저장
            } else {  // 출차하는 경우
                total.put(num, total.get(num) + min - recordMap.get(num)); // 누적 주차 시간 계산하여 저장
                recordMap.remove(num);  // 출차
            }
        }

        // map 에 아직 남아있는 차량이 있다면, 출차된 내역이 없으므로 23:59 에 출차된 것으로 처리
        for(Integer key : recordMap.keySet())
            total.put(key, total.get(key) + (23 * 60 + 59) - recordMap.get(key));

        List<Integer> list = new ArrayList<>(car);
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            int time = total.get(list.get(i));  // 누적 주차 시간
            // 누적 주차 시간이 기본 시간 이내라면, 기본 요금, 이후라면 요금 표에 따라 계산하여 저장
            answer[i] = (time <= fees[0]) ? fees[1] :
                    (int) (fees[1] + Math.ceil((double) (time - fees[0]) / fees[2]) * fees[3]);
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution92341 s = new Solution92341();
        int[] f1 = {180, 5000, 10, 600};
        String[] r1 = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT",
                "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        System.out.println(Arrays.toString( s.solution(f1, r1)));
    }
}
