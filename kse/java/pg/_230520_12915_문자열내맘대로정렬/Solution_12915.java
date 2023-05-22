package java.pg._230520_12915_문자열내맘대로정렬;

import java.util.Arrays;
import java.util.Comparator;
//신규_프로그래머스_1level_12915_문자열내맘대로정렬
//https://school.programmers.co.kr/learn/courses/30/lessons/12915
public class Solution_12915 {
    /**
     * 1. 배열 전체 정렬
     * 2. 배열 n번째 자릿수 기준으로 정렬
     * @param strings   정렬할 문자열
     * @param n         기준으로 할 자릿수
     * @return answer
     */
    public String[] solution(String[] strings, int n) {
        String[] answer = {};
        Arrays.sort(strings);
        Arrays.sort(strings, Comparator.comparing(s -> s.charAt(n)));
        answer = strings;
        return strings;
    }
}
