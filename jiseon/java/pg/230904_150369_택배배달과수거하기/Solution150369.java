package com.example.algorithm.ps;

public class Solution150369 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int d = 0, p = 0;  // 수용 가능한 배달, 수거 택배 수

        // 최대 용량만큼 배달하고, 수거하며 이동한다.
        for (int i = n - 1; i >= 0; i--) {
            d -= deliveries[i];
            p -= pickups[i];

            while (d < 0 || p < 0) {  // 음수라면, 최대 용량 초과로 해당 집에서 배달, 수거가 불가능
                d += cap; // 다시 방문하여 배달, 수거
                p += cap;
                answer += (i + 1) * 2;  // 다시 방문했기 때문에, 왕복 거리만큼 더해줌
            }
        }
        return answer; // 최소 이동거리 리턴
    }
}
