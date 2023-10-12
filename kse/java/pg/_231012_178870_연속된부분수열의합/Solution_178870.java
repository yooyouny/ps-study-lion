package java.pg._231012_178870_연속된부분수열의합;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/178870
//신규_프로그래머스_lv2_178870_연속된부분수열의합
public class Solution_178870 {
    public int[] solution(int[] sequence, int k) {
        int start = 0;              //부분 수열의 시작 좌표
        int end   = 0;              //부분 수열의 끝 좌표
        int sum   = sequence[0];    //부분 수열의 합

        // 합이 k인 부분 수열을 담을 queue
        // {수열의 길이, 시작 좌표, 끝좌표}를 담는다.
        // 0번째 항을 기준으로 정렬하여 가장 길이가 작은 수열이 앞으로 오도록 한다.
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // end 가 수열의 길이를 초과하지 않으면 계속 돌도록 한다.
        while (end <= sequence.length-1){
            // 합이 k보다 작다면 end 를 늘려준다.
            if(sum < k){
                end += 1;
                if(end >= sequence.length) break;
                sum += sequence[end];
            }
            // 합이 k보다 크다면 start 를 줄여준다.
            else {
                //sum 이 k와 같다면 queue 에 값을 넣어준다.
                if(sum == k) queue.add(new int[]{end-start, start, end});
                sum -= sequence[start];
                start += 1;
            }
        }
        // 가장 길이가 작은 부분 수열의 좌표값을 return 해준다.
        int[] temp = queue.poll();
        return new int[] {temp[1], temp[2]};
    }
}
