import java.util.*;
class Solution169199 {
    static int answer = Integer.MAX_VALUE;
    static int[] dr = {-1, 1, 0, 0};// 상하좌우
    static int[] dc = {0, 0, -1, 1};
    static int R, C;
    public int solution(String[] board) {
        R = board.length;
        C = board[0].length();
        char[][] map = new char[R][C];
        boolean[][] visited = new boolean[R][C];
        int startR = 0;
        int startC = 0;

        //시작좌표 저장 및 char배열로 변환
        for(int i=0; i<R; i++){
            map[i] = board[i].toCharArray();
            for(int j=0; j<C; j++){
                if(map[i][j] == 'R'){
                    startR = i;
                    startC = j;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();// 시작한 좌표로부터 이동횟수+1씩 진행해야하기 때문에 bfs로 진행
        queue.add(new int[]{startR, startC, 0});// 시작지점과 이동횟수 저장

        while(!queue.isEmpty()){
            int[] before = queue.poll();
            int r = before[0];
            int c = before[1];
            int m = before[2];
            if(map[r][c] == 'G'){// 목적지를 찾으면 최소값 갱신하고 리턴
                answer = Math.min(answer, m);
                break;
            }
            for(int i=0; i<4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr >= R || nc >= C || nr < 0 || nc < 0) continue;
                if(map[nr][nc] == 'D') continue;

                // 현재 방향 i의 끝까지 이동 가능(D가 나올경우 D직전까지 가능)
                while(nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != 'D') {
                    nr += dr[i];// 방향은 유지한채로 nr과 nc의 끝까지 이동
                    nc += dc[i];
                }
                nr -= dr[i];// while문을 벗어나오기 전에 더 증가했으므로 했으므로 감소해줌
                nc -= dc[i];

                if(visited[nr][nc]) continue;// 이동할 다음 좌표가 방문처리 되어있으면 다른 방향으로 탐색
                visited[nr][nc] = true;// 방문체크
                queue.add(new int[]{nr, nc, m+1});// 이동횟수 증가하며 다음 좌표로부터 목적지를 찾기위해 큐에 add
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}