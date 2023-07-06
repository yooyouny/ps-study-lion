package java.pg._230704_42861_섬연결하기;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/42861
//신규_프로그래머스_lv3_섬연결하기
//Kruskal 알고리즘
public class Solution_42861 {
    int[] island;
    public int solution(int n, int[][] costs) {

        // i 번 째 섬이 연결된 섬 = i (본인)
        island = new int[n];
        for (int i = 0; i < n; i++) {
            island[i] = i;
        }
        //간선길이가 작은 걸 먼저 사용하도록하여 최소값을 구하도록 한다.
        Arrays.sort(costs, Comparator.comparingInt(c -> c[2]));

        int picked = 0; // 선택한 다리의 개수 N-1이면 BREAK; 모든 섬이 연결되었다는 뜻
        int totalLength = 0; // 모든 섬은 연결하는 데에 들어가는 비용
        List<String> pickedBridges = new ArrayList<>(); //연결할 때 사용한 다리의 정보
        for (int i = 0; i < costs.length; i++) {
            int start = costs[i][0];    //A에서
            int end = costs[i][1];      //B로 연결된 다리

            //START 섬과 END 섬은 연결되지 않은 섬이라는 의미
            if (findSet(start) != findSet(end)) {
                // 두 원소를 하나의 집합으로
                union(start, end);
                // 간선을 골랐음을 표시
                picked++;
                // 총 가중치 업데이트
                totalLength += costs[i][2];
                pickedBridges.add(Arrays.toString(costs[i]));
            }
            if (picked == n - 1) break;
        }
        return totalLength;
    }

    // [ A - B - node1 - D ] [ A - C - node2 ]
    // return A             return A
    // 같은 tree 상에서 연결된 경우 이므로 더이상 연결할 필요 x
    // [ node2 - C - A - B - node1 - D ]
    public int findSet(int node) {
        if (island[node] != node)
            return findSet(island[node]);
        else return island[node];
    }

    // X [A - B - C]    Y [E - D]
    // [A - B - C - E - D]
    public void union(int x, int y) {
        island[findSet(y)] = findSet(x);
    }
}
