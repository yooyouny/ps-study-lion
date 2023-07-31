import java.util.*;

class Solution {
    
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    
    public int solution(int[][] maps) {
        int answer = 0;
        //무한 반복을 안하기 위해 visited
        //거리를 재는 용도도 있음
        int[][] visited = new int[maps.length][maps[0].length];
        
        bfs(maps, visited);
        //해당 지점까지 도착했으면 visited에 최단거리의 값이 기록되어있다.
        answer = visited[maps.length-1][maps[0].length-1];
        //방문하지 못한경우는 vistited에 값이 기록되어있지 않기 때문에 -1반환
        if(answer == 0){
            answer = -1;
        }
        
        return answer;
    }
    
    public void bfs(int[][] maps, int[][] visited){
        int x = 0;
        int y = 0;
        //첫번째 방문 노드는 일단 1
        visited[x][y] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        //결국 최단거리에 도달하면 나머지는 값을 visited에 기록하지 못하고 끝나게 되어있다
        while(!queue.isEmpty()){
            //이번턴에 방문 노드
            int[] current = queue.remove();
            int cX = current[0];
            int cY = current[1];
            
            for(int i = 0; i < 4; i++){
                //다음 방문 노드를 동서남북으로
                int nX = cX + dx[i];
                int nY = cY + dy[i];
                //조건에 안맞으면 continue
                if(nX < 0 || nX > maps.length-1 || nY < 0 || nY > maps[0].length-1)
                    continue;
                //조건에 맞으면 넣는다.
                if(visited[nX][nY] == 0 && maps[nX][nY] == 1){
                    //기존에 온 거리에 1을 더해주면 cX, cY까지 온거리
                    visited[nX][nY] = visited[cX][cY] + 1;
                    queue.add(new int[]{nX, nY});
                }
            }
            
        }
        
        
    }
}
