package java.pg._231221_17687_n진수게임;

import java.util.ArrayList;
import java.util.List;

//신규_프로그래머스_lv2_17687_n진수게임
//https://school.programmers.co.kr/learn/courses/30/lessons/17687
public class Solution_17687{
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer  = new StringBuilder();

        // n진수 문자열 합 구하기
        StringBuilder numbers = new StringBuilder();
        for(int i = 0; numbers.length() <= t * m; i++){
            numbers.append(Integer.toString(i, n));
        }

        // 필요한 순서 번호의 문자 넣기
        for (int i = 0; i < t; i++){
            answer.append(numbers.charAt(p + (m * i) - 1));
        }


        return answer.toString().toUpperCase();
    }
}
