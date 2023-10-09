package java.pg._231009_154540_무인도여행;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/154540
//신규_프로그래머스_lv2_154540_무인도여행
public class Solution_154540 {
    char[][] map;           // 지도를 char 형으로 변환
    boolean[][] visited;    // 방문 여부 체크를 위한 배열
    int[] directY = new int[]{1, -1, 0, 0}; //좌표 이동 시의 변화값 저장
    int[] directX = new int[]{0, 0, -1, 1};
    int ROW, COL;   //가로 세로 길이
    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        ROW = maps.length;
        COL = maps[0].length();

        map = Arrays.stream(maps).map(String::toCharArray).toArray(char[][]::new);
        visited = new boolean[ROW][COL];

        // map 에서 벽이 아니고 방문한 적이 없는 경우 탐색을 시작
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if(map[i][j] != 'X' && !visited[i][j]){
                    //그 섬에서 머무를 수 있는 일 수를 구해서 answer 에 저장
                    answer.add(findSize(i, j));
                }
            }
        }

        // answer 에 들어 있는 값이 없을 경우 -1을 추가하여 지낼 수 있는 무인도가 없다는 것을 처리해준다.
        if(answer.isEmpty()) answer.add(-1);

        // answer 를 오름차순으로 정렬하여 배열로 변경
        return answer.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    /** 무인도에서 머무를 수 있는 일 수를 탐색하는 메서드
     * @param startY 시작 y 좌표
     * @param startX 시작 x 좌표
     * @return 머무를 수 있는 일 수
     */
    private int findSize(int startY, int startX) {
        int sum = 0;

        //시작 좌표의 방문 표시를 해준다.
        visited[startY][startX] = true;

        //탐색 좌표를 넣을 Queue
        Queue<int[]> locations = new LinkedList<>();
        locations.add(new int[]{startY, startX});

        //BFS
        while (!locations.isEmpty()){
            int[] current = locations.poll();
            int dy = current[0];
            int dx = current[1];
            sum += map[dy][dx] - 48;    //sum 에 해당 좌표의 값을 넣어준다.

            // 상하좌우 좌표를 탐색한다.
            for (int i = 0; i < 4; i++) {
                int nowY = dy + directY[i];
                int nowX = dx + directX[i];

                //방문하지 않았으며 바다가 아닌 경우 큐에 추가
                if(nowY >= 0 && nowY < ROW && nowX >= 0 && nowX < COL && !visited[nowY][nowX] && map[nowY][nowX] != 'X'){
                        locations.offer(new int[]{nowY, nowX});
                        visited[nowY][nowX] = true;
                }
            }
        }

        return sum;
    }
}
