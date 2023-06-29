import java.util.*;
class Solution42627 {
	public int solution(int[][] jobs) {
		Arrays.sort(jobs, Comparator.comparingInt((int[] job) -> job[0]));// 요청시점 기준으로 오름차순 정렬

		Queue<int[]> queue = new LinkedList<>(Arrays.asList(jobs));// list로 변환 후 큐에 저장, 링크드 리스트는 큐의 구현체
		PriorityQueue<int[]> pq = new PriorityQueue<>(
			Comparator.comparingInt((int[] job) -> job[1]));// 우선순위 기준을 소요시간 기준으로 오름차순 정렬되게 설정

		int usage = 0;// 전체 걸린 시간
		int now = 0;// 현재 시간

		//queue는 아직 처리 되지 않은 대상들 저장
		//pq는 현재 처리중인 작업들 저장
		while (!queue.isEmpty() || !pq.isEmpty()) {// queue가 비어있는 경우면
			while (!queue.isEmpty() && queue.peek()[0] <= now) {// 현재 시간보다 작업의 시작 시간이 작으면
				//현재시간 이전에 들어온 작업들을 다 대상에 넣음
				pq.add(queue.poll());// 우선순위인 소요시간이 짧은 순서대로 정렬됨
			}

			if (pq.isEmpty()) {// 현재 처리할 작업이 없으면
				now = queue.peek()[0];// 시작시간 순으로 정렬한 큐에 있는 가장 빠른 시작시간을 현재시간으로 변경해서 진행
				continue;
			}

			int[] job = pq.poll();// 처리해야 할 대상 poll
			usage += now + job[1] - job[0];// 해당 작업의 소요시간 저장
			now += job[1];// 헤당 작업이 끝난 현재 시간 저장
		}

		return usage / jobs.length;
	}
}
