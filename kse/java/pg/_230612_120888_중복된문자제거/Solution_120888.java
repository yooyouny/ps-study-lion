package java.pg._230612_120888_중복된문자제거;

import java.util.HashSet;
import java.util.List;

//신규_프로그래머스_lv0_중복된문자제거
public class Solution_120888 {
    public String solution(String my_string) {
        String answer = "";
        for(String spell : my_string.split("")){
            if(!answer.contains(spell)){
                answer+=spell;
            }
        }
        return answer;
    }
}
