import java.util.*;
import java.util.stream.*;
class Solution121688 {
    public int solution(int[] ability, int number) {
        PriorityQueue<Integer> pq = Arrays.stream(ability)
                .boxed()// 기본타입 -> 래퍼타입
                .collect(Collectors.toCollection(PriorityQueue::new));

        while(number-- > 0){
            int stats = pq.poll() + pq.poll();
            pq.offer(stats);
            pq.offer(stats);
        }

        // 능력치 총합 구하기
        int answer = 0;
        while(!pq.isEmpty()){
            answer += pq.poll();
        }

        return answer;
    }
}