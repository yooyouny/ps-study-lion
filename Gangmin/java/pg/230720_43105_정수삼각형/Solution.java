public class Solution {

	static int[][] dp;

	public int solution(int[][] triangle) {
		int answer = 0;
        //최댓값을 저장 해줄 dp 테이블
		dp = new int[triangle.length][triangle.length];
		dp[0][0] = triangle[0][0];
		for (int i = 1; i < triangle.length; i++) {
            //가장 왼쪽 구간의 최댓값
			dp[i][0] = triangle[i][0] + dp[i - 1][0];

            //중간 구간의 최댓값
			for (int j = 1; j <= i; j++) {
				dp[i][j] = triangle[i][j] + Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
			}
            //가장 오른쪽 구간의 최댓값
			dp[i][i] = triangle[i][i] + dp[i - 1][i - 1];
		}

        //마지막 줄에는 각 경로마다 최댓값들이 계산되어있다.
		for (int i = 0; i < triangle.length; i++) {
			answer = Math.max(answer, dp[triangle.length - 1][i]);
		}


		return answer;
	}
}

