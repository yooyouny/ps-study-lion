package java.pg._231009_181188_요격시스템;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/181188?language=java
//신규_프로그래머스_lv2_181188_요격시스템
public class Solution_181188 {
    public int solution(int[][] targets) {
        int answer = 0;
        //targets 를 타겟의 끝자리를 기준으로 정렬하는 큐에 넣어준다.
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        queue.addAll(Arrays.asList(targets));

        //요격 미사일의 x 좌표를 넣을 변수를 만들어 준다.
        int endPoint = 0;

        //queue 를 통해 target 을 하나씩 확인한다.
        while (!queue.isEmpty()){
            //target 의 시작점이 endPoint 보다 크다면 endPoint 의 미사일로 요걱할 수 없다는 의미이므로
            if(queue.peek()[0]>= endPoint){
                endPoint = queue.poll()[1]; //endPoint 를 갱신해주고
                answer++;                   //answer 값을 추가해준다.
            }
            else queue.poll();
        }
        return answer;
    }
}

