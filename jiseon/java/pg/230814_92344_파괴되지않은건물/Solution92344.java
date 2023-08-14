package com.example.algorithm.ps;

public class Solution92344 {
    // 신규_프로그래머스_lv3_92344_파괴되지않는건물
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] sum = new int[board.length + 1][board[0].length + 1];

        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1], c1 = s[2];
            int r2 = s[3], c2 = s[4];
            int degree = s[5] * (type == 1 ? -1 : 1);  // type이 1이면 공격, 2면 회복

            // 스킬 마다 시작점, 끝점 갱신
            sum[r1][c1] += degree;
            sum[r2 + 1][c2 + 1] += degree;
            sum[r1][c2 + 1] -= degree;
            sum[r2 + 1][c1] -= degree;
        }

        sum = cal(sum, board.length, board[0].length);  // 누적합

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 초기 상태와 누적합의 합산 결과가 0보다 크면 카운트
                if (board[i][j] + sum[i][j] > 0) answer++;
            }
        }
        return answer;
    }

    public static int[][] cal(int[][] sum, int n, int m) { // 누적합 계산
        for (int c = 1; c < m; c++) {
            for (int r = 0; r < n; r++) {
                sum[r][c] += sum[r][c - 1];
            }
        }
        for (int r = 1; r < n; r++) {
            for (int c = 0; c < m; c++) {
                sum[r][c] += sum[r - 1][c];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution92344().solution(
                new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}},
                new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}})
        );
    }
}
