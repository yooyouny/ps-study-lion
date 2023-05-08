package java.pg._230503_120852_소인수분해;

import java.util.LinkedHashSet;
import java.util.Set;

//https://school.programmers.co.kr/learn/courses/30/lessons/120852
public class Solution_120852 {
    public boolean isPrime(int numb){
        for(int i=2 ; i<=numb/2 ; i++){
            if(numb%2==0)
                return false;
        }
        return true;
    }

    public int[] solution(int n) {
        int[] answer = {};
        int numb = 2;
        Set<Integer> anslist = new LinkedHashSet<>();

        while(n>1){
            //numb가 소수인 경우
            if(isPrime(numb)){
                boolean cnt = false;
                //소수로 n을 나눴을 때의 나머지가 0이면 소인수
                while(n%numb==0){
                    n/=numb;
                    cnt = true;
                }
                if(cnt) anslist.add(numb);
            }
            numb++;
        }
        answer = anslist.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
