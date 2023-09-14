package com.example.algorithm.ps;

public class Solution150365 {
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static String[] term = {"d", "l", "r", "u"}; // 사전 순으로

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        int diff = getDiff(x, y, r, c);

        // 시작지점과 도착지점까지의 최단 거리가 k 보다 크고,
        // 이동 거리에서 도착지까지의 최단 거리를 뺸 후의 남은 수가 2로 나누어 떨어지지 않으면
        // k 내로 미로 탈출 불가능
        if (diff > k || (k - diff) % 2 != 0) return "impossible";

        while (k > 0) {
            for (int i = 0; i < 4; i++) { // 가장 빠른 단어부터 시도
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx <= 0 || nx > n || ny <= 0 || ny > m) continue; // 미로 범위 내인지
                if (k < getDiff(nx, ny, r, c)) continue; // 현재 가능한 이동 거리보다 작은지

                answer += term[i]; // 현재 단어를 더해주고
                x = nx; y = ny; // 현재 위치 갱신
                --k; // 한 칸 이동했으므로, 이동거리 감소
                break;
            }
        }

        return answer;
    }

    public int getDiff(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static void main(String[] args) {
        System.out.println(new Solution150365().solution(3, 4, 2, 3, 3, 1, 5));
//        System.out.println(new Solution150365().solution(2, 2, 1, 1, 2, 2, 2));
//        System.out.println(new Solution150365().solution(3, 3, 1, 2, 3, 3, 4));
    }
}
