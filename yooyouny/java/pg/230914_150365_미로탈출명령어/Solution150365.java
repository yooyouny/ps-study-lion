import java.util.*;
import java.lang.*;
class Solution150365 {
    static int[][] maze;
    String answer = "impossible";
    StringBuilder sb;
    char[] ds = {'d', 'l', 'r', 'u'};// 사전순으로 가장 빠른 경로 찾기
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    boolean success;
    int endX, endY;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        sb = new StringBuilder();
        maze = new int[n][m];
        endX = r; endY = c;

        int len = getDistance(x, y);
        if((k - len) % 2 == 1) return answer;// 시작점에서 목표지점까지의 거리가 짝수여야함

        dfs(x, y, 0, k, n, m);

        return answer;
    }

    private void dfs(int x, int y, int len, int k, int n, int m){
        if(success) return;
        if(len + getDistance(x, y) > k) return; // 현재방문길이 + 목표지점까지의 거리가 k보다 작거나 같아야하므로
        if(k == len) {// 목표 이동거리와 같으면
            answer = sb.toString();// 방문한 문자를 문자열로 변환해서 저장
            success = true;// 처음으로 갱신된 값이 최단거리 경로이므로 더이상 탐색을 안하기 위해 success 값 저장
            return;
        }
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx <= n && ny <= m && nx > 0 && ny >0){// 경계 안이면
                sb.append(ds[i]);// 이동좌표의 값 넣고
                dfs(nx, ny, len+1, k, n, m);
                sb.delete(len, len+1);// 이동 좌표의 탐색이 끝나면 재탐색을 위해 해당 위치의 값을 삭제함
            }
        }
    }
    private int getDistance(int x, int y){
        return Math.abs(x-endX) + Math.abs(y-endY);
    }
}