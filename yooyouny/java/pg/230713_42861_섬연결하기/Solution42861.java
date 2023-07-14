import java.util.*;
class Solution {
	static int[] parent;
	public void union(int a, int b){
		a = find(a);
		b = find(b);
		if(a == b)// 루트노드가 같으면 이미 같은 집합이므로 리턴
			return;
		else{
			if(a < b)// 더 작은 루트노드로 합침
				parent[b] = a;
			else
				parent[a] = b;
		}
	}
	public int find(int num){// 해당 노드의 루트 노드 찾기
		if(parent[num] == num)// 해당 노드가 루트 노드인 경우
			return num;
		parent[num] = find(parent[num]);// 해당 노드의 루트 노드를 찾아 부모노드 저장(경로압축)
		return parent[num];// 해당 노드의 부모노드 값 리턴
	}
	public int solution(int n, int[][] costs) {
		int answer = 0;// 모든 노드를 연결하는 가장 최소 비용

		parent = new int[n];// 같은 부모에 연결되어있는 상태를 확인하기 위한 배열
		for(int i=0; i<n; i++)
			parent[i] = i;// 초깃값으로 자기 자신을 가리키도록 넣어줌

		costs = Arrays.stream(costs)
			.sorted(Comparator.comparingInt(cost -> cost[2]))//모든 노드의 간선 비용이 최소가 되어야 하므로
			.toArray(int[][] :: new);

		for(int[] cost : costs){
			if(find(cost[0]) == find(cost[1]))// 이미 같은 집합(부모가 같으면)이면 최소의 비용으로 더해졌을테니 패스
				continue;
			union(cost[0], cost[1]);// 연결이 안된 경우면 연결 시켜주고
			answer += cost[2];// 현재 cost가 가장 최소의 비용이므로 더해줌
		}
		return answer;
	}
}
