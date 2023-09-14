package java.pg._230913_150365_미로탈출명령어;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/150365
//신규_프로그래머스_lv3_150365_미로탈출명령어
public class Solution_150365 {

    //이동 거리 *** x가 세로줄이다.
    int[] directY = new int[]{0, -1, 1, 0};
    int[] directX = new int[]{1, 0, 0, -1};
    String[] directString = new String[]{"d", "l", "r", "u"};

    int arriveX;    //도착 x좌표
    int arriveY;    //도착 y좌표
    int maxDistance;//이동해야 하는 거리
    int boardX;     //격자의 세로
    int boardY;     //격자의 가로
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        arriveY = c; boardX = n;
        arriveX = r; boardY = m;
        maxDistance = k;
        //DFS 탐색
        answer = dfs("", x, y);

        return answer.equals("") ? "impossible" : answer;
    }

    public String dfs(String string, int dx, int dy){
        int count = maxDistance - string.length();
        //이동할 수 있는 거리를 다 썼을 때
        if(count == 0){
            //탈출 위치에 있는 경우 return string
            if(arriveY == dy && arriveX == dx) return string;
            return "";
        }
        int diff = findDiff(dx, dy);
        // 남아 있는 이동 횟수로는 도달 할 수 없는 경우
        if(diff > count) return "";
        // 도달하더라도 최대거리까지의 이동을 사용했을 때 제자리로 돌아올 수 없는 경우
        if((count - diff) % 2 != 0) return "";


        // d -> l -> r -> u 순서로 대입하여 사전순에서 가장 빠른 경우를 구한다.
        String temp   = "";
        for (int i = 0; i < 4; i++) {
            int nextDx = dx + directX[i];
            int nextDy = dy + directY[i];

            if(nextDy < 1 || nextDy > boardY|| nextDx < 1 || nextDx > boardX) continue;

            temp = dfs(string + directString[i], nextDx, nextDy);

            //이전 경우에서 답이 나온 경우 더 이상 진행하지 않는다.
            if(!temp.equals("")) break;
        }
        return temp;
    }

    //거리 차이 구하는 메서드
    public int findDiff(int dx, int dy){
        return Math.abs(arriveX-dx) + Math.abs(arriveY-dy);
    }
}
