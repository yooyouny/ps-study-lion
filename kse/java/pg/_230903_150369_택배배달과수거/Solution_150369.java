package java.pg._230903_150369_택배배달과수거;

import java.util.PriorityQueue;

//https://school.programmers.co.kr/learn/courses/30/lessons/150369
//신규_프로그래머스_lv2_150369_택배배달과수거하기
public class Solution_150369 {
    /**
     *
     * @param cap   트럭에 실을 수 있는 재활용 상자 개수
     * @param n     배달할 집의 개수
     * @param deliveries    i+1번째 집에 배달할 재활용 택배 상자의 개수
     * @param pickups       i+1번째 집에서 수거할 빈 재활용 택배 상자의 개수를
     * @return  모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리
     */
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        // 배달할 집의 index 와 수거할 집의 index 를 해당 개수만큼 넣어 내림차순으로 정렬한다.
        PriorityQueue<Integer> delivery = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); // 내림차순 정렬
        PriorityQueue<Integer> pickup = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int i = 0; i < deliveries.length; i++) {
            for (int j = 0; j < deliveries[i]; j++) delivery.offer(i+1);
        }
        for (int i = 0; i < pickups.length; i++) {
            for (int j = 0; j < pickups[i]; j++) pickup.offer(i+1);
        }

        // delivery 와 pickup 이 비어있지 않은 경우
        while (!delivery.isEmpty() || !pickup.isEmpty()){
            int deliveryDistance = 0;
            int pickDistance = 0;
            // 배달과 수거의 최대 거리를 구해 준다.
            // 내림차순으로 정렬되어 있기 때문에 Queue 의 가장 값의 index 값이 최대 거리가 된다.
            if(!delivery.isEmpty()) deliveryDistance = delivery.peek();
            if(!pickup.isEmpty())   pickDistance = pickup.peek();

            // 차량에 실을 수 있는 개수만큼 Queue 에서 빼준다.
            for (int i = 0; i < cap; i++) {
                if(!delivery.isEmpty()) delivery.poll();
                if(!pickup.isEmpty()) pickup.poll();
            }
            // 왕복을 기준으로 하여 한번 다녀올 때의 거리를 answer 에 넣어준다.
            answer += Math.max(deliveryDistance, pickDistance)* 2L;
        }
        return answer;
    }
}
