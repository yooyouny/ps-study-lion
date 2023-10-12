package java.pg._231012_181187_두원사이의정수쌍;
//https://school.programmers.co.kr/learn/courses/30/lessons/181187
//신규_프로그래머스_lv2_181187_두원사이의정수쌍
public class Solution_181187 {
    /**
     * 원의 방정식을 사용하여 각 x값에 따른 y값을 구한다.
     * x^2 + y^2 = r^2
     * y^2 = r^2 - x^2
     */
    public long solution(int r1, int r2) {
        long answer = 0;

        //각 반지름의 r^2 값을 구해준다.
        long radius1Pow = (long) Math.pow(r1,2);
        long radius2Pow = (long) Math.pow(r2,2);

        // 1 ~ r2 까지의 정수 x를 대입했을 때의 y값을 구해주고,
        // 각 y값 사이의 정수 개수를 구해준다.
        // x = 0 을 포함하면 각 사분면의 개수에 겹치는 수가 생기기 때문에 제외
        for (int i = 1; i <= r2; i++) {
            long xPow = (long) Math.pow(i , 2);         // x^2 값을 구해주고
            double y1 = Math.sqrt(radius1Pow - xPow);   // 각 y값을 구해준다.
            double y2 = Math.sqrt(radius2Pow - xPow);

            // 큰 원의 y2와 작은 원의 y1 사이의 좌표 개수를 구해준다.
            answer += ( (long) y2 - (long) Math.ceil(y1) + 1);
        }
        // 구한 것은 한 사분면의 개수이기 때문에 4를 곱해줘 원 전체의 좌표 개수를 구한다.
        return answer * 4;
    }
}
