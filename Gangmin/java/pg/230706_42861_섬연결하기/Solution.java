import java.util.*;

class Solution {
    
	public int solution(int n, int[][] costs) {
		int answer = 0;
		int picked = 0;
		parent = new int[n];
		connectedIndex = new int[costs.length];
		initParent(parent);
        //최소비용을 return해야하기 때문에 정렬
		Arrays.sort(costs, Comparator.comparingInt(edges -> edges[2]));
		//가장 비용이 작은 노선 부터 잇는다.
        for (int i = 0; i < costs.length; i++) {
			int startNode = costs[i][0];
			int endNode = costs[i][1];
            //순환이 일어나지 않으면 두 집합을 합친다.
			if(findSet(startNode) != findSet(endNode)){
				union(startNode, endNode);
				connectedIndex[i] = costs[i][2];
				picked++;
			}

            //n-1개의 간선을 뽑으면 종료
			if (picked == n - 1){
				break;
			}
		}

		answer = Arrays.stream(connectedIndex).sum();
		return answer;
	}

    //이어진 edge를 기록하는 노선
	static int[] connectedIndex;
    //부모노드를 기록할 배열
    static int[] parent;


    //각 노드의 부모값을 초기화한다.
	public void initParent(int[] parent){
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
	}
    
    //집합의 부모를 찾는다.
	public int findSet(int node){
		if(parent[node] != node){
			return findSet(parent[node]);
		}else {
			return node;
		}
	}

    //집합을 합치는과정
	public void union(int mainGroup, int subGroup){
		//sub그룹의 부모가 maingroup의 부모가 되게 한다.
        parent[findSet(subGroup)] = findSet(mainGroup);
	}

	public static void main(String[] args) {
		Solution algo = new Solution();
		System.out.println(algo.solution(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1}, {2,3,8}}));
	}
}
