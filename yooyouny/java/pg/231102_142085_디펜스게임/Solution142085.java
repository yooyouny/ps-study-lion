import java.util.*;
class Solution142085 {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();// 병사의 인원수가 작은 순서부터 정렬되는 우선순위큐 사용

        for(int round = 0; round<enemy.length; round++){
            queue.add(enemy[round]);// 큐에 넣을떄마다 무적권 사용 대상

            if(queue.size() > k){// 다음으로 더 이상 무적권 사용을 할 수가 없게 되면
                n -= queue.poll();// 기존에 사용했던 무적권 대상 중 가장 작은 병력수에는 사용할 필요가 없었으므로 복원
            }
            if(n < 0){// 병력 수보다 작아지게 되면 현재 라운드에서 종료
                return round;
            }
        }
        return enemy.length;// enemy 순회가 끝날떄까지 n이 남아있었으면 가장 마지막 라운드로 종료
    }
}