package java.pg._230810_92343_양과늑대;
//https://school.programmers.co.kr/learn/courses/30/lessons/92343
//신규_프로그래머스_Lv3_92343_양과늑대
public class Solution_92343_2 {
    int maxSheepCnt = 0;

    public int solution(int[] info, int[][] edges) {
        //방문했는지 확인 하기 위한 boolean 배열
        boolean[] isVisit = new boolean[info.length];

        dfs(info, edges,  isVisit, 0,0, 0);

        return maxSheepCnt;
    }

    public void dfs(int[] info, int[][] edges, boolean[] visited,  int idx,int sheepCnt, int wolfCnt) {

        visited[idx] = true; // 방문 확인

        // info[idx]에 따라 양 || 늑대의 수를 늘려줌
        if (info[idx] == 0) sheepCnt++; else wolfCnt++;

        // 양의 수가 max 보다 많으면 바꿔준다.
        maxSheepCnt = Math.max(maxSheepCnt, sheepCnt);

        // 늑대의 수가 많아진 경우 return
        if (sheepCnt <= wolfCnt) return;

        // tree를 돌면서
        for (int[] edge : edges) {
            //현재 노드이면서, 방문하지 않은 노드를 탐색
            if (visited[edge[0]] && !visited[edge[1]]) {
                //boolean 배열 복사
                boolean[] nextVisited = new boolean[visited.length];
                System.arraycopy(visited, 0, nextVisited, 0, visited.length);

                dfs(info, edges, nextVisited, edge[1], sheepCnt, wolfCnt);
            }
        }
    }
}
