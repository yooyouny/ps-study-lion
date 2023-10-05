import java.util.*;
class Solution159993 {
    class Point{
        int r;
        int c;
        int time;
        public Point(){}
        public Point(int r, int c, int time){// 이동좌표에 따라 time값이 증가하므로 함께 저장
            this.r = r;
            this.c = c;
            this.time = time;
        }
        public void setPoint(Point p, int r, int c, int time){
            p.r = r;
            p.c = c;
            p.time = time;
        }

    }
    static char[][] maze;
    public int solution(String[] maps) {
        int R = maps.length;
        int C = maps[0].length();
        maze = new char[R][C];

        Point start = new Point();
        Point exit = new Point();
        Point lever = new Point();
        init(maps, start, exit, lever);// 출발지점, 도착지점, 레버지점 init

        int[] dr = {0, 0, -1, 1};
        int[] dc = {-1, 1, 0, 0};

        Queue<Point> queue = new LinkedList<>();// 최소경로를 찾아야하기 때문에 bfs 활용
        boolean[][] visited = new boolean[R][C];// 방문경로 체크해서 NPE 막기
        queue.add(start);// 시작지점에서 출발
        visited[start.r][start.c] = true;// 큐에 넣었으니 방문 표시

        boolean on = false;// 레버에 도착했는지 체크
        int answer = Integer.MAX_VALUE;

        while(!queue.isEmpty()){
            Point get = queue.poll();
            int r = get.r;
            int c = get.c;
            int time = get.time;
            if(r == lever.r && c == lever.c){// 레버를 찾은 경우
                answer = time;// exit 찾을떄 현재까지의 time 반영하기 위해 저장
                on = true;// 레버 찾았다는 표시
                break;// 찾았으면 더이상 탐색하지 않아도 되므로 종료
            }
            for(int i=0; i<4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                if(maze[nr][nc] == 'X' || visited[nr][nc]) continue;// 벽이거나 이미 방문한 좌표면 제외
                visited[nr][nc] = true;
                queue.add(new Point(nr, nc, time + 1));// 이전 좌표에서 현재 좌표까지 + 1 이동했으므로 time 증가
            }
        }

        if(!on) return -1;// 레버를 찾지 못하고 반복문을 빠져나올 경우, 레버를 발견 못하면 실패로 종료처리

        queue = new LinkedList<>();// 이전 큐에 남아있는 좌표들을 리셋처리
        queue.add(new Point(lever.r, lever.c, answer));// 레버 위치에서 시작
        visited = new boolean[R][C];// exit까지 가기 위해 이전에 방문했던 경로도 리셋
        visited[lever.r][lever.c] = true;// 레버 위치 방문체크

        while(!queue.isEmpty()){
            Point get = queue.poll();
            int r = get.r;
            int c = get.c;
            int time = get.time;
            if(r == exit.r && c == exit.c){// 탈출경로를 찾을 경우 그동안 증가해온 시간 리턴
                return time;
            }
            for(int i=0; i<4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                if(maze[nr][nc] == 'X' || visited[nr][nc]) continue;// 벽이거나 이미 방문한 좌표면 제외
                visited[nr][nc] = true;
                queue.add(new Point(nr, nc, time + 1));
            }
        }

        return -1;// return을 못하고 빠져나온 경우면 탈출구를 찾지 못한 경우기 때문에 실패 처리
    }
    private void init(String[] maps, Point start, Point exit, Point lever){
        for(int i=0; i<maps.length; i++){
            maze[i] = maps[i].toCharArray();// char 배열 바로 넣어주기
            for(int j=0; j<maze[i].length; j++){
                switch(maze[i][j]){
                    case 'S' -> {
                        start.setPoint(start, i, j, 0);
                    }
                    case 'E' -> {
                        exit.setPoint(exit, i, j, 0);
                    }
                    case 'L' -> {
                        lever.setPoint(lever, i, j, 0);
                    }
                    default -> {}
                }
            }
        }
    }
}