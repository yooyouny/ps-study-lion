package com.example.algorithm.ps;

import java.util.*;

public class Solution121686 {
    public long[] solution(int[][] program) {
        long[] answer = new long[11];
        Arrays.sort(program, (o1, o2)->(o1[1] == o2[1]) ? o1[0] - o2[0] : o1[1] - o2[1]);  // 호출 시각 기준으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)-> (o1[0] == o2[0])?o1[1] - o2[1] : o1[0] - o2[0]); // 점수 기준으로 우선 순위 대기 큐

        int time = 0;  // 실행 시간
        int[] pro = null;

        // 순차적으로 실행시키면서 실행 시간 동안 호출되는 프로그램 큐에 추가
        for(int[] p : program){
            while(!pq.isEmpty() && time < p[1]){
                pro = pq.poll();
                if (time < pro[1]) time = pro[1]; // 현재 시간이 호출 시각보다 작다면, 해당 호출 시각으로 갱신
                answer[pro[0]] += time - pro[1];  // 해당 점수의 프로그램 대기 시간 저장
                time += pro[2];  // 현재시간 갱신
            }
            pq.offer(p);
        }

        while(!pq.isEmpty()){ //프로그램 종료 시, 큐에 남아있는 프로그램까지 계산
            pro = pq.poll();
            if (time < pro[1]) time = pro[1];
            answer[pro[0]] += time - pro[1];
            time += pro[2];
        }

        answer[0] = time;  // 모든 프로그램이 종료되는 시각
        return answer;
    }

    public static void main(String[] args) {
        Solution121686 solution = new Solution121686();
        int[][] programs = {{2, 0, 10}, {1, 5, 5}, {3, 5, 3}, {3, 12, 2}};
        long[] result = solution.solution(programs);
        System.out.println(Arrays.toString(result));
    }
}