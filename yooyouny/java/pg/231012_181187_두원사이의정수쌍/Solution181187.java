import java.util.*;
class Solution181187 {
    public long solution(int r1, int r2) {
        long answer = 0;

        // x^2 + y^2 = r^2을 활용하여 1사분면의 점의 개수(y길이)를 구하고 * 4하기
        for(int x = 1; x <= r2 ; x++){// x좌표에 해당하는 y길이 구하기
            double y2 = Math.sqrt(Math.pow(r2,2) - Math.pow(x,2));// 큰 원의 점의 개수
            double y1 = Math.sqrt(Math.pow(r1,2) - Math.pow(x,2));// 작은 원의 점의 개수
            // 작은 원의 y높이 이상 큰 원의 y높이 이하인 점의 개수 구하기
            answer += ( (long)y2 - (long)Math.ceil(y1) + 1);//큰 원의 위치를 내림 한 값 - 작은 원의 위치를 올림한 값 + 1
        }
        answer *= 4;

        return answer;
    }

}