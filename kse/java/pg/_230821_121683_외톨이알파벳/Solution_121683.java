package java.pg._230821_121683_외톨이알파벳;
//https://school.programmers.co.kr/learn/courses/15008/lessons/121683
//신규_프로그래머스_lv1_121683_외톨이알파벳
import java.util.*;

public class Solution_121683 {
    public String solution(String input_string) {
        StringBuilder answer = new StringBuilder();

        //사용한 철자와 사용 횟수를 저장해주는 Map
        Map<Character, Integer> result = new HashMap<>();
        char[] input_array = input_string.toCharArray();
        char temp = 'N';
        for(char spell : input_array){
            if(temp != spell){ //철자가 바뀔 때마다 저장
                result.put(spell, result.getOrDefault(spell, 0)+1);
                temp = spell;
            }
        }
        //철자 사용횟수가 2 이상인 경우 answer 에 더해 준다.
        for(Map.Entry<Character, Integer> entry: result.entrySet()){
            if(entry.getValue()>1) answer.append(entry.getKey());
        }
        if(answer.length()==0) return "N";
        return answer.toString();
    }
}
