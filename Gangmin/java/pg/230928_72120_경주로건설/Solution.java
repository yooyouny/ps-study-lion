import java.util.LinkedList;
import java.util.Queue;


class Solution {
     int[] dx = {1, -1, 0 , 0};
    int[] dy = {0, 0, 1, -1};


    int[][][] visit;
    int mapBoundary;
    Queue<Point> pointQueue = new LinkedList<>();
    public int solution(int[][] board) {
        int answer = 0;
        mapBoundary = board.length;
        visit = new int[mapBoundary][mapBoundary][4];
        return bfs(new Point(-1, 0, 0, 0), board);
    }

    private int bfs(Point point, int[][] board){
        pointQueue.offer(point);
        int minCost = Integer.MAX_VALUE;
        while (!pointQueue.isEmpty()){
            Point current = pointQueue.poll();
            int cDirection = current.direction;
            int cCost = current.totalCost;
            int cX = current.x;
            int cY = current.y;
            if(cX == mapBoundary - 1 && cY == mapBoundary - 1){
                minCost = Math.min(minCost, cCost);
            }
            for (int newDir = 0; newDir < 4; newDir++) {
                int newX = cX + dx[newDir];
                int newY = cY + dy[newDir];
                if(isBoundaryOut(newX, newY) || isBarrier(newX, newY, board)) continue;
                int newCost = 0;
                if(newDir == cDirection || cDirection == -1) newCost = cCost + 100;
                else newCost = cCost + 600;
                if(visit[newX][newY][newDir] == 0 || board[newX][newY] >= newCost) {
                    pointQueue.offer(new Point(newDir, newCost, newX, newY));
                    visit[newX][newY][newDir] = 1;
                    board[newX][newY] = newCost;
                }
            }
        }
        return minCost;
    }


    private boolean isBoundaryOut(int x, int y){
        if(x < 0 || x >= mapBoundary || y < 0 || y >= mapBoundary) return true;
        return false;
    }
    private boolean isBarrier(int x, int y, int[][] board){
        if(board[x][y] == 1) return true;
        return false;
    }

    static class Point{
        int direction;
        int totalCost;
        int x;
        int y;

        public Point(int direction, int totalCost, int x, int y) {
            this.direction = direction;
            this.totalCost = totalCost;
            this.x = x;
            this.y = y;
        }
    }
}
