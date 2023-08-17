package java.pg._230816_92345_사라지는발판;

import java.util.LinkedList;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/92345
//신규_프로그래머스_lv3_92345_사라지는발판
public class Solution_92345 {
    /**
     * 이 게임이 끝날 때까지 양 플레이어가 캐릭터를 몇 번 움직이게 될지 예측하는 문제
     * 1. 움직일 차례인데 캐릭터의 상하좌우 주변 4칸이 모두 발판이 없거나 보드 밖이라서 이동할 수 없는 경우, 해당 차례 플레이어는 패배
     * 2. 두 캐릭터가 같은 발판 위에 있을 때, 상대 플레이어의 캐릭터가 다른 발판으로 이동하여 자신의 캐릭터가 서있던 발판이 사라지게 되면 패배
     * -------------------------------------------------------------------
     * 플레이어 A는 이길 수 있는 플레이어이며, B는 질 수밖에 없는 플레이어이다.
     * 위 예시에서 양 플레이어가 최적의 플레이를 해야 한다.
     * -> A는 가장 적게 움직일 수 있는 방향으로
     * -> B는 가장 많이 움직일 수 있는 방향으로 가야 한다.
     */
    public int[] dx = {0,0,1,-1};
    public int[] dy = {1,-1,0,0};   //

    // 위 아래 오 왼
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        // 항상 플레이어 A가 먼저 시작 true : A turn || false : B turn
        return Math.abs(game( board, aloc, bloc, true, 0));
    }

    public int game(int[][] board, int[] aloc, int[] bloc, boolean isATurn, int count){
        int aX = aloc[1];
        int aY = aloc[0];
        int bX = bloc[1];
        int bY = bloc[0];

        // 발판이 없는 경우
        if ((board[aY][aX] == 0 && isATurn) || (board[bY][bX] == 0 && !isATurn)) return -count;

        int X, Y;   // TURN 에 맞게 X, Y 좌표 구해주기
        if (isATurn) { Y = aY; X = aX; }
        else { Y = bY; X = bX; }

        int win  = Integer.MAX_VALUE;   //가장 적게 움직일 때의 COUNT
        int lose = 0;                   //가장 많이 움직일 때의 COUNT
        int res;

        //현재 위치 Y,X 에서 이동할 수 있는지 확인
        boolean canGo = false;

        board[Y][X] = 0;
        //최선의 경우 리턴
        for (int i = 0; i < 4; i++) {
            int nowX = X + dx[i];
            int nowY = Y + dy[i];
            //갈 수 없는 좌표 거나 발판이 없는(board[nowY][nowX] == 0) 경우
            if (nowX < 0 || nowY < 0 || nowX >= board[0].length || nowY >= board.length || board[nowY][nowX] == 0) continue;
            canGo = true;
            if (isATurn) {
                res = game(board, new int[]{nowY, nowX}, bloc, !isATurn, count + 1);
            } else {
                res = game(board, aloc, new int[]{nowY, nowX}, !isATurn, count + 1);
            }

            // 다음 순번이 이길 경우 현재 순번이 패배 -> 최대의 움직임
            if (res>=0) lose = Math.max(lose, Math.abs(res));
            // 다음 순번이 질 경우 현재 순번이 승리 -> 최소의 움직임
            else win = Math.min(win, Math.abs(res));
        }
        board[Y][X] = 1;

        // 움직지이 못하는 경우
        if (!canGo) return -count;
        // 현재 순번이 이기는 경우
        else if (win != Integer.MAX_VALUE) return win;
        // 현재 순번이 지는 경우
        else return -lose;
    }
}
