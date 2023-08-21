package com.example.algorithm.ps;

public class Solution121684 {
    static int max = 0;  // 능력치 최댓값
    static boolean[] visit;

    public int solution(int[][] ability) {  // ability.length : 학생 수, ability[0].length : 종목 수
        visit = new boolean[ability.length];
        permutation(0, ability, 0);
        return max;

    }

    public static void permutation(int depth, int[][] ability, int sum) {
        if (depth == ability[0].length) {   // 종목 수만큼 골랐다면
            max = max > sum ? max : sum;  // 반의 능력치가 최대가 되도록 갱신
            return;
        }

        for (int i = 0; i < ability.length; i++) {
            if (visit[i] == false) {  // 해당 학생을 추가하지 않았다면
                visit[i] = true; // 해당 학생을 추가
                permutation(depth + 1, ability, sum + ability[i][depth]); // 해당 학생을 뽑아서 재귀 호출
                visit[i] = false;  // 해당 학생을 다시 false 처리

            }
        }
    }
}
