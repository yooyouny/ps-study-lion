import java.util.*;
class Solution67259 {
    class Point{
        int r;
        int c;
        int d;
        int cost;
        public Point(int r, int c, int d, int cost){
            this.r = r;
            this.c = c;
            this.d = d;
            this.cost = cost;
        }
    }
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        int N = board.length;

        Queue<Point> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][N][4];// 도달한 칸으로 들어온 방향에 따른 최솟값을 구해야하므로 3차원 배열로 체크
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0 ,-1};

        queue.add(new Point(0, 0, -1, 0));


        while(!queue.isEmpty()){
            Point point = queue.poll();
            int r = point.r;
            int c = point.c;
            int d = point.d;
            int nowCost = point.cost;

            if(r == N-1 && c == N-1){
                answer = Math.min(answer, nowCost);
                //최소비용 && 모든 가능한 경로를 탐색해야 하기 때문에 break 하지 않음
            }

            for(int i=0; i<4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                int nd = i;
                int newCost = nowCost;// 현재 비용을 꺼내서

                if(0 <= nr && nr < N && 0 <= nc && nc < N && board[nr][nc] != 1){
                    if(nd == d || d == -1){// 이전 방향과 같은 방향이거나 초기 좌표에서 이동인거면 비용 100원
                        newCost += 100;
                    }else {// 방향이 달라서 꺾어지는 경우 비용 600
                        newCost += 600;
                    }
                    if(!visited[nr][nc][nd] || board[nr][nc] >= newCost){// 방문하지 않은 길이거나 방문했더라도 지금 비용이 최소비용이면 갱신해줘야함
                        queue.add(new Point(nr, nc, nd, newCost));
                        visited[nr][nc][nd] = true;
                        board[nr][nc] = newCost;
                    }
                }
            }
        }

        return answer;
    }
}