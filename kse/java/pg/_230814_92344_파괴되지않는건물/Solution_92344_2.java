package java.pg._230814_92344_파괴되지않는건물;

import java.util.Arrays;

//https://school.programmers.co.kr/questions/25471
//신규_프로그래머스_lv3_92344_파괴되지않는건물
//해설 : https://tech.kakao.com/2022/01/14/2022-kakao-recruitment-round-1/
public class Solution_92344_2 {
    /**
     * skill [type, r1, c1, r2, c2, degree]
     * type 1 적의 공격 | type 2 회복 스킬
     * degree : 변동 수치
     * =====================================
     * 누적합 : 특정한 배열이 있을 때, 해당 배열까지의 합을 나타내는 말
     * [1,2,3,4,5] 배열의 누적합을 배열로 나타내면 [1,3,6,10,15]
     * -------------------------------------
     * [1,0,0,3,4,4]
     * 0 ~ 3 열의 0 ~ 4번 까지의 배열에 -4
     * [-4 0 0 0 0 4] -> [-4 -4 -4 -4 -4 0]
     */
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] temp = new int[board.length+1][board[0].length+1];
        for (int[] turn : skill){
            int x1 = turn[2];
            int x2 = turn[4]+1;
            int y1 = turn[1];
            int y2 = turn[3]+1;
            // type 1 적의 공격 | type 2 회복 스킬
            int degree = turn[0] == 1 ? -turn[5] : turn[5];
            temp[y1][x1]+=degree;   //시작점
            temp[y2][x2]+=degree;
            temp[y1][x2]-=degree;
            temp[y2][x1]-=degree;
        }
        calcPrefixSum(board, temp);

        //부서지지 않은 건물을 세준다.
        for (int[] line : board){
            answer += Arrays.stream(line).filter(val -> val > 0).count();
        }
        return answer;
    }



    /** 가산합을 계산하는 메서드 */
    public void calcPrefixSum(int[][] board, int[][] SumBoard){
        //위에서 아래로
        for (int i = 1; i < SumBoard.length; i++) {
            for (int j = 0; j < SumBoard[0].length; j++) {
                SumBoard[i][j] += SumBoard[i-1][j];
            }
        }
        //왼쪽에서 오른쪽으로
        for (int i = 0; i < SumBoard.length; i++) {
            for (int j = 0; j < SumBoard[0].length -1; j++) {
                SumBoard[i][j+1] += SumBoard[i][j];
            }
        }
        //board 와 가산합 board 를 합산
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] += SumBoard[i][j];
            }
        }
    }

    public void printArray(int[][] board){
        for (int[] line : board){
            System.out.println(Arrays.toString(line));
        }
        System.out.println();
    }
}

