package java.pg._230925_42897_도둑질;

import java.util.Arrays;

//https://school.programmers.co.kr/learn/courses/30/lessons/42897
//신규_프로그래머스_lv4_42897_도둑질
public class Solution_42897 {
    public int solution(int[] money) {
        //첫번째 집부터 훔쳤을 경우
        int[] startZero = Arrays.copyOfRange(money, 0, money.length);
        startZero[1] = -1;          // 첫번째 집을 훔친 것을 가정하기 때문에 중복 계산을 피하기 위해 -1을 넣어준다.
        startZero[2] += money[0];   // index 2에 첫번째 집의 금액을 합산해준다.

        //첫번째 집을 훔치지 않았을 경우
        int[] startOne  = Arrays.copyOfRange(money, 0, money.length);
        startOne[0]  = -1;          // 두번째 집을 훔친 것을 가정하기 때문에 중복 계산을 피하기 위해 -1을 넣어준다.


        for (int i = 3; i < money.length; i++) {
            startZero[i] += Math.max(startZero[i - 2], startZero[i - 3]);   //i번째 집을 훔칠 수 있다면 i-1을 훔지지 않았다는 의미이기 때문에
            startOne[i]  += Math.max( startOne[i - 2],  startOne[i - 3]);   //[i - 2], [i - 3]번째 중 어느 집에서 i로 넘어왔을 때 가장 금액이 큰지 계산해준다.
        }
        //첫번째 집을 훔쳤다면 마지막 집은 들릴 수 없기 때문에 [i-2] [i-3] 중 큰 값을 고른다.
        int startZeroMax  = Math.max(startZero[startZero.length - 2], startZero[startZero.length - 3]);
        int startOneMax   = Math.max(  startOne[startOne.length - 1],   startOne[startOne.length - 2]);

        return Math.max(startZeroMax, startOneMax);
    }
}
