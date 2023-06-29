package java.pg._230628_42627_디스크컨트롤러;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/42627
//신규_프로그래머스_lv3_디스크컨트롤러
public class Solution_42627 {
	//작업의 요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법
	/**
	 * 1. 모든 작업들을 시작 시간을 기준으로 하는 큐 disk 에 넣어준다.
	 * 2. 마지막 작업이 끝나는 시점인 endtime 보다 시작시간이 같거나 빠른 작업을
	 *    작업시간을 기준으로 하는 ready 큐에 넣어준다.
	 * 3. ready 큐에서 가장 빨리 끝날 수 있는 다음 작업(temp)을 가져와 실행한다.
	 *    endtime 을 temp 작업이 끝나는 시간으로 맞춰준다.
	 * 4. 작업을 진행할 떄마다 answer 에 temp 의 대기시간과 작업시간을 넣어준다.
	 * 5. 이 작업을 disk 와 ready 큐가 빌 때까지 반복해준다.
	 */
	public int solution(int[][] jobs) {
		//시작 시간을 기준으로 하는 우선순위 큐
		PriorityQueue<int[]> disk = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
		disk.addAll(Arrays.asList(jobs));

		//작업 시간을 기준으로 하는 큐
		PriorityQueue<int[]> ready = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));


		int answer = 0;
		int endTime = 0;	//마지막 작업이 끝나는 시간
		while (!disk.isEmpty() || !ready.isEmpty()){
			//다음 작업의 시작 시간이 이전 작업이 끝나는 시간보다 빠른 경우 ready 큐에 넣어준다.
			while(!disk.isEmpty() && endTime >= disk.peek()[0]){
				ready.add(disk.poll());
			}
			//ready큐가 비어있지 않다면 ready에서 가장 짧은 작업시간을 가진 작업을 시작해준다.
			if(!ready.isEmpty()){
				int[] temp = ready.poll();
				// 대기시간(endTime - temp[0])과 작업시간(temp[1])을 넣어준다.
				answer += endTime - temp[0] + temp[1];
				endTime += temp[1];
			}else{
				//새로 들어간 값이 없는데 while 문으로 넘어왔다 -> disk에 값이 남아있다.
				endTime = disk.peek()[0];
			}

		}
		return answer / jobs.length;

	}
	public void printQueue(Queue<int[]> disk){
		for(int[] job : disk){
			System.out.println(Arrays.toString(job));
		}
		System.out.println("---------------");
	}
}