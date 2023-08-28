package com.example.algorithm.ps;

import java.util.Arrays;

public class Solution121687 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[] point;
    static int D;  // 0 1 2 3

    public int[] solution(String command) {
        point = new int[]{0, 0}; D = 0;  // 초기 위치와 방향
        command.chars().forEach(s -> {
            switch (s) {
                case 'R' -> D = (D + 1) % 4;  // 오른쪽으로 회전
                case 'L' -> D = (D + 3) % 4;  // 왼쪽으로 회전
                case 'G' -> {  // 현재 방향에 따라 전진
                    point[0] += dx[D];
                    point[1] += dy[D];
                }
                case 'B' -> {  // 현재 방향에 따라 후진
                    point[0] -= dx[D];
                    point[1] -= dy[D];
                }
            }
        });
        return point;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution121687().solution("GRGRGRB")));
    }
}
