package java.pg._230612_42576_완주하지못한선수;

import java.util.*;

//신규_프로그래머스_lv1_완주하지못한선수
public class Solution_42576 {
    public static String solution(String[] participant, String[] completion) {
        String answer = "";

        Map<String, Integer> party = new HashMap<>();

        for (String name : participant) {
            party.put(name, party.getOrDefault(name, 0) + 1);
        }

        for (String name : completion) {
            party.put(name, party.get(name) - 1);
        }

        for (String key : party.keySet()) {
            if (party.get(key) != 0) {
                answer = key;
                break;
            }
        }

        return answer;
    }
}
