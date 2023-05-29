package java.pg._230529_68645_삼각달팽이;

import java.util.ArrayList;

//복습_프로그래머스_lv2_68645_삼각달팽이
//https://school.programmers.co.kr/learn/courses/30/lessons/68645
public class Solution_68645 {
    /**
     * board를 answer에 맞게 바꿔주는 함수
     * n번째 줄의 0에서 n번까지의 값을 ArrayList result에 담아 배열로 변환해준다.
     */
    public int[] makeArray(int[][] board){
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < board.length ; i++){
            for(int j = 0; j <= i ; j++){
                result.add(board[i][j]);
            }
        }
        return result.stream().mapToInt(i -> i).toArray();

    }
    public int[] solution(int n) {
        int[] answer = {};
        int[][] board = new int[n][n];
        int nn =  1;    //들어갈 값, 계산 횟수
        int dx =  0;    //x의 좌표값
        int dy = -1;    //y의 좌표값
        int[] mx = {-1, 0, 1}; //pattern에 따른 dx의 움직임
        int[] my = {-1, 1, 0}; //pattern에 따른 dy의 움직임
        for(int i = 1; i <= n; i++){
            for(int j = 0 ; j <= n-i ; j++){
                dx += mx[i%3];  //i%3이 0이면 대각선, 1이면 아래로, 2이면 위로 움직인다.
                dy += my[i%3];
                board[dy][dx] = nn;
                nn++;
            }
        }
        //board를 answer에 맞게 바꿔주는 함수
        answer = makeArray(board);
        return answer;
    }
}
