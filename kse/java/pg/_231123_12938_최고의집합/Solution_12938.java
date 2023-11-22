package java.pg._231123_12938_최고의집합;

import java.util.Arrays;

//https://school.programmers.co.kr/learn/courses/30/lessons/12938
//신규_프로그래머스_lv3_12938_최고의집합
public class Solution_12938 {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];

        // n이 2라고 할 때 1 + (s - 1) 보다는 중간값 s/2 + s/2를 곱했을 때 더 큰 값이 나온다.
        // n개의 항을 채울 수 있도록 s를 최대한 공평하게 나눴을 때 제곱값이 커질 수 있다.
        // 중간값 middleValue 에서 부족한 값만큼 answer 의 각 요소에 채웠을 때 정답이 나온다.
        int middleValue = s/n;

        // 모든 자리에 1을 넣어도 합이 s가 되지 못하면 -1
        if(middleValue < 1) return new int[]{-1};

        // answer 배열에 middleValue 를 채워준다.
        Arrays.fill(answer, middleValue);

        // 나머지가 0아닌 경우
        if (s % n != 0) {
            // 오름차순이기에 나머지만큼의 항에 뒤부터 +1씩 채워준다.
            for (int i = n-1; i > s % n; i--) answer[i] += 1;
        }

        return answer;
    }
}
