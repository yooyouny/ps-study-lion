import java.util.*;
/*
* 가장 밑의 삼각형 라인 -1 부터 해당 위치에 있는 값과 그 아래 라인 중 가장 큰 값을 더해서 반복
* dp[i][j] += Math.max(dp[i+1][j] + dp[i+1][j+1])
* */
class Solution43105 {
    public int solution(int[][] triangle) {
        for(int i=triangle.length - 2; i>=0; i--){// 가장 아래 라인부터 삼각형 꼭대기까지
            for(int j=0; j<triangle[i].length; j++){
                triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);// 내 위치 있는 왼쪽, 오른쪽 수 중 가장 큰 수와 합해져야 거쳐간 수 중 가장 큰 값이 됨
            }
        }
        return triangle[0][0];// 맨 꼭대기가 가장 큰 수
    }
}