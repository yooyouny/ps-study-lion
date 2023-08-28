package com.example.algorithm.ps;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution121688 {
    public int solution(int[] ability, int number) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.stream(ability).forEach(pq::add);  // 큐에 능력치 저장

        for (int i = 0; i < number; i++) {
            int tmp = pq.poll() + pq.poll();
            pq.add(tmp);
            pq.add(tmp);
        }

        return pq.stream().mapToInt(Integer::intValue).sum();  // 큐에 담긴 능력치의 합 리턴
    }

    public static void main(String[] args) {
        System.out.println(new Solution121688().solution(new int[]{10, 3, 7, 2}, 2));
    }
}
