public class Solution {

      static class Result{
        int step;
        boolean winOrLose;

        public Result(int step, boolean winOrLose) {
            this.step = step;
            this.winOrLose = winOrLose;
        }
    }

    static final int EMPTY = 0;
    static final int AVAILABLE = 1;
    //상하 좌우 이동
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1 , 1};
    int R;
    int C;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int ar = aloc[0];
        int ac = aloc[1];
        int br = bloc[0];
        int bc = bloc[1];
        R = board.length;
        C = board[0].length;
        //TODO: 최적해의 이동 횟수를 구하는 함수
        Result finalResult = play(board, ar, ac, br, bc);
        return finalResult.step;
    }
    //TODO: A와B말을 움직여서 최적해를 구하는 함수
    // 이기는 경우, count를 최솟값으로 업데이트, 지는경우, count를 최댓값으로 업데이트 해야하기 때문에 boolean을 추가적으로 return해야한다.
    // 이기는 값: true
    // 현재 플레이어 좌표 : r1, c1
    // 상대 플레이어 좌표 : r2, c2
    // 현재 플레이어 기준 함수

    public Result play(int[][] board, int r1, int c1, int r2, int c2){
        //TODO: 패배인지 아닌지 확인하는 조건
        if(board[r1][c1] == EMPTY){
            return new Result(0, false);
        }
        //TODO: 이동하고 현재 위치의 발판을 없애는 로직
        board[r1][c1] = EMPTY;
        //TODO: 이겼을때와 졌을때 업데이트 하는 방식이 다르다.
        //이긴 경우 이동횟수
        int minWin = Integer.MAX_VALUE;
        //진 경우 이동 횟수 -> 여기서 1씩 증가시켜줘야 하기 때문에 MIN_VALUE가 아닌 0으로 초기화
        int maxLose = 0;
        //TODO : 상하좌우에 이동가능한 공간을 확인하는 로직
        for (int i = 0; i < 4; i++) {
            int nr = r1 + dr[i];
            int nc = c1 + dc[i];
            //  - 보드의 경계를 넘어선 안된다.
            // - 이동할 보드에 발판이 있어야한다.
            if(!isOverBoundary(nr, nc) && board[nr][nc] == AVAILABLE){
                // TODO : 턴을 넘긴다.
                //  상대 플레이어 기준 함수 : 상대가 이기면 현재 플레이어는 지게 된다. -> 가능한 각 방향에 대해 게임을 전개한 결과가 반환된다.
                Result opponentResult = play(board, r2, c2, nr, nc);
                //  만약 반환값이 true면 상대 플레이어가 이겼고 나는 졌다는 뜻이므로 max step을 반환해주어야 한다.
                if(opponentResult.winOrLose){
                    maxLose = Math.max(maxLose, opponentResult.step + 1);
                }else {
                    //만약 반환값이 false면 상대 플레이어는 지고 내가 이겼다는 뜻, min step을 반환해주어야한다.
                    minWin = Math.min(minWin, opponentResult.step + 1);
                }
            }
        }
        //TODO: 모든 경우를 체크하고 나서 현재 위치를 다른 타일로 이동하기 전으로 원상복구 해주어야한다.
        board[r1][c1] = AVAILABLE;
        //TODO : 상하좌우중에 이긴 경우가 있다면 이겨야하니까  minWin이 업데이트 되었는지 체크해서 이길수 있는 경우가 있는지보고
        if(minWin != Integer.MAX_VALUE){
            // 이겼다면, True와 minWin을 return
            return new Result(minWin, true);
        }else {
            // 졌다면 false와 maxLose 리턴
            return new Result(maxLose, false);
        }
    }

    private boolean isOverBoundary(int nr, int nc){
        if((nr < 0 || nc < 0) || (nr >= R || nc >= C)){
            return true;
        }
        return false;
    }
}
