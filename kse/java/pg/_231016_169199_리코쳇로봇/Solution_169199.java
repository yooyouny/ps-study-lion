package java.pg._231016_169199_리코쳇로봇;

import java.util.LinkedList;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/169199
//신규_프로그래머스_lv2_169199_리코쳇로봇
public class Solution_169199 {

    int startY = 0, startX = 0; //시작 좌표
    int endY   = 0, endX   = 0; //골인 좌표
    int[] directY = new int[]{1, -1, 0, 0}; //좌표 이동 시의 변화값 저장
    int[] directX = new int[]{0, 0, -1, 1};
    int ROW, COL;   //가로 세로 길이
    public int solution(String[] board) {
        ROW = board.length;
        COL = board[0].length();

        return bfs(initBoard(board));
    }

    public int bfs(int[][] board){
        //bfs 를 위한 queue
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX, 0});

        //방문했던 좌표인지 확인하기 위한 배열
        boolean[][] visited = new boolean[ROW][COL];
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            int[] nowPoint = queue.poll();
            int dy = nowPoint[0];
            int dx = nowPoint[1];
            int depth = nowPoint[2];

            // 골인 지점에 도착한 경우 답을 return
            if (dy == endY && dx == endX) return depth;

            for (int i = 0; i < 4; i++) {
                int nowY = dy + directY[i];
                int nowX = dx + directX[i];

                // 벽이나 장애물에 닿을 때까지 좌표값을 이동
                while(isInRange(nowY, nowX) && board[nowY][nowX] != 1){
                    nowY += directY[i];
                    nowX += directX[i];
                }
                // while 문에서 나오기 직전 상태로 돌려주기
                nowY -= directY[i];
                nowX -= directX[i];

                // 방문을 하거나 같은 위치일 경우 스킵
                if (visited[nowY][nowX] || (nowY == dy && nowX == dx)) continue;

                // 이 좌표에 방문했음을 체크
                visited[nowY][nowX] = true;

                // queue 에 넣어준다.
                queue.add(new int[]{nowY, nowX, depth + 1});
            }
        }

        return -1;
    }

    public boolean isInRange(int nowY, int nowX){
        return nowY >= 0 && nowY < ROW && nowX >= 0 && nowX < COL;
    }


    public int[][] initBoard(String[] board){
        int[][] newBoard = new int[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            char[] spells = board[i].toCharArray();
            for (int j = 0; j < COL; j++) {
                switch (spells[j]){
                    case '.' -> newBoard[i][j] = 0;
                    case 'D' -> newBoard[i][j] = 1;
                    case 'R' -> {
                        startY = i; startX = j;
                        newBoard[i][j] = 0;
                    }
                    case 'G' -> {
                        endY = i; endX = j;
                        newBoard[i][j] = 0;
                    }
                }
            }
        }
        return newBoard;
    }
}
