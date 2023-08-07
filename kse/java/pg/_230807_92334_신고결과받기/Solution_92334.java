package java.pg._230807_92334_신고결과받기;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/92334?language=java
//신규_프로그래머스_Lv1_92334_신고결과받기
public class Solution_92334 {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        //중복 신고 제거 해주기
        String[] uniqueReport = Arrays.stream(report).distinct().toArray(String[]::new);
        //신고 수가 정지 기준 수보다 적은 경우 종료
        if(uniqueReport.length < k) return answer;

        //개인의 신고 기록 저장
        Map<String, Set<String>> reportMap = new HashMap<>();
        //총 신고 호시수 저장
        Map<String, Integer> countMap = new HashMap<>();

        //신고 목록 정리하기
        for (String temp : uniqueReport){
            String[] users = temp.split(" ");
            reportMap.computeIfAbsent(users[0], key -> new HashSet<>()).add(users[1]);
            countMap.put(users[1], countMap.getOrDefault(users[1], 0)+1);
        }

        //정지 회원 리스트 만들어 주기
        //countMap 의 value 가 k와 같거나 많은 경우
        Set<String> freezeList = new HashSet<>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if(entry.getValue() >= k) freezeList.add(entry.getKey());
        }

        //사용자가 신고한 회원 리스트와 정지 리스트를 비교하며
        //answer 개수 세주기
        for (int i = 0; i < id_list.length; i++) {
            for (String reportedUser : reportMap.getOrDefault(id_list[i], new HashSet<>())) {
                if (freezeList.contains(reportedUser)) {
                    answer[i]++;
                }
            }

        }

        return answer;
    }
}
