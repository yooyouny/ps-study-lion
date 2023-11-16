package java.pg._231116_12927_야근지수;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/12927
//신규_프로그래머스_lv3_112927_야근지수
public class Solution_12927 {
    public long solution(int n, int[] works) {
        long answer = 0;
        // 내림차순 정렬 일 목록
        Queue<Long> workList = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) workList.add((long) work);

        // 가장 일하는 시간이 긴 업무부터 가져와서 처리한다.
        while (n > 0 && !workList.isEmpty()){
            long work = workList.poll();
            if(work > 1) workList.add(work - 1);
            n--;
        }

        // 남은 업무의 시간의 제곱합을 구해준다.
        for (long work : workList)  answer += work * work;

        return answer;
    }
}
