package java.pg._231023_169198_당구연습;
//https://school.programmers.co.kr/learn/courses/30/lessons/169198
//신규_프로그래머스_lv2_169198_당구연습

import java.util.*;

public class Solution_169198 {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        //부딪힐 벽 선택하기
        for (int i = 0; i < balls.length; i++) {
            int[] targetBall = balls[i];
            //(a, b) = ( startX, startY )
            List<int[]> findCase = new ArrayList<>();
            //상
            findCase.add(new int[]{n - startY, n - targetBall[1], Math.abs(startX - targetBall[0])});
            //하
            findCase.add(new int[]{startY, targetBall[1], Math.abs(startX - targetBall[0])});
            //좌
            findCase.add(new int[]{startX, targetBall[0], Math.abs(startY - targetBall[1])});
            //우
            findCase.add(new int[]{m - startX, m - targetBall[0], Math.abs(startY - targetBall[1])});

            int minDistance = Integer.MAX_VALUE;
            for (int[] temp : findCase) {
                minDistance = Math.min(minDistance, calc(temp));
            }
            answer[i] = minDistance;
        }
        return answer;
    }

    public int calc(int[] array){
        int col = array[0] + array[1];
        int row = array[2];

        //X, Y 좌표값 중 하나가 수평한 경우인데
        if(row == 0){
            // 벽을 치러 갈 때 공을 지나가는 경우
            if(array[0] > array[1]) return Integer.MAX_VALUE;
            //벽을 치고 올라가서 공을 치는 경우
            return (int) Math.pow(col, 2);
        }
        //직각 삼각형
        return (int) Math.pow(col, 2) + (int) Math.pow(row,2);
    }
}
