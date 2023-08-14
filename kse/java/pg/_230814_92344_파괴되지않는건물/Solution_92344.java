package java.pg._230814_92344_파괴되지않는건물;

import java.util.Arrays;
//https://school.programmers.co.kr/questions/25471
//신규_프로그래머스_lv3_92344_파괴되지않는건물
//해설 : https://tech.kakao.com/2022/01/14/2022-kakao-recruitment-round-1/
public class Solution_92344 {
    /**
     * skill [type, r1, c1, r2, c2, degree]
     * type 1 적의 공격 | type 2 회복 스킬
     * degree : 변동 수치
     */
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        for(int[] turn : skill){
            int y1 = turn[1];
            int y2 = turn[3];
            int x1 = turn[2];
            int x2 = turn[4];
            int degree = (turn[0] == 1) ? -turn[5] : turn[5];
            for (int dy = y1; dy <= y2; dy++) {
                for (int dx = x1; dx <= x2; dx++) {
                    board[dy][dx] +=degree;
                }
            }
            //printArray(board);
        }

        return countBroken(board);
    }

    public int countBroken(int[][] board){
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            count += Arrays.stream(board[i]).filter(val -> val > 0).count();
        }
        return count;
    }

    public void printArray(int[][] board){
        for (int[] line : board){
            System.out.println(Arrays.toString(line));
        }
        System.out.println();
    }
}
