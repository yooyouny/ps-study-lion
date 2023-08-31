import java.util.*;
class Solution {
      int[] dNormalX={1, -1, 0, 0};
    int[] dNormalY = {0, 0, 1, -1};
    int[] dMagicX = {2, -2, 0, 0};
    int[] dMagicY = {0, 0, 2, -2};

    //n이 가로길이, m이 세로 길이
    public int solution(int n, int m, int[][] hole) {
        //시작 점 (문제의 1,1)
        int startX = m;
        int startY = 1;
        //끝 점 (문제의 n,m)
        int endX = 1;
        int endY = n;
        //방문 체크 1이면, 한칸 이동해 도달한 경우, 0이면, 신발을 써서 도달한 경우
        boolean[][][] visited = new boolean[m + 1][n + 1][2];
        //함정 체크를 위한 MAP
        boolean[][] Map = new boolean[m + 1][n + 1];
        //함정 삽입
        for (int i = 0; i < hole.length; i++) {
            int x = m - hole[i][1] + 1;
            int y = hole[i][0];
            Map[x][y] = true;
        }
        //BFS를 위한 큐
        Queue<Node> queue = new LinkedList<>();
        //첫번째 시작점
        queue.offer(new Node(0, startX, startY, 1));
        visited[startX][startY][1] = true;
        int mininumTime = Integer.MAX_VALUE;
        //BFS시작
        while (!queue.isEmpty()){
            //방문한 노드
            Node nodeVisited = queue.poll();
            //끝 점에 도달한 경우, 최소 시간 비교
            if(nodeVisited.x == endX && nodeVisited.y == endY){
                if(mininumTime > nodeVisited.time){
                    mininumTime = nodeVisited.time;
                }
            }
            //한칸 씩 이동하는 경우
            for (int i = 0; i < 4; i++) {
                int nNormalX = nodeVisited.x + dNormalX[i];
                int nNormalY = nodeVisited.y + dNormalY[i];
                //TODO: 경계을 벗어나거나, 함정이거나, 방문한적이 있으면 경우의수에서 제거
                if(!checkBoundary(nNormalX, nNormalY, m, n) || visited[nNormalX][nNormalY][nodeVisited.count] || Map[nNormalX][nNormalY]){
                    continue;
                }
                //TODO: 방문 가능하면 Queue에 넣기
                visited[nNormalX][nNormalY][nodeVisited.count] = true;
                queue.offer(new Node(nodeVisited.time + 1, nNormalX, nNormalY, nodeVisited.count));
            }
            //아직 신발을 사용하지 않았다면 2칸씩 이동하는 경우
            if(nodeVisited.count > 0){
                for (int i = 0; i < 4; i++) {
                    int nMagicX = nodeVisited.x + dMagicX[i];
                    int nMagicY = nodeVisited.y + dMagicY[i];
                    if(!checkBoundary(nMagicX, nMagicY, m, n) || visited[nMagicX][nMagicY][nodeVisited.count - 1] || Map[nMagicX][nMagicY]){
                        continue;
                    }
                    visited[nMagicX][nMagicY][nodeVisited.count - 1] = true;
                    queue.offer(new Node(nodeVisited.time + 1, nMagicX, nMagicY, nodeVisited.count - 1));
                }
            }
        }
        //끝났는데 mini가 갱신이 되지 않았다면 -1
        return mininumTime == Integer.MAX_VALUE ? -1 : mininumTime;
    }

    //BFS 경계 체크 로직
    private boolean checkBoundary(int x, int y, int m, int n){
        if(x < 1 || x > m || y < 1 || y > n){
            return false;
        }
        return true;
    }

    //방문 노드
    static class Node{
        int time;
        int x;
        int y;
        int count;

        public Node(int time, int x, int y, int count) {
            this.time = time;
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
