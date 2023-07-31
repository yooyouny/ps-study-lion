import java.util.*;
class Solution {
      static boolean[] visited;
    static Queue<Integer> queue = new LinkedList<>();
    public int solution(int n, int[][] computers) {
    //중복 방문 제거
        visited = new boolean[n];
        int answer = 0;


        for (int i = 0; i < computers.length; i++) {
            //방문한 노드는 볼필요 없음
            if(visited[i]) continue;
            queue.offer(i);
            //while문을 돌때마다 하나의 네트워크를 찾는다.        
            while (!queue.isEmpty()){
                Integer node = queue.poll();
                visited[node] = true;
                for (int j = 0; j < n; j++) {
                    if(computers[node][j] == 1 && !visited[j]){
                        queue.offer(j);
                    }
                }
            }
            answer++;
        }
        return answer;
    }


}
