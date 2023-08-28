package java.pg._230828_121687_실습용로봇;
//https://school.programmers.co.kr/learn/courses/15009/lessons/121687
//신규_프로그래머스_lv1_121687_실습용로봇
public class Solution_121687 {
    int[] dx = new int[]{0, 1, 0, -1};  //방향별 x좌표 이동값
    int[] dy = new int[]{1, 0, -1, 0};  //방향별 y좌표 이동값
    public int[] solution(String command) {
        int nowX = 0;
        int nowY = 0;
        int nowDirect = 0;  //현재 방향
        for(char spell : command.toCharArray()){
            switch (spell){
                case 'G' -> { // 전진
                    nowX += dx[nowDirect];
                    nowY += dy[nowDirect];
                }
                case 'B' -> { // 후진
                    nowX -= dx[nowDirect];
                    nowY -= dy[nowDirect];
                }
                case 'R' -> nowDirect = nowDirect==3 ? 0 : nowDirect+1; //오른방향 회전 direct +1
                case 'L' -> nowDirect = nowDirect==0 ? 3 : nowDirect-1; //왼방향 회전 direct -1
            }
        }
        return new int[]{nowX, nowY};
    }
}
