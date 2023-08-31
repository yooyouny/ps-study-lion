package com.example.algorithm.ps;

import java.util.*;

public class Solution121690 {
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int solution(int n, int m, int[][] hole) {
        int[][][] arr = new int[2][n + 1][m + 1]; // 신발 사용 여부, y좌표, x좌표

        for (int[][] subArr : arr) {
            for (int[] row : subArr) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
        }

        boolean[][] trap = new boolean[n + 1][m + 1];
        for (int[] h : hole) trap[h[0]][h[1]] = true;

        Queue<int[]> q = new LinkedList<>(); // 다음에 이동할 좌표 저장
        q.offer(new int[]{0, 1, 1});
        arr[0][1][1] = 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int use = current[0];
            int y = current[1];
            int x = current[2];

            for (int[] d : dir) {  // 네 방향을 모두 확인
                int ny = y + d[0];
                int nx = x + d[1];
                if (ny >= 1 && ny <= n && nx >= 1 && nx <= m &&  // 범위 안에 있는 좌표이고,
                        !trap[ny][nx] && arr[use][ny][nx] > arr[use][y][x] + 1) { // 함정이 아닌 좌표이며, 아직 지나가지 않은 자리라면
                    arr[use][ny][nx] = arr[use][y][x] + 1;
                    q.offer(new int[]{use, ny, nx});
                }

                // 신발을 사용할 경우
                int nny = ny + d[0]; // 현재 위치의 다다음 좌표
                int nnx = nx + d[1];
                if (use == 0 && nny >= 1 && nny <= n && nnx >= 1 && nnx <= m  // 신발을 사용하지 않았다면
                        && !trap[nny][nnx] && arr[1][nny][nnx] > arr[0][y][x] + 1) {
                    arr[1][nny][nnx] = arr[0][y][x] + 1;
                    q.offer(new int[]{1, nny, nnx});
                }
            }
        }
        return (arr[1][n][m] != Integer.MAX_VALUE) ? arr[1][n][m] : -1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution121690().solution(4, 4, new int[][]{{2, 3}, {3, 3}}));
    }
}
