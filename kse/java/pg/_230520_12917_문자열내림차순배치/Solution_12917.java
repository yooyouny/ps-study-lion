package java.pg._230520_12917_문자열내림차순배치;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
//신규_프로그래머스_1level_12917_문자열내림차순배치
//https://school.programmers.co.kr/learn/courses/30/lessons/12917
public class Solution_12917 {

    /**
     * 1. 문자열을 splits 배열로 쪼개준다.
     * 2. 거꾸로 정렬 후  .sorted(Comparator.reverseOrder())
     * 3. 합쳐준다.      .collect(Collectors.joining());
     * @param s 내림차순 정렬할 문자열
     * @return  answer
     */
    public String solution(String s) {
        String answer = "";
        String[] splits = s.split("");
        answer = Arrays.stream(splits)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining());
        return answer;
    }
}
