import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] ability, int number) {
        int answer = 0;
        //가장 작은 숫자 두개를 골라 더해야 최솟값이 나오니 우선순위큐로 정렬
        PriorityQueue<Integer> scoreBoards = Arrays.stream(ability)
            .boxed()
            .collect(Collectors.toCollection(PriorityQueue::new));
        //가장 작은 두값을 큐에서 꺼내 더해서 넣어준다.
        for (int i = 0; i < number; i++) {
            Integer least = scoreBoards.poll();
            Integer leastSecond = scoreBoards.poll();
            scoreBoards.offer(least + leastSecond);
            scoreBoards.offer(least + leastSecond);
        }
        //모든 요소의 합
        return scoreBoards.stream().reduce(0, Integer::sum);
    }
}
    
