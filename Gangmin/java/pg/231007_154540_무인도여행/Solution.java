import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    boolean[][] visited;
    Queue<int[]> queue = new LinkedList<>();
    int[] dx = {-1 ,1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    //경계 인덱스
    int rowLimit;
    int colLimit;
    //각 섬의 합을 저장할 List
    List<Integer> answer = new ArrayList<>();
    public int[] solution(String[] maps) {
        colLimit = maps[0].length() - 1;
        rowLimit = maps.length - 1;
        visited = new boolean[maps.length][maps[0].length()];
        for (int i = 0; i <= rowLimit; i++) {
            for (int j = 0; j <= colLimit; j++) {
                //숫자면 bfs
                if(!isSea(i, j, maps) && !visited[i][j]){
                    bfs(i, j, maps);
                }else {
                    //불필요한 확인을 줄이기 위한 'X'인 경우 visit 배열 갱신
                    visited[i][j] = true;
                }
            }
        }
        if(answer.isEmpty()) return new int[]{-1};
        Collections.sort(answer);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private void bfs(int startY, int startX, String[] maps){
        int total = 0;
        queue.offer(new int[]{startY, startX});
        visited[startY][startX] = true;
        //붙어있는 섬인 경우 total 합산
        total += Integer.parseInt(String.valueOf(maps[startY].charAt(startX)));
        while (!queue.isEmpty()){
            int[] toVisit = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nY = toVisit[0] + dy[i];
                int nX = toVisit[1] + dx[i];
                if(outBoundary(nY, nX) || isSea(nY, nX, maps)) continue;
                if(!visited[nY][nX]){
                    queue.offer(new int[]{nY, nX});
                    visited[nY][nX] = true;
                    //마찬가지로, 합산
                    total+= Integer.parseInt(String.valueOf(maps[nY].charAt(nX)));
                }
            }
        }
        //최종 합산 결과 answer에 저장
        answer.add(total);
    }


    private boolean outBoundary(int y, int x){
        if(y < 0 || y > rowLimit || x < 0 || x > colLimit) return true;
        return false;
    }
    private boolean isSea(int y, int x, String[] maps){
        if(maps[y].charAt(x) == 'X') {
            visited[y][x] = true;
            return true;
        }
        return false;
    }
}
