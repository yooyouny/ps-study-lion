package com.example.algorithm.ps;

public class Solution92345 {
    int[][] map;
    int n, m;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        map = board.clone();
        return dfs(aloc[0], aloc[1], bloc[0], bloc[1], 0).move;
    }

    static class Result {  // 결과 저장
        boolean win;
        int move;

        public Result(boolean win, int move){
            this.win = win;
            this.move = move;
        }
    }
    public Result dfs(int ax, int ay, int bx, int by, int cnt){ // a : 해당 턴, b : 다음 턴
        boolean win = false;  // 이기는 케이스가 있는지를 위한 boolean 변수
        int win_cnt = 5 * 5;  // 움직인 횟수의 최댓값
        int lost_cnt = cnt;

        if(map[ax][ay] == 1){
            for (int i = 0; i < 4; i++) {
                int nx = ax + dx[i];
                int ny = ay + dy[i];

                // 이동할 수 없거나 발판이 사라졌을 때
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 0) continue;

                map[ax][ay] = 0;  // a 의 위치에 발판 제거 가정
                Result r = dfs(bx, by, nx, ny, cnt+1);  // dfs 수행 (b의 턴으로 넘겼기 때문에 b 기준에서 결과가 결정됨)
                win |= !r.win;  // 이기는 케이스가 있다면, true
                if (!r.win) win_cnt = Math.min(win_cnt, r.move);  // a가 이겼다면 최소 이동 횟수 갱신
                else lost_cnt = Math.max(lost_cnt, r.move); // a가 졌다면 최대 이동 횟수 갱신
                map[ax][ay] = 1;  // 다시 발판 원위치
            }
        }

        return new Result(win, win ? win_cnt : lost_cnt);
    }

    public static void main(String[] args) {
        System.out.println(new Solution92345().solution(new int[][]{{1,1,1},{1,0,1},{1,1,1}}, new int[]{1, 0}, new int[]{1,2}));
    }
}

