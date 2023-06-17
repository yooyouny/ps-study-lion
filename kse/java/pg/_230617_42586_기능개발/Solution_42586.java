package java.pg._230617_42586_기능개발;

import java.util.*;
//https://school.programmers.co.kr/learn/courses/30/lessons/42586?language=java
//신규_프로그래머스_lv2_기능개발
public class Solution_42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();

        //progress를 처리하는데에 걸리는 작업일
        Queue<Integer> days  = new ArrayDeque<>();
        for (int i = 0; i < progresses.length; i++) {
            //나머지가 0이 아닌경우 +1을 해줘야하기 때문에 Math.ceil(올림) 사용
            days.add((int) Math.ceil((float)(100-progresses[i])/speeds[i]));
        }

        int now = days.peek();  //현재 경과일
        int temp;               //다음 업무의 필요 경과일
        int complete = 0;       //현재 경과일까지 완료된 작업의 수
        while (!days.isEmpty()){
            temp = days.poll();
            if(now >= temp) complete++; //현재경과일까지 이미 처리된 일이라면 compete++
            else{                       //현재경과일까지 처리되지 못한 업무라면
                answer.add(complete);   //answer에 now에 배포될 업무 개수를 넣어준다.
                complete = 1;
                now = temp;
            }
        }
        answer.add(complete);
        return answer.stream().mapToInt(i -> i).toArray();
    }
}
