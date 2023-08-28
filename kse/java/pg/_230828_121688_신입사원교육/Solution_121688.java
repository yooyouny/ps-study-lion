package java.pg._230828_121688_신입사원교육;

import java.util.PriorityQueue;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/15009/lessons/121688
//신규_프로그래머스_lv2_121688_신입사원교육
public class Solution_121688 {
    public int solution(int[] ability, int number) {
        int answer = 0;

        //신입사원의 능력을 담을 queue
        Queue<Integer> newcomers = new PriorityQueue<>();
        for(int temp : ability) newcomers.add(temp); // 모든 사원의 정보 삽입

        for (int i = 0; i < number; i++) {
            int member1 = newcomers.poll();   // 가장 능력치가 낮은 2명 선택하여
            int member2 = newcomers.poll();
            newcomers.add(member1 + member2); // 교육 후의 능력치를 삽입
            newcomers.add(member1 + member2);
        }
        for (int temp : newcomers) answer += temp;
        return answer;
    }
}
