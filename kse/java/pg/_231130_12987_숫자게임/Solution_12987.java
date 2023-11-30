package java.pg._231130_12987_숫자게임;

import java.security.Key;
import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/12987?language=java
//신규_프로그래머스_lv3_12987_숫자게임
public class Solution_12987 {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        // B의 최댓값보다 A의 최솟값이 더 큰 경우 이길 수 있는 경우가 존재하지 않기 때문에
        // return 0 해준다.
        if(!BCanGetPoint(A,B)) return 0;

        // 순서에 상관없이 각 팀의 패의 수의 차이가 적으면서 이길 수 있는 경우의 수를 고르면 되기 때문에
        // 각 팀의 패를 오름차순 정렬하여 작은 것부터 계산 할 수 있도록 한다.
        Queue<Integer> AQueue = new PriorityQueue<>();
        for (int ANum : A) AQueue.add(ANum);
        Queue<Integer> BQueue = new PriorityQueue<>();
        for (int BNum : B) BQueue.add(BNum);

        while (!AQueue.isEmpty() && !BQueue.isEmpty()){
            int ANum = AQueue.poll();       // AQueue 에서 가장 작은 값과 BQueue 에서 가장 작은 값을 계속 비교해준다.
            while (!BQueue.isEmpty()){
                if(ANum < BQueue.poll()){   // BQueue 의 값이 더 큰 경우나 나올 때까지 BQueue 에서 값을 제거해준다.
                    answer++;               // BQueue 의 값이 더 크면 answer + 1 해준다.
                    break;
                }
            }
        }

        return answer;
    }

    boolean BCanGetPoint(int[] A, int[] B){
        int AMIN = Arrays.stream(A).min().orElse(Integer.MAX_VALUE);
        int BMAX = Arrays.stream(B).max().orElse(Integer.MIN_VALUE);
        return AMIN <= BMAX;
    }
}
