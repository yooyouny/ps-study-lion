import java.util.Arrays;
public class Solution {
    public int solution(int[][] data, int col, int rowBegin, int rowEnd){
        int answer = 0;
        Arrays.sort(data, ((o1, o2) -> o1[col - 1] != o2[col - 1] ? o1[col - 1] - o2[col - 1] : o2[0] - o1[0]));
        for (int i = rowBegin - 1; i <= rowEnd - 1; i++) {
            int S_i = 0;
            for (int sum : data[i]) {
                S_i += (sum % (i + 1));
            }
            answer ^= S_i;
        }
        return answer;
    }
}
