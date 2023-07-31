package java.pg._230731_1844_게임맵최단거리;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/1844
//신규_프로그래머스_Lv3_1844_게임맵최단거리
public class Solution_1844 {
    int answer = Integer.MAX_VALUE;
    public int solution(int[][] maps) {
        move(maps, 0, 0, 0);
        if(answer == Integer.MAX_VALUE) return -1;
        return answer;
    }
    public void move(int[][] map, int dx, int dy, int count) {
        if (dx >= map[0].length || dy >= map.length || dx < 0 || dy < 0 || map[dy][dx] == 0) {
            return;
        }

        if (dx == map[0].length - 1 && dy == map.length - 1) {
            answer = Math.min(answer, count + 1);
            return;
        }

        // 이 좌표의 길을 지났다는 표시를 해준다.
        map[dy][dx] = 0;

        move(copyMap(map), dx, dy - 1, count + 1); // 위쪽으로 이동
        move(copyMap(map), dx, dy + 1, count + 1); // 아래쪽으로 이동
        move(copyMap(map), dx - 1, dy, count + 1); // 왼쪽으로 이동
        move(copyMap(map), dx + 1, dy, count + 1); // 오른쪽으로 이동
    }

    public int[][] copyMap(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }
}
