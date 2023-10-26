package java.pg._231026_154538_숫자변환하기;

import java.util.LinkedList;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/154538
//신규_프로그래머스_lv2_154538_숫자변환하기
public class Solution_154538 {
    public int solution(int x, int y, int n) {
        //BFS

        Queue<int[]> queue = new LinkedList<>();
        // 시작 값을 Y로 선택
        // X에서 * + 를 사용한 경우 시간 초과가 나기 쉽기 떄문에
        // Y에서 / - 를 사용하여 나눈 값이 소수인 경우를 가지치기 해주기 위함.
        queue.add(new int[]{y, 0});

        while (!queue.isEmpty()){
            int[] now = queue.poll();
            int value = now[0];
            int count = now[1];

            // value == x 이면 조건을 만족 return
            if(value == x) return count;

            // 올바른 계산인지 확인 후 queue 에 다음 계산할 값을 넣어준다.
            if(value - n >  0) queue.add(new int[]{value - n, count+1});
            if(value % 2 == 0) queue.add(new int[]{value / 2, count+1});
            if(value % 3 == 0) queue.add(new int[]{value / 3, count+1});

        }
        return -1;
    }
}
