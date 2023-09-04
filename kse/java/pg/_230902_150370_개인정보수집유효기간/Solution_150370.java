package java.pg._230902_150370_개인정보수집유효기간;

import java.util.*;
//https://school.programmers.co.kr/learn/courses/30/lessons/150370
//신규_프로그래머스_lv1_150370_개인정보수집유효기간
public class Solution_150370 {
    /**
     * 모든 달은 28일이다.
     * @param today     오늘 날짜 ex ) 2021.05.02
     * @param terms     약관 별 유효 기간 개월 수 기준 -> ["A 6", "B 12", "C 3"]
     * @param privacies 개인정보의 약관 및 수집일 -> ["2022.02.20 C", ...]
     * @return 파기해야 할 개인정보의 index 번호 배열
     */
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 결과값을 저장한 list
        List<Integer> result = new ArrayList<>();

        // 각 약관 별 만료 날짜를 저장하는 맵
        Map<String, Integer> expiredDate = new HashMap<>();

        // 현재 날짜를 일 수로 계산한 변수
        int todayDays = dayCalc(Arrays.stream(today.split("\\.")).mapToInt(Integer::parseInt).toArray());

        // expiredDate 에 약관별 현재 날짜 기준으로 만료에 해당하는 개인정보 수집일을 저장한다.
        String[][] terms_split = Arrays.stream(terms).map(s -> s.split(" ")).toArray(String[][]::new);
        for(String[] term : terms_split){
            expiredDate.put(term[0], todayDays - Integer.parseInt(term[1]) * 28 );
        }

        // 개인정보를 약관과 날짜로 분해한다.
        String[][] privacy_split = Arrays.stream(privacies).map(s -> s.split(" ")).toArray(String[][]::new);

        for (int i = 0; i < privacy_split.length; i++) {
            int[] date = Arrays.stream(privacy_split[i][0].split("\\.")).mapToInt(Integer::parseInt).toArray();
            String temp_term = privacy_split[i][1];
            if(expiredDate.get(privacy_split[i][1]) >= dayCalc(date)){  // expiredDate 에서 해당 약관의 만료 수집일을 찾아 비교한다.
                result.add(i+1);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    //일수 계산하는 메서드
    public int dayCalc(int[] period){
        int year  = (period[0] - 2000) * 12 * 28;
        int month = period[1] * 28;
        int day   = period[2];
        return year + month + day;
    }
}
