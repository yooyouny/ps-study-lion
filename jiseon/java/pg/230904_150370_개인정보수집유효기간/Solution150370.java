package com.example.algorithm.ps;

import java.util.*;

public class Solution150370 {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answerList = new ArrayList<>();
        int todayValue = getDate(today);   // 현재 날짜를 정수로 변환
        Map<String, Integer> termMap = new HashMap<>();  // 약관 정보 map
        for (String term : terms) {
            String[] termSplit = term.split(" ");
            termMap.put(termSplit[0], Integer.parseInt(termSplit[1]) * 28);
        }


        for (int i = 0; i < privacies.length; i++) { // 유효기간에 따라 파기해야 할 개인 정보 검사
            String[] privacySplit = privacies[i].split(" ");
            if (todayValue >= getDate(privacySplit[0]) + termMap.get(privacySplit[1])) {  // 날짜에 약관을 더한 값이 현재보다 과거라면
                answerList.add(i + 1);
            }
        }
        return answerList.stream().mapToInt(integer -> integer).toArray();
    }

    private int getDate(String today) { // 날짜를 정수로 변환하는 메소드
        String[] date = today.split("\\.");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        return (year * 12 * 28) + (month * 28) + day;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution150370()
                        .solution("2022.05.19",
                                new String[]{"A 6", "B 12", "C 3"},
                                new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"})));
    }
}
