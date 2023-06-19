package java.pg._230617_42584_주식가격;

import java.util.Stack;
//https://school.programmers.co.kr/learn/courses/30/lessons/42584
//신규_프로그래머스_lv2_주식가격
public class Solution_42584 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> upDown = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            //하락장인 경우
            while(!upDown.isEmpty() && prices[i] < prices[upDown.peek()]){
                //stack은 상승장동안의 index가 남아있기 때문에
                //하락장의 값이 이전값보다 작지 않은 경우 그 뒤에 있는 모든 값은 다 작지 않다.
                //따라서 prices[i] < prices[upDown.peek()]를 만족하는 경우는 결과값을 도출하고
                //남은 값들은 남겨준다.
                //하락한 시점까지의 기간[하락점 - 기준점]
                answer[upDown.peek()] = i - upDown.pop();
            }
            upDown.push(i);
        }
        //stack에 남아있는 값은 모두 한 번도 가격이 하락하지 않은 주식이기 때문에
        //주식의 길이 - 본인 index -1을 하여 answer를 구해 준다.
        while (!upDown.isEmpty()){
            answer[upDown.peek()] = prices.length - upDown.pop() - 1;
        }
        return answer;
    }
}
