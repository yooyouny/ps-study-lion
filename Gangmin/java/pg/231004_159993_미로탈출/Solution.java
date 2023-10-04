import java.util.LinkedList;
import java.util.Queue;

class Solution {
 //이동좌표와 거리를 저장할 클래스
    static class Point{
        int x;
        int y;
        int distance;

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
    //Map의 Index
    int rowLimit;
    int colLimit;
    //bfs
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};


    public int solution(String[] maps) {
        rowLimit = maps.length - 1;
        colLimit = maps[0].length() - 1;
        Point start = findTarget(maps, 'S');
        Point lever = findTarget(maps, 'L');
        int distanceToLever = bfs(maps, start, 'L');
        if(distanceToLever == Integer.MAX_VALUE) return -1;
        int distanceToExit = bfs(maps, lever, 'E');
        if(distanceToExit == Integer.MAX_VALUE) return -1;
        return distanceToLever + distanceToExit;
    }

    private int bfs(String[]maps, Point start, char target){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rowLimit + 1][colLimit + 1];
        int count = Integer.MAX_VALUE;
        queue.offer(start);
        while (!queue.isEmpty()){
            Point toVisit = queue.poll();
            int cX = toVisit.x;
            int cY = toVisit.y;
            int cDis = toVisit.distance;
            if(maps[cX].charAt(cY) == target){
                count = Math.min(cDis, count);
            }
            for (int i = 0; i < 4; i++) {
                //다음 방문 노드
                int nX = cX + dx[i];
                int nY = cY + dy[i];
                int nDis = cDis + 1;
                if(outBoundary(nX, nY) || isWall(nX, nY, maps)) continue;
                if(!visited[nX][nY]){
                    queue.offer(new Point(nX, nY, nDis));
                    visited[nX][nY] = true;
                }
            }
        }
        return count;
    }


    private Point findTarget(String[] maps, char target){
        Point start = null;
        for (int i = 0; i <= rowLimit; i++) {
            for (int j = 0; j <= colLimit; j++) {
                if(maps[i].charAt(j) == target)
                    start = new Point(i, j, 0);
            }
        }
        return start;
    }

    //TODO: 벽인 경우 확인
    private boolean isWall(int x, int y, String[] maps){
        if(maps[x].charAt(y) == 'X')
            return true;
        return false;
    }

    //TODO: 레버인 경우
    private boolean isLever(int x, int y, String[] maps){
        if(maps[x].charAt(y) == 'L') return true;
        return false;
    }
    //TODO: 경계를 넘어서는 경우 확인
    private boolean outBoundary(int x, int y){
        if(x < 0 || x > rowLimit || y < 0 || y > colLimit) return true;
        return false;
    }
}
