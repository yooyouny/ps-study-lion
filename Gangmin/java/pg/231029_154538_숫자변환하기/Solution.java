import java.util.Arrays;

public class Solution {

    /**
     * 원래 문제 : y를 만족하는 최소 연산을 찾아라
     * 가짜 문제 : (y / 3, y / 2, y - n)을 만족하는 최소 연산을 찾아라
     * 가짜 문제를 풀었을때 진짜 문제를 풀수 있는가? -> yes : y / 3을 만족하는 최소 연산을 찾으면, 다음 y * 3이 y / 3에서 갈 수 있는 최소 연산
     * 가장 작은 가짜 문제는 무엇인가?
     *  pick (x + n, x * 2, x * 3)값에 대한 최소 연산 : 1
     * @param x
     * @param y
     * @param n
     * @return
     */
    public int solution(int x, int y, int n) {
        // 연산횟수를 저장할 배열
        int[] dp = new int[1_000_001];
        Arrays.fill(dp, -1);
        dp[x] = 0;
        // y -> 진짜 문제: y를 만족하는 최소 연산 = dp[y]
        // i -> 가짜 문제 : i를 만족하는 최소 연산 = dp[i]
        for (int i = x; i < y; i++) {
            //연산된 적이 있는 경우 : x값에서 3가지 연산을 해서 도달할 수 있는 경우
            if(dp[i] != -1){
                if(i + n <= y){
                    // i + n 값을 다른 연산이 도출한적 없는 경우
                    if(dp[i + n] == -1){
                        dp[i + n] = dp[i] + 1;
                    } else {
                        // 다른 연산이 도출한적이 있다면, 최소 연산을 대입
                        dp[i + n] = Math.min(dp[i] + 1, dp[i + n]);
                    }
                }
                if(i * 2 <= y){
                    // i * 2 값을 다른 연산이 도출한적 없는 경우
                    if(dp[i * 2] == -1){
                        dp[i * 2] = dp[i] + 1;
                    }else {
                        dp[i * 2] = Math.min(dp[i] + 1, dp[i * 2]);
                    }
                }
                if(i * 3 <= y){
                    // i * 3 값을 다른 연산이 도출한적 없는 경우
                    if(dp[i * 3] == -1){
                        dp[i * 3] = dp[i] + 1;
                    }else {
                        dp[i * 3] = Math.min(dp[i] + 1, dp[i * 3]);
                    }
                }
            }
        }
        //진짜문제의 답 dp[y] return
        return dp[y];
    }
}

