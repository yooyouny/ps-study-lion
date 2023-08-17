class Solution92345 {
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static class Result{
        boolean win;// 승부여부에 따라 cnt 저장 값이 달라지므로
        int cnt;// 이동횟수
        Result(boolean win, int cnt){
            this.win = win;
            this.cnt = cnt;
        }
    }
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        Result result = dfs(board, aloc, bloc);// 현재보드, 현재 플레이어의 좌표, 상대 플레이어의 좌표
        return result.cnt;
    }
    private Result dfs(int[][] board, int[] aloc, int[] bloc){
        //현재플레이어 기준으로 이동, 항상 a플레이어 먼저 시작
        int r1 = aloc[0];
        int c1 = aloc[1];

        if(board[r1][c1] == 0)// 종료조건1 발판이 없어서 이동할 수 없어서 지는 경우
            return new Result(false, 0); // 결과는 false, 이동횟수는 0으로 리턴


        // 발판이 있어서 이동할 수 있는 경우
        board[r1][c1] = 0;// 해당 위치를 밟고 이동하면 발판이 사라짐

        int minStepsToWin = Integer.MAX_VALUE;// 이겼을때의 cnt 저장, 이동을 최소화해야하므로 최솟값을 구함
        int maxStepsToLose = 0;// 졌을때의 cnt 저장, 최대한 오래 이동해서 살아남아야 하므로 최댓값을 구함

        // 현재 위치에서 상하좌우를 탐색
        for(int i=0; i<4; i++){
            int nr = r1 + dr[i];
            int nc = c1 + dc[i];
            if(nr<0 || nc<0 || nr>= board.length || nc>=board[0].length) continue;
            if(board[nr][nc] == 0) continue; // 종료조건2 보드밖이거나 발판이 없으면 이동 불가

            Result result = dfs(board, bloc, new int[]{nr, nc});// b플레이어가 탐색한 결과
            if(result.win){// 상대방이 이겼으면 현재 플레이어는 짐
                maxStepsToLose = Math.max(maxStepsToLose, result.cnt + 1);// 다음 플레이어가 이동한 횟수에서 현재 플레이어가 이동한 횟수를 더함
            }else{// 현재 플레이어가 이기는 경우
                minStepsToWin = Math.min(minStepsToWin, result.cnt + 1);
            }
        }

        board[r1][c1] = 1;// 가능한 4방향의 모든 경우 탐색이 끝나면 다시 원상복귀시켜줌

        //탐색 결과 이길 수 있는 경우가 하나라도 있는지 확인
        if(minStepsToWin < Integer.MAX_VALUE){
            return new Result(true, minStepsToWin);// 승부결과와 이동횟수를 리턴
        }else{
            return new Result(false, maxStepsToLose);
        }
    }
}