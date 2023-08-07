import java.text.SimpleDateFormat;
import java.util.*;
class Solution {
    static Map<String, Integer> parseMap;
    public int[] solution(int[] fees, String[] records) throws Exception{
        // 출입시간을 기록하기 위한 map
        Map<String, String> map = new HashMap<>();
		
        // 차량 번호별 총 주차시간을 계산하기 위한 map
        parseMap = new TreeMap<>();

        for (int i = 0; i < records.length; i++) {
            String[] tempRecords = records[i].split(" ");
            // 입차라면
            if (tempRecords[2].equals("IN")) {
                map.put(tempRecords[1], tempRecords[0]);
                // 출차라면 입차시간, 출차시간, 차량번호를 parse에 넘김
            } else { //이전에 넣어둔 입차 시간을 가져옴, 현재 입차시간을 넘겨서 차이를 계산
                parse(map.get(tempRecords[1]), tempRecords[0], tempRecords[1]);
                map.remove(tempRecords[1]);
            }
        }

        // map에 남아있는 차량이 있다면
        if (!map.isEmpty()) {
            for (String s : map.keySet()) {
                //23:59초에 출차한 차량으로 친다.
                parse(map.get(s), "23:59", s);
            }
        }

        // 계산 메서드 호출
        cul(fees);
        
        // map에 value를 담아오기 위한 list
        List<Integer> list = new ArrayList<>();
        for (String key : parseMap.keySet()) {
            list.add(parseMap.get(key));
        }
        
        // list를 array로 변환
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
    
    // 주차 시간을 변환하기 위한 메서드
    static void parse(String in, String out, String carNum) throws Exception {
        // 시간 형식 지정을 위한 simpleDateFormat
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        // 출차 시간
        Date outTime = simpleDateFormat.parse(out);
        // 입차 시간
        Date inTime = simpleDateFormat.parse(in);
        // 시간 차이를 구하고 분으로 변환
        int dif = (int) ((outTime.getTime() - inTime.getTime()) / 60000);
        // parseMap에 업데이트
        parseMap.put(carNum, parseMap.getOrDefault(carNum, 0) + dif);
    }
    
    // 요금 계산을 위한 메서드
    static void cul(int[] fees) {
        for (String key : parseMap.keySet()) {
            //기본 요금 미만일 경우
            if (parseMap.get(key) < fees[0]) {
                //기본 요금
                parseMap.put(key, fees[1]);
                //기본 요금 이상인 경우
            } else {
                double addTime = parseMap.get(key) - fees[0];
                //단위 시간으로 올림
                addTime = Math.ceil(addTime / fees[2]);
                //총 금액
                int result = (int) (addTime * fees[3] + fees[1]);
                parseMap.put(key, result);
            }
        }
    }
}
