package java.pg._230831_121690_보물지도;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/15009/lessons/121690
//신규_프로그래머스_lv3_121688_보물지도
public class Solution_121690 {
    /**맨 왼쪽 아래 칸의 좌표를 (1, 1)으로, 맨 오른쪽 위 칸의 좌표를 (n, m)
     * [상,하,좌,우]로 인접한 네 칸 중 한 칸으로 걸어서 이동
     * 신발을 신고 뛰면 한 번에 두 칸을 이동할 수 있으며, 함정이 있는 칸도 넘을 수 있다.
     * 신발은 한 번만 사용 가능
     * @param n     가로 길이를 나타내는 정수
     * @param m     세로 길이를 나타내는 정수
     * @param hole  함정의 위치를 나타내는 2차원 정수 배열
     * @return 출발점에서 보물이 위치한 칸으로 이동하는데 필요한 최소 시간
     */

    int rowSize;
    int colSize;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int n, int m, int[][] hole) {
        int[][] board = new int[m][n];
        rowSize = n;
        colSize = m;

        for (int[] temp : hole)
            board[colSize - temp[1]][temp[0] - 1] = Integer.MAX_VALUE;

        return bfs(board);
    }

    public int bfs(int[][] board) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, colSize - 1, 0, 1}); // x, y, count, usedShoes

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int noxX = current[0];
            int nowY = current[1];
            int count = current[2];
            int usedShoes = current[3];

            // 도착 종료
            if (noxX == rowSize - 1 && nowY == 0){
                return count;
            }
            if (board[nowY][noxX] > count -1) continue;
            board[nowY][noxX] = count;

            for (int i = 0; i < 4; i++) {
                int nextX = noxX + dx[i];
                int nextY = nowY + dy[i];

                // 이동하려는 다음 좌표가 이동 가능한 좌표인지 확인
                if (nextX >= 0 && nextX < rowSize && nextY >= 0 && nextY < colSize) {
                    //다음 좌표가 갈 수 있는 좌표인지 확인
                    if (board[nextY][nextX] == 0) {
                        queue.offer(new int[]{nextX, nextY, count + 1, usedShoes});
                    }
                    // 다음 좌표가 함정이고 함정을 건너뛸 수 있는지 확인
                    if (usedShoes == 1){
                        int jumpX = nextX + dx[i];
                        int jumpY = nextY + dy[i];

                        if (jumpX >= 0 && jumpX < rowSize && jumpY >= 0 && jumpY < colSize && board[nextY][nextX] == 0) {
                            queue.offer(new int[]{jumpX, jumpY, count + 1, 0});
                        }
                    }
                }
            }
        }

        return -1;
    }
}

/**
 * // 왼쪽 이동 가능
 *             if(nowX > 0){
 *                 // 이동 가능
 *                 if(board[nowY][nowX-1] == 0){
 *                     move(nowX-1, nowY, count+1, usedShoes, boardCopy);
 *                 }
 *                 if(usedShoes && nowX > 1 && board[nowY][nowX-1] == 2 && board[nowY][nowX-2] == 0){
 *                     move(nowX-2, nowY, count+1, false, boardCopy);
 *                 }
 *             }
 *             if(nowX < rowSize - 1){
 *                 // 이동 가능
 *                 if(board[nowY][nowX+1] == 0){
 *                     move(nowX+1, nowY, count+1, usedShoes, boardCopy);
 *                 }
 *                 if(usedShoes && nowX < rowSize - 2 && board[nowY][nowX+1] == 2 && board[nowY][nowX+2] == 0){
 *                     move(nowX+2, nowY, count+1, false, boardCopy);
 *                 }
 *             }
 *             if(nowY > 0){
 *                 // 이동 가능
 *                 if(board[nowY-1][nowX] == 0){
 *                     move(nowX, nowY-1, count+1, usedShoes, boardCopy);
 *                 }
 *                 if(usedShoes && nowY > 1 && board[nowY-1][nowX] == 2 && board[nowY-2][nowX] == 0){
 *                     move(nowX, nowY-2, count+1, false, boardCopy);
 *                 }
 *             }
 *             if(nowY < colSize - 1){
 *                 // 이동 가능
 *                 if(board[nowY+1][nowX] == 0){
 *                     move(nowX, nowY+1, count+1, usedShoes, boardCopy);
 *                 }
 *                 if(usedShoes && nowY < colSize - 2 && board[nowY+1][nowX] == 2 && board[nowY+2][nowX] == 0){
 *                     move(nowX, nowY+2, count+1, false, boardCopy);
 *                 }
 *             }
 */