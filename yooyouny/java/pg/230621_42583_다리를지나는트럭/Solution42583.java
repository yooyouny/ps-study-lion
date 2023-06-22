import java.util.*;
class Solution42583 {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		int time = 0, bridgeWeight = 0;// 문제에서 요구하는 time과 weight 체크를 위한 변수 할당
		Queue<Integer> queue = new LinkedList<>();// 선형구조의 다리

		for (int truck : truck_weights) {// 지나갈 대상 트럭을 순차적으로 접근

			while (true) {// overweight으로 대기가 발생할 경우 다음 트럭으로 접근하지 않고 처리해 주기 위해
				if (queue.isEmpty()) {// 다리에 트럭들이 없어서 대상 트럭을 지나가게 할 수 있는 경우
					queue.offer(truck);
					bridgeWeight += truck;
					time++;// queue에 넣어준 경우만 time 증가
					break;// 대상 트럭을 다리로 지나가게 했으면 다음 트럭에 접근해야하므로 break

				} else if (queue.size() == bridge_length) {// 다리가 꽉 차 대상트럭을 지나가게 할 수 없는 경우
					bridgeWeight -= queue.poll();

				} else { // 현재 다리에 지나가고 있는 트럭이 있을 경우
					if (bridgeWeight + truck > weight) { // 대상 트럭을 지나가게 하기 전 다리의 무게 체크
						queue.offer(0); // 트럭 대신 0을 큐에 넣어줌으로써 트럭의 진입을 대기시킴
						time++;// queue에 넣어준 경우만 시간 증가

					} else {// 대상 트럭을 지나가게 할 수 있는 경우
						queue.offer(truck);
						bridgeWeight += truck;
						time++;// queue에 넣어준 경우만 시간 증가
						break;// 대상 트럭을 다리로 지나가게 했으면 다음 트럭에 접근해야하므로 break
					}
				}
			}
		}
		//마지막으로 다리에 올라간 트럭은 아직 다리 위에 있음
		return time + bridge_length;// 다리에 트럭이 남아 있는 경우 해당 트럭들이 모두 빠져나오는 시간은 다리의 길이
	}
}
