package com.example.algorithm.ps;
import java.util.*;

public class Solution121689 {
    public int solution(int[] menu, int[] order, int k) {
        int answer = Integer.MIN_VALUE;
        Queue<Integer> queue = new LinkedList<>();  // 대기 큐, 각 주문의 소요 시간을 저장함
        int current = 0, complete = menu[order[0]]; // 첫 주문 완료 시간 설정

        for (int i = 1; i < order.length; i++) {
            current += k;  // k 초마다 새로운 손님이 옴, 현재 시간에 더해줌

            while (complete <= current && !queue.isEmpty())
                complete += queue.poll(); // 주문 시작할 수 있는 상태, 대기 큐에서 꺼내 완성 시간 갱신

            if (complete <= current)
                complete = current; // 현재 손님 주문 시간으로 갱신

            queue.offer(menu[order[i]]);

            // 주문 완료 시간이 더 크면, 이전 손님도 대기중이므로 +1
            int wait = (complete > current ? 1 : 0) + queue.size();
            answer = Math.max(answer, wait);  // 동시에 존재한 손님 수 최댓값 갱신
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Solution121689().solution(new int[]{5, 12, 30}, new int[]{1, 2, 0, 1}, 10));
    }

}
