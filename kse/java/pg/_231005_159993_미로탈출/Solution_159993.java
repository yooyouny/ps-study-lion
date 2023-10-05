package java.pg._231005_159993_미로탈출;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/159993
//신규_프로그래머스_lv2_159993_미로탈출
public class Solution_159993 {
    int[] directY = new int[]{1, -1, 0, 0};
    int[] directX = new int[]{0, 0, -1, 1};
    /**
     * S : 시작 지점
     * E : 출구
     * L : 레버
     * O : 통로
     * X : 벽
     */
    public int solution(String[] maps) {
        //maps char 2차원 배열로 바꿔주기
        char[][] map = Arrays.stream(maps).map(String::toCharArray).toArray(char[][]::new);

        //시작, 탈출, 레버 좌표 찾기
        int startX = 0, startY = 0;
        int exitX  = 0, exitY  = 0;
        int leverX = 0, leverY = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                switch (map[i][j]){
                    case 'S' -> {
                        startY = i;
                        startX = j;
                    }
                    case 'E' -> {
                        exitY = i;
                        exitX = j;
                    }
                    case 'L' -> {
                        leverY = i;
                        leverX = j;
                    }
                }
            }
        }
        //시작 -> 레버까지 최단거리 구하기
        int goToLever = maze(map, startY, startX, leverY, leverX);
        //레버 -> 탈출까지 최단거리 구하기
        int goToExit  = maze(map, leverY, leverX, exitY, exitX);

        //둘 중 하나라도 -1 이면 실패이므로 -1
        return (goToLever == -1 || goToExit == -1 ) ? -1 : goToLever + goToExit;
    }

    //BFS
    public int maze(char[][] map, int startY, int startX, int endY, int endX){

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startY, startX, 0});

        //방문 했는지 확인하기 위한 boolean 배열
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int dy = current[0];
            int dx = current[1];
            int steps = current[2];

            //좌표가 end 인 경우 return step
            if (dy == endY && dx == endX) return steps;

            for (int i = 0; i < 4; i++) {
                int nowY = dy + directY[i];
                int nowX = dx + directX[i];

                //방문하지 않았으며 벽이 아닌 경우 큐에 추가
                if(nowY >= 0 && nowY < map.length && nowX >= 0 && nowX < map[0].length && !visited[nowY][nowX] && map[dy][dx] != 'X'){
                    queue.offer(new int[]{nowY, nowX, steps + 1});
                    visited[nowY][nowX] = true;
                }
            }
        }
        return -1;
    }
}
