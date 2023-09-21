package java.pg._230920_42888_오픈채팅방;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/42888
//신규_프로그래머스_lv2_42888_오픈채팅방
public class Solution_42888 {
    /**
     * [모든 기록이 처리된 후], 최종적으로 방을 개설한 사람이 보게 되는 메시지를 문자열 배열 형태로 return
     * @param record 사용자 기록
     */
    public String[] solution(String[] record) {
        List<String> answer = new ArrayList<>();    // 정답 메세지
        Map<String, String> users = new HashMap<>();// 사용자 정보
        //기록 처리
        for (String temp : record){
            String[] arr = temp.split(" ");
            String id   = arr[1];
            switch (arr[0].charAt(0)){      //사용자 정보가 기록되는 것은 Enter 와 Change 뿐이다.
                case 'E', 'C' -> {
                    String nick = arr[2];
                    users.put(id, nick);
                }
                default -> {}
            }
        }
        //메세지 저장
        for (String s : record) {
            String[] arr = s.split(" ");
            switch (arr[0].charAt(0)){
                case 'E' -> answer.add(users.get(arr[1]) + "님이 들어왔습니다.");
                case 'L' -> answer.add(users.get(arr[1]) + "님이 나갔습니다.");
                default -> {}
            }
        }
        return answer.toArray(new String[0]);
    }
}
