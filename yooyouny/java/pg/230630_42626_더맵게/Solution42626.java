import java.util.*;
/*
* 반복문안에서 queue.poll()을 두번 하는데 조건이 !queue.isEmpty()만 있으면 런타임 에러 남
* */
class Solution42626 {
	public int solution(int[] scoville, int K) {
		int answer = 0;
		PriorityQueue<Integer> queue = new PriorityQueue<>();// 스코빌 지수가 작은 순으로 정렬되어있어야 하므로 pq사용
		for(int sco : scoville){
			queue.add(sco);
		}

		while(queue.peek() < K && queue.size() > 1){// 계속 정렬되기 때문에 맨 앞에 있는게 K보다 크면 빠져나오는 조건이 됨
			int first = queue.poll();
			int second = queue.poll();
			int mix = first + (second * 2);
			queue.add(mix);
			answer++;
		}

		return K > queue.peek() ? -1 : answer;// queue에서 최대치만큼 빼도 queue에 있는 값이 k보다 작으면 -1리턴
	}
}
