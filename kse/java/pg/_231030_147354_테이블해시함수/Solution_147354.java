package java.pg._231030_147354_테이블해시함수;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//https://school.programmers.co.kr/learn/courses/30/lessons/147354
//신규_프로그래머스_lv2_147354_테이블해시함수
public class Solution_147354 {
    /**
     * 1. 테이블의 튜플을 col 번째 컬럼의 값을 기준으로 오름차순 정렬
     * 2. 만약 그 값이 동일하면 기본키인 첫 번째 컬럼의 값을 기준으로 내림차순 정렬
     * 3. S_i를 i 번째 행의 튜플에 대해 각 컬럼의 값을 i 로 나눈 나머지들의 합으로 정의
     */
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        // 계산의 편의를 위해 -1
        int COL = col - 1;
        row_begin -= 1;
        row_end   -= 1;

        // 튜플을 col 번째 컬럼의 값을 기준으로 오름차순 정렬
        // 값이 동일하면 기본키인 0 번째 컬럼의 값을 기준으로 내림차순 정렬
        Arrays.sort(data, (a, b) -> {
            if(a[COL] == b[COL]) return Integer.compare(b[0], a[0]);
            else return Integer.compare(a[COL], b[COL]);
        });

        // s_i값을 담을 List 생성
        List<Integer> s_i = new ArrayList<>();

        // 범위 내의 s_i값 구하여 리스트에 추가
        for (int i = row_begin; i <= row_end; i++) {
            int sum = 0;
            for (int value : data[i]){ sum += value%(i+1); }
            s_i.add(sum);
        }

        // 순차적으로 xor 연산
        for (int value : s_i) answer ^= value;

        return answer;
    }
}
