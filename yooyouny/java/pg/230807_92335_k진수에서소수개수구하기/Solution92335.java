import java.util.*;

class Solution {

    public boolean isPrime(long n){
        if(n<=1) return false;
        for(int i=2; i<= Math.sqrt(n); i++)
            if(n % i == 0) // 2이상 숫자에서 나눠떨어지는 경우가 있으면 소수가 아님
                return false;
        return true;
    }

    public int solution(int n, int k) {
        //n이 백만까지 들어오므로 k진수로 변환시 21자리 이상이므로 long으로 parse
        String[] splitByZero = Long.toString(n, k).split("0"); // 숫자를 k진수로 바꾸고 0을 기준으로 split

        return (int) Arrays.stream(splitByZero)
                .filter(s -> !s.isEmpty())// 0이 연속되는경우 공백이 들어갈 수 있으므로 제외
                .filter(s -> isPrime(Long.parseLong(s)))// 해당 숫자가 소수인 경우만 필터링
                .count();// 해당 경우의 개수 저장
    }
}