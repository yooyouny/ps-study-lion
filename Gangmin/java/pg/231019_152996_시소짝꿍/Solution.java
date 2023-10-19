import java.util.Arrays;
class Solution {
    //쌍으로 구할수 있는 비율
    int[][] ratio = {{1, 1}, {3, 2}, {4, 2}, {4, 3}};

    public long solution(int[] weights) {
        //쌍의 갯수
        long answer = 0;
        //이분탐색으로 위한 정렬
        Arrays.sort(weights);
        //weight[i]의 비율별 쌍 구하기
        for (int[] r : ratio) {
            for (int i = 0; i < weights.length; i++) {
                //이항: x : y = r1 : r2 -> y = (x * r2) / r1;
                if((weights[i] * r[0]) % r[1] != 0) continue;
                int y = (weights[i] * r[0]) / r[1];
                int up = upperBound(y, i + 1, weights.length, weights);
                int down = lowerBound(y, i + 1, up, weights);
                answer += up - down;
            }
        }
        return answer;
    }


    //TODO: 상한값 구하기
    private int upperBound(int target, int left, int right, int[] weights){
        while (left < right){
            int mid = (left + right) / 2;
            if(target < weights[mid]){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }

    //TODO: 하한값 구하기
    private int lowerBound(int target, int left, int right, int[] weights){
        while (left < right){
            int mid = (left + right) / 2;
            if(target <= weights[mid]){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }

}
