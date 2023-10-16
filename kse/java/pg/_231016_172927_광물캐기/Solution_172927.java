package java.pg._231016_172927_광물캐기;

import java.util.Arrays;

//https://school.programmers.co.kr/learn/courses/30/lessons/172927
//신규_프로그래머스_lv2_172927_광물캐기
public class Solution_172927 {
    int answer;
    int pickSum;
    int[][] sumBoard;

    public int solution(int[] picks, String[] minerals) {
        // 최소 피로도
        answer   = Integer.MAX_VALUE;
        // 곡괭이 총 개수
        pickSum  = Arrays.stream(picks).sum();
        // 피로도 층감 보드 [곡괭이][광물]
        sumBoard = new int[][]{ { 1, 1, 1},
                                { 5, 1, 1},
                                {25, 5, 1}};

        // 각 시작 곡괭이를 정해 dfs 를 돌린다.
        for (int i = 0; i < 3; i++) {
            if(picks[i] > 0){
                picks[i] -= 1;
                mine(picks, minerals, i, 0, 0, 1);
                picks[i] += 1;
            }
        }
        return answer;
    }

    private void mine(int[] picks, String[] minerals, int pickIndex, int index, int totalSum, int pickCount) {

        // 이미 최소 피로도가 될 수 없거나 index 값이 광물 개수를 초과한 경우 무효
        if (answer <= totalSum || index >= minerals.length) return;

        // 이번 곡괭이를 사용했을 때의 합계를 구한다.
        int nowSum = 0;
        // 한 곡괭이당 쓸 수 있는 횟수는 5회
        for (int i = 0; i < 5; i++) {
            // mineral 이 더 없는 경우 break
            if (index + i >= minerals.length) break;
            // sumBoard 에서 해당 피로도값을 찾아 더해준다.
            switch (minerals[index + i].charAt(0)){
                case 'd' -> nowSum += sumBoard[pickIndex][0];
                case 'i' -> nowSum += sumBoard[pickIndex][1];
                case 's' -> nowSum += sumBoard[pickIndex][2];
            }
        }

        // 곡괭이를 다 썼거나 index 범위를 초과하는 경우 종료
        if (pickCount >= pickSum || index + 5 >= minerals.length) {
            answer = Math.min(answer, totalSum + nowSum);
            return;
        }

        // 다음 곡괭이를 선택하여 넘겨준다.
        for (int i = 0; i < 3; i++) {
            if (picks[i] == 0) continue;
            if(picks[i] > 0){
                picks[i] -= 1;
                mine(picks, minerals, i, index + 5, totalSum + nowSum, pickCount + 1);
                picks[i] += 1;
            }
        }
    }
}
