import java.util.*;
class Solution1844_2 {
    public int solution(int[][] maps) {
        Queue<int[]> queue = new LinkedList<>();
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int n = maps.length - 1;
        int m = maps[0].length - 1;

        queue.offer(new int[]{0, 0});

        while(!queue.isEmpty()){
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && ny >= 0 && nx<=n && ny<=m && maps[nx][ny] == 1){ // visited 배열 없이 시작점 1, 1을 3으로 바꿔도 문제 없음
                    maps[nx][ny] = maps[x][y] + 1;// 좌표 값을 갱신해도 답 구하는데 문제 없음
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return maps[n][m] == 1 ? -1 : maps[n][m];
    }
}