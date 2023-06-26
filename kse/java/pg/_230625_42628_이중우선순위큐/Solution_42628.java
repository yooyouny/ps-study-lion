package java.pg._230625_42628_이중우선순위큐;

import java.util.*;

import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.Tree;

//https://school.programmers.co.kr/learn/courses/30/lessons/42628
//신규_프로그래머스_lv3_이중우선순위큐
public class Solution_42628 {
	/**
	 * 1. 우선순위 큐를 2개 만들어 최대값 최소값을 빼기 쉽도록 한다.
	 *
	 * 2. I 명령어가 들어오면 해당값을 모든 큐에 넣어준다.
	 *
	 * 3. D 명령어가 들어오면 입력값에 따라
	 *     1이면 maxqueue의 값을 반환해주고 minqueue에서 같은 값을 제거해준다.
	 *    -1이면 minqueue의 값을 반환해주고 maxqueue에서 같은 값을 제거해준다.
	 *
	 * 4. 큐가 비어있지 않다면 최댓값 최소값을 반환하여 return 해주고
	 *    비어있다면 0,0을 return 해준다.
	 *
	 * @param operations 명령어 모음
	 * @return 큐 안에 남아있는 최대값 최소값
	 */
	public int[] solution(String[] operations) {
		Queue<Integer> maxqueue = new PriorityQueue<>(Comparator.reverseOrder()); //최대값이 앞에 오는 큐
		Queue<Integer> minqueue = new PriorityQueue<>(); //최소값이 앞에 오는 큐

		for(String operation : operations){
			String[] op = operation.split(" ");
			int value   = Integer.parseInt(op[1]);
			//명령어가 I인 경우
			if(op[0].equals("I")) {
				maxqueue.offer(value);
				minqueue.offer(value);
			}
			//명령어가 D이며, 큐가 비어있지 않은 경우
			else if (!maxqueue.isEmpty()){
				int temp;
				if(value >= 0) {
					temp = maxqueue.poll();
					minqueue.remove(temp);
				} else {
					temp = minqueue.poll();
					maxqueue.remove(temp);
				}
			}
		}
		if(!maxqueue.isEmpty())
			return new int[] {maxqueue.poll(), minqueue.poll()};

		return new int[] {0,0};
	}
}
