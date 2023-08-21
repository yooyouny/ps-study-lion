package java.pg._230821_121684_체육대회;
//https://school.programmers.co.kr/learn/courses/15008/lessons/121684
//신규_프로그래머스_lv1_121684_체육대회

public class Solution_121684 {
    //완전 탐색으로 계산
    public int solution(int[][] ability) {
        int answer = 0;

        return findMaxSum(ability, new boolean[ability.length], 0,  0);
    }

    public int findMaxSum(int[][] ability, boolean[] isVisit, int turn, int sum){
        // 다 구했으면 return
        if(turn == ability[0].length) return sum;

        // 가장 큰 값을 구해서 return 해준다.
        int max = 0;
        for (int i = 0; i < ability.length; i++) {
            if(!isVisit[i]){
                isVisit[i] = true;
                max = Math.max(findMaxSum(ability, isVisit, turn + 1, sum + ability[i][turn]), max);
                isVisit[i] = false;
            }

        }
        return max;
    }

}
