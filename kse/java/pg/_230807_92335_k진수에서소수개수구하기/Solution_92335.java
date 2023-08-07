package java.pg._230807_92335_k진수에서소수개수구하기;

import java.util.*;
import java.util.stream.Collectors;

//https://school.programmers.co.kr/learn/courses/30/lessons/92335
//신규_프로그래머스_Lv2_92335_k진수에서소수개수구하기
public class Solution_92335 {
    public int solution(int n, int k) {
        int answer = 0;
        String nString = Long.toString(n, k);

        List<String> numbers = Arrays.stream(nString.split("0"))  // "0"을 기준으로 분해
                .map(String::trim)                                      // 문자열 앞뒤 공백 제거
                .filter(s -> !s.isEmpty())                              // 빈 값이 아닌 경우만 필터링
                .collect(Collectors.toList());

        for(String number : numbers){
            if(isPrime(Long.parseLong(number))) answer++;
        }
        return answer;
    }


    //소수 구하는 메서드
    public boolean isPrime(Long number){
        if(number<2) return false;
        // 2 ~ number 의 제곱근 까지 계산
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if(number%i==0) return false;
        }
        return true;
    }
}
