package java.pg._231221_12971_스티커모으기2;
//https://school.programmers.co.kr/learn/courses/30/lessons/12971
//신규_프로그래머스_lv3_12971_스티커모으기2
public class Solution_12971 {
    /**
     * 스티커를 선택하는 방법은 첫번째를 선택하는 경우와 하지 않는 경우 2가지 이다.
     * 그리고 i번째 스티커를 선택하는 경우와 i-1 과 i+1번째 스타커 중 어느게 큰지를 봐야 한다.
     * @param sticker
     * @return
     */
    public int solution(int sticker[]) {
        int answer = 0;
        int length = sticker.length;

        if(length == 1) return sticker[0];
        if(length == 2) return Math.max(sticker[0], sticker[1]);

        int[] dp1 = new int[length];    //첫번째 스티커를 떼는 경우
        int[] dp2 = new int[length];    //두번째 스티커를 떼는 경우

        // 첫번째 스티커를 떼는 방법
        // dp의 0, 1번 째 값을 첫번째 스티커 값으로 두어 반드시 첫번때를 쓴다고 지정
        dp1[0] = sticker[0];
        dp1[1] = sticker[0];
        // 첫번째 스티커의 사용유무를 정하고 시작하기 때문에 i = 2,
        // 마지막 스티커는 사용하지 않기 때문에 i < length - 1
        // 3번 째 스티커부터 A -> B -> C 일 때 B를 쓰는것과 A,C를 쓰는 것 중 큰것을 선택하도록 한다.
        for (int i = 2; i < length - 1;i++)
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + sticker[i]);


        // 첫번째 스티커를 떼지 않는 방법
        // 1번째와 0을 dp에 넣어 반드시 두번째를 쓴다고 가정
        dp2[0] = 0;
        dp2[1] = sticker[1];
        for (int i = 2; i < length; i++)
            dp2[i] = Math.max(dp2[i-1],dp2[i-2] + sticker[i]);

        answer = Math.max(dp1[length-2],dp2[length-1]);

        return answer;
    }
}
