package java.pg._231109_134239_우박수열정적분;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//https://school.programmers.co.kr/learn/courses/30/lessons/134239
//신규_프로그래머스_lv2_134239_우박수열정적분
public class Solution_134239 {
    /**
     * 1. 콜라츠 추측의 각 단계의 값을 구한다.
     * 2. 우박수 그래프의 x값 1 단위의 넓이를 구한다.
     * 3. 정해진 범위 range 의 start end 값 안의 넓이를 더해준다.
     */
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];    // 정답 배열
        int[] collatz   = calcCollatz(k);               // 콜라츠 추측의 단계별 값을 구한다.
        double[] width  = calcWidth(collatz);           // x좌표 1마다 넓이를 구해준다.

        for (int i = 0; i < ranges.length; i++) {
            // 범위는 (range[0]번 항) ~ (그래프 길이 k + range[1])
            int start = ranges[i][0];
            int end   = width.length + ranges[i][1];

            // 시작 좌표가 더 큰 경우 -1 return
            if(start > end){
                answer[i] = -1;
                continue;
            }

            // 넓이 구하기
            for (int j = start; j < end; j++) {
                answer[i] += width[j];
            }
        }
        return answer;
    }

    // 그래프 구간별 넓이 구하기
    private double[] calcWidth(int[] collatz) {
        double[] array = new double[collatz.length - 1];
        for (int i = 1; i < collatz.length; i++) {
            array[i - 1] = Math.min(collatz[i - 1], collatz[i])
                    + (double) Math.abs(collatz[i] - collatz[i - 1]) /2;
        }
        return array;
    }

    // 콜라츠 추측 단계별 값 구하기
    private int[] calcCollatz(int k) {
        List<Integer> list = new ArrayList<>();
        list.add(k);
        while (k != 1){
            if(k % 2 == 0) k /= 2;
            else k = k * 3 + 1;
            list.add(k);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
