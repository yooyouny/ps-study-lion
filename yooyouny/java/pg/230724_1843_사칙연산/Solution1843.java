import java.util.*;
/*
https://velog.io/@longroadhome/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-LV.4-%EC%82%AC%EC%B9%99%EC%97%B0%EC%82%B0
참고링크
* */
class Solution1843 {
    public int solution(String arr[]) {
        int opCnt = (arr.length + 1) / 2;// 피연산자 수의 개수
        int[][] max_dp = new int[opCnt][opCnt];// i번째 피연산자부터 j번째 피연산자의 최댓값
        int[][] min_dp = new int[opCnt][opCnt];// i번째 피연산자부터 j번째 피연산자의 최솟값

        for(int[] row : max_dp){// 비교연산을 위한 최솟값, 최댓값 세팅
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        for(int[] row : min_dp){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 0; i < opCnt; i++) {// dp배열에 초기화 피연산자 자리에 숫자로 변환해서 넣기
            max_dp[i][i] = Integer.parseInt(arr[i * 2]);
            min_dp[i][i] = Integer.parseInt(arr[i * 2]);
        }

        for (int cnt = 1; cnt < opCnt; cnt++) {
            for (int i = 0; i < opCnt - cnt; i++) {// 구간의 시작
                int j = i + cnt;// 구간의 끝
                for (int k = i; k < j; k++) {// 피연산자 범위를 구간의 시작부터 끝까지 순차적으로 증가
                    if (arr[k * 2 + 1].equals("+")) {
                        max_dp[i][j] = Math.max(max_dp[i][j], max_dp[i][k] + max_dp[k + 1][j]);
                        min_dp[i][j] = Math.min(min_dp[i][j], min_dp[i][k] + min_dp[k + 1][j]);
                    } else {
                        max_dp[i][j] = Math.max(max_dp[i][j], max_dp[i][k] - min_dp[k + 1][j]);// 연산의 결과가 최댓값이 되려면 최댓값 - 최솟값
                        min_dp[i][j] = Math.min(min_dp[i][j], min_dp[i][k] - max_dp[k + 1][j]);
                    }
                }
            }
        }

        return max_dp[0][opCnt - 1];// 구간의 범위가 처음부터 끝까지에 해당되는 전체 범위에서의 최댓값
    }
}