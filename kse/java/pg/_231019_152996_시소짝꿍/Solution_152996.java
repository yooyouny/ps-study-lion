package java.pg._231019_152996_시소짝꿍;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://school.programmers.co.kr/learn/courses/30/lessons/152996
//신규_프로그래머스_lv2_152996_시소짝꿍
public class Solution_152996 {
    /**
     * 2, 3, 4 거리 중 어딘가에 위치한 두 사람의 거리 * 무게 값이 같은 경우 짝이 된다.
     * --------------------------------------------------------------------
     * 1. A가 항상 앞, B가 항상 뒤에 올 수 있도록 이를 정렬해준다.
     * 2. 정렬된 배열에서 앞 뒤 값이 일치 할 수 있는 비율을 구해준다.
     *    2A = 2B || 3A = 2B || 4A == 3B || 4A == 2B
     * 3. 나보다 작은 값들 중에 나와 짝이 될 수 있는 값을 구하는 계산식의 비율을 배열로 만들어준다.
     *    int[][] ratios = new int[][]{{1,1},{2,3},{1,2},{3,4}};
     *    4A == 3B 를 찾을 때 A = (3/4)B
     *    나의 3/4 값의 개수를 찾으면 해당 비율의 값을 찾을 수 있다.
     * 4. 이를 weights 를 차례로 돌면서 계산해준다.
     */
    public long solution(int[] weights) {
        long answer = 0;

        // weight 의 i번째 값이 i+1번째 값보다 항상 작거나 같을 수 있도록 정렬
        Arrays.sort(weights);

        // 정렬된 무게 배열에서 앞 뒤 값이 일치할 수 있는 배율의 배열
        int[][] ratios = new int[][]{{1,1},{2,3},{1,2},{3,4}};

        // weight 의 비율값을 저장할 map
        Map<Double, Integer> results = new HashMap<>();


        System.out.println(Arrays.toString(weights));

        for (int weight : weights) {
            // 각 비율값이 map 에 있다면 시소 짝꿍이 result 의 값만큼 있다는 의미 이므로
            // answer 에 저장
            double number = weight * 1.0;
            for (int j = 0; j <= 3; j++) {
                double key = (number * ratios[j][0]) / ratios[j][1];
                answer += results.getOrDefault(key, 0);
            }
            // result 의 weight 를 저장
            results.put(number, results.getOrDefault(number, 0)+1);
        }
        return answer;
    }

}
