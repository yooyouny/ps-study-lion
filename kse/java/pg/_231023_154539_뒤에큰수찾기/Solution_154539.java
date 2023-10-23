package java.pg._231023_154539_뒤에큰수찾기;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/154539
//신규_프로그래머스_lv2_154539_뒤에있는큰수찾기
public class Solution_154539 {
    /**
     * 뒷 큰 수 => 배열의 각 원소들에 대해 자신보다 뒤에 있는 숫자 중에서 자신보다 크면서 가장 가까이 있는 수
     *
     */
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);

        // int[] -> int [value, index]
        // 기준 숫자보다 커서 뒷큰수를 찾지 못한 경우 queue 에 넣는다면, 가장 큰 수가 기준 숫자에서 가장 먼 숫자가 된다.
        // 따라서 value 를 기준으로 정렬해준다.
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < numbers.length; i++) {
            int nowValue = numbers[i];

            // 아직 뒷큰수를 찾지 못한 숫자가 남아 있고 &&
            // 현재 숫자보다 작은 경우, 현재 숫자가 해당 숫자의 뒷큰수가 된다.
            while (!queue.isEmpty() && queue.peek()[0] < nowValue){
                int[] smallerNumber = queue.poll();
                answer[smallerNumber[1]] = nowValue;
            }
            queue.add(new int[] {nowValue, i});
        }
        return answer;
    }
}
