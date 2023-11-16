package java.pg._231116_12978_배달;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/12978
//신규_프로그래머스_lv2_12978_배달
public class Solution_12978 {
    int maxLength;
    boolean[] canGoTown;
    public int solution(int N, int[][] road, int K) {
        int answer  = 0;
        maxLength   = K;

        // 1. 도달 할 수 있는 마을인지 확인할 배열을 생성
        canGoTown   = new boolean[N + 1];

        // 2. road 배열의 내용을 map(key = 출발 마을)로 정리
        Map<Integer, List<int[]>> map = new HashMap<>();
        for(int i = 1; i < N + 1; i++) map.put(i, new ArrayList<>());

        for (int[] temp : road) {
            map.get(temp[0]).add(new int[]{temp[1], temp[2]});
            map.get(temp[1]).add(new int[]{temp[0], temp[2]});
        }

        // 3. 시작 마을인 1번을 true 로 하고 dfs 를 돌려준다.
        canGoTown[1] = true;
        calcLength(map, new boolean[N+1], 1, 0);

        // 4. 마을에 도달 할 수 있는 경우 answer + 1 해준다.
        for (boolean temp : canGoTown){ if(temp) answer++;}

        return answer;
    }

    public void calcLength (Map<Integer, List<int[]>> map, boolean[] isVisited, int sPoint, int befLength){
        // sPoint 에서 갈 수 있는 마을 목록을 불러온다.
        for (int[] temp : map.get(sPoint)){
            int end    = temp[0];
            int length = temp[1];
            // 이전에 방문한 적이 없고, 마을까지 가는 거리가 maxLength 이하 인 경우 다음 마을로 이동한다.
            if(!isVisited[end] && befLength + length <= maxLength) {
                isVisited[end] = true;
                canGoTown[end] = true;
                calcLength(map, isVisited, end, befLength + length);
                isVisited[end] = false;
            }
        }
    }
}
