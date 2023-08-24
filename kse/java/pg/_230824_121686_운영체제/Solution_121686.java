package java.pg._230824_121686_운영체제;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/15008/lessons/121686
//신규_프로그래머스_lv3_121686_운영체제
public class Solution_121686 {
    /**
     * program[ 점수, 호출 시각, 실행 시간 ]
     * programQueue -> 호출 시각 기준 정렬
     * waitingQueue -> 점수, 호출 시각, 실행 시간 모두 정렬
     */
    public long[] solution(int[][] program) {
        long[] answer = new long[11];

        //프로그램 큐
        Queue<int[]> programQueue
                = new PriorityQueue<>(Comparator.comparingInt((int[] arr) -> arr[1]));
        programQueue.addAll(Arrays.asList(program));
        //대기 큐
        Queue<int[]> waitingQueue = new PriorityQueue<>(
                Comparator.comparingInt((int[] arr) -> arr[0])
                        .thenComparingInt(arr -> arr[1])
                        .thenComparingInt(arr -> arr[2])
        );

        long time = 0; // 현재 시간

        // 2개의 큐 중 하나라도 남아있는 값이 있다면
        while(!programQueue.isEmpty() || !waitingQueue.isEmpty()){

            // programQueue 중에 현재 시간 기준으로 실행할 수 있는 프로그램 풀러오기
            while(!programQueue.isEmpty() && programQueue.peek()[1] <= time){
                waitingQueue.add(programQueue.poll());
            }
            // waitingQueue가 비어있다면 programQueue에서 가장 빠른 실행시간 불러오기
            if(waitingQueue.isEmpty()){
                time = programQueue.peek() != null ? programQueue.peek()[1] : time+1;
                continue;
            }

            // 프로그램 실행
            int[] exeProgram = waitingQueue.poll();
            int score = exeProgram[0];
            int exeTime = exeProgram[2];
            // 대기 시간
            long waitingTime = time - exeProgram[1];
            // 현재 시간 갱신
            time += exeTime;
            // 실행 프로그램의 score 에 대기시간 추가
            answer[score] += waitingTime;

        }
        // 총 실행 시간 저장
        answer[0] = time;
        return answer;
    }

}
