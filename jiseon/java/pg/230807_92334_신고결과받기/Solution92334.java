package com.example.javaproject3.psstudy;

import java.util.*;

public class Solution92334 {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, HashSet<String>> reportList = new HashMap<>();  // < 신고 당한 사람, 누구한테 당했는지 >
        Map<String, Integer> result = new LinkedHashMap<>(); // < 이용자, 결과 메일 횟수 >
        for (String id : id_list) {  // 초기화
            reportList.put(id, new HashSet<>());
            result.put(id, 0);
        }

        for (String r : report) {
            String[] tmp = r.split(" ");
            reportList.get(tmp[1]).add(tmp[0]);  // 신고한 ID에 대한, 이용자 ID 들을 map 에 저장
        }

        for (String user : reportList.keySet()) {
            HashSet<String> users = reportList.get(user);  // 신고한 이용자 ID 리스트
            if(users.size() >= k){  // 해당 이용자 신고 횟수가 k 보다 많으면
                for(String id : users) result.put(id, result.getOrDefault(id, 0) + 1); // 해당 유저가 받은 결과 메일 횟수 추가
            }
        }

        return result.values().stream().mapToInt(Integer::intValue).toArray();  // value 값들을 배열로 변환하여 리턴
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new Solution92334().solution(new String[]{"muzi", "frodo", "apeach", "neo"},
                new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"},
                2)));
    }
}
