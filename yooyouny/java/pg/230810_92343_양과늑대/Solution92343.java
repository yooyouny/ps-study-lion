import java.util.*;

class Solution92343 {
    public int solution(int[] info, int[][] edges) {
        int numNodes = info.length;
        boolean[][] tree = new boolean[numNodes][numNodes];

        for (int[] edge : edges) {// 노드 연결정보를 저장하는 인접행렬
            int parent = edge[0];
            int child = edge[1];
            tree[parent][child] = true;
        }

        Set<Integer> visited = new HashSet<>();// 현재까지의 방문한 노드들을 저장, 순서상관없으므로 중복제거하는 set 사용
        visited.add(0); // 루트부터 탐색 시작

        return getMaxSheepCnt(visited, tree, info, 0, 0);
    }

    private int getMaxSheepCnt(Set<Integer> visited, boolean[][] tree, int[] info, int sheep, int wolf) {
        int answer = 0;

        for (int node : visited) {
            // 현재노드의 info정보에 따라 wolf, sheep 개수 증가
            int nextSheep = sheep + (info[node] == 0 ? 1 : 0);
            int nextWolf = wolf + (info[node] == 1 ? 1 : 0);

            if (nextWolf >= nextSheep) {// 늑대 수가 많아지면 탐색 중단하고 다음 노드 탐색
                continue;
            }

            //다음 연결 노드 탐색을 위한 새 경로 저장
            Set<Integer> nextVisited = new HashSet<>(visited);// 이전 탐색에 영향을 주지 않도록 new visited 생성
            nextVisited.remove(node);// 이미 방문했던 노드는 탐색에서 제외

            for (int next = 0; next < tree.length; next++) {
                if (tree[node][next]) {// 현재 노드와 연결된 노드들을 경로에 저장
                    nextVisited.add(next);
                }
            }
            // 현재 양의 갯수와 새로운 경로로 구한 양의 갯수 중 최댓값을 저장
            answer = Math.max(getMaxSheepCnt(nextVisited, tree, info, nextSheep, nextWolf), answer);
        }

        return answer;
    }
}