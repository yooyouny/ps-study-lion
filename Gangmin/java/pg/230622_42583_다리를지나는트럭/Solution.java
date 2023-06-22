
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static Queue<Integer> queue = new LinkedList<>();
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		answer = passBridge(bridge_length, weight, truck_weights);
		return answer;
	}

	public static int passBridge(int maxTruck, int maxWeight, int[] trucks){
		int sum = 0;
		int time = 0;
		for (int i = 0; i < trucks.length; i++) {
			int truck = trucks[i];
			while(true){
				//도로 위에 아무 것도 없는 경우
				if(queue.isEmpty()){
					//도로에 차를 올린다.
					queue.add(truck);
					//도로에 차를 올린 만큼 무게 증가
					sum += truck;
					//차가 올라갔으니 시간 증가
					time++;
					break;
					//도로가 차로 가득찬 경우
					//가장 먼저 들어간 트럭이 다리의 끝에 도달했다는 의미
				} else if (queue.size() == maxTruck) {
					//차를 내린다. 동시에 weight 감소
					sum -= queue.poll();
					// 트럭이 최대 다리 길이 만큼 차지 않았다.
					// 먼저 들어간 트럭이 아직 다리의 끝에 도달하지 않은 상태
					// 최대 무게를 넘지 않는 선에서 트럭을 올린다.
				} else {
					if(sum + truck <= maxWeight){
						queue.add(truck);
						sum += truck;
						time++;
						break;
					} else {
						/*무게가 넘는다면, 0을 넣어서 이미 도로에 있는 트럭
						* 을 전진시켜준다.
						* 만약 maxWeight만큼의 truck이 있다면 계속
						* 0이 들어가면서 while문을 반복하고 마지막 트럭이
						* 도르를 나간다음 while문 반복에서 차가 들어감*/
						queue.add(0);
						time++;
					}
				}
			}
			//마지막 트럭은 현재 시간에서 다리 길이 만큼 건너야 하기 때문에
			//maxTruck만큼 시간을 더해 준다.

		}
		return time + maxTruck;
	}

	public static void main(String[] args) {
		int[] trucks = {7, 4, 5, 6};
		System.out.println(solution(2, 10, trucks));
	}
}
