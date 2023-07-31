class Solution43162 {
    public static void dfs(int i, int n, int[][] computers, boolean[] visited){
        visited[i] = true;// 방문 체크
        for(int j=0; j<n; j++){
            if(computers[i][j] == 1 && !visited[j]){// 연결되어있고 기존에 방문했던 노드가 아니면
                dfs(j, n, computers, visited);// 해당 노드 기준으로 연결 된 노드 탐색
            }
        }
    }
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];// 방문 노드 체크
        int answer = 0;// 같은 집합의 개수
        for(int i=0; i<n; i++){
            if(!visited[i]){//방문하지 않은 노드면 새로운 집합
                dfs(i, n, computers, visited);// 연결된 집합 체크
                answer++;// i노드와 연결된 노드들을 하나의 집합으로 간주
            }
        }
        return answer;
    }

}