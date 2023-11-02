package java.pg._231031_140107_점찍기;
//https://school.programmers.co.kr/learn/courses/30/lessons/140107
//신규_프로그래머스_lv2_140107_점찍기
public class Solution_140107 {
    /**
     * 원점에서 x축 방향으로 a*k  y축 방향으로 b*k떨어진 곳에 점을 찍는다.
     * 원점과의 거리가 d를 넘으면 점을 찍지 않은다.
     */
    public long solution(int k, int d) {
        long answer = 0;

        // x^2 + y^2 = d^2 를 계산하기 쉽도록 값을 미리 구해준다.
        long maxDistance = (long) Math.pow(d, 2);

        // 계산
        for (int i = 0; i < d; i+=k) {
            // 점과 원점의 거리가 maxDistance 에 해당하는 y 좌표를 구해준다.
            int valY = calcY(maxDistance, (long) Math.pow(i, 2));

            // a*k로 구할 수 있는 valY 까지의 좌표의 개수를 구해 넣어준다.
            answer += (double) (valY / k) + 1;
        }
        return answer;
    }

    // y 좌표를 구하는 메서드
    public int calcY (long squareD, long squareX){
        return (int) Math.sqrt(squareD - squareX);
    }
}
