package com.example.javaproject3.psstudy;

public class Solution92335 {
    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        while (n != 0){ // k 진법 변환
            sb.append(n % k);
            n /= k;
        }
        sb = sb.reverse();

        String[] arr = sb.toString().split("0");  // 0 을 기준으로 split
        for(String s : arr){
            if(s.equals("")) continue;
            if(isPrime(Long.parseLong(s))) answer++;
        }
        return answer;
    }

    public boolean isPrime(long num){  // 소수 구하는 메소드
        if(num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}
