class Solution {
    /**
     * 점화식 - dp[i] = Max(dp[i - 1], arr[i] + dp[i - 2])
     * @param money
     * @return
     */
    int[] first_dp;
    int[] second_dp;
    int[] first_arr;
    int[] second_arr;
    int n;
    public int solution(int[] money) {
        n = money.length;
        initArr(money);
        first_dp[0] = 0;
        second_dp[0] = 0;
        first_dp[1] = first_arr[1];

        second_dp[1] = second_arr[1];

        for (int i = 2; i < n; i++) {
            first_dp[i] = Math.max(first_dp[i - 1], first_arr[i] + first_dp[i -2]);
            second_dp[i] = Math.max(second_dp[i - 1], second_arr[i] + second_dp[i -2]);
        }
        return Math.max(first_dp[n-1], second_dp[n-1]);
    }


    private void initArr(int[] money){
       first_arr = new int[n];
       second_arr = new int[n];
       first_dp = new int[n];
       second_dp = new int[n];
       first_arr[0] = 0;
       second_arr[0] = 0;
       int index = 1;
        for (int i = 0; i < n - 1; i++) {
            first_arr[index++] = money[i];
        }
        index = 1;
        for (int i = 1; i < n; i++) {
            second_arr[index++] = money[i];
        }
    }
}
