import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class Solution {

	/*인덱스와 값을 저장후 스택에 들어갈 객체*/
	static class PastPosition{
		int value;
		int index;

		public PastPosition(int value, int index) {
			this.value = value;
			this.index = index;
		}
	}
	/*괄호문제랑 같은 이유로 stack활용*/
	static Stack<PastPosition> stack = new Stack<>();
	public static int[] solution(int[] prices) {
		return proc(prices);
	}

	public static int[] proc(int[] prices){
		int[] answer = new int[prices.length];
		for (int i = 0; i < prices.length; i++) {
			/*비어있지 않거나 현재 주식 가격보다 작거나 같다면, 가격이 떨어지지 
			* 않은 것*/
			if(stack.empty() || stack.peek().value <= prices[i]){
				stack.push(new PastPosition(prices[i], i));
			/*가격이 떨어지면, 가격이 떨어진 주식들을 모두 확인, 비어있으면 오류기때문에 not Empty*/
			}else {
				while(!stack.empty() && stack.peek().value > prices[i]) {
					PastPosition droppedStock = stack.pop();
					/*현재 시간 - 과거 주식 가격을 본 시점 -> 가격이 떨어지지 않은 기간*/
					int duration = i - droppedStock.index;
					answer[droppedStock.index] = duration;
				}
				stack.push(new PastPosition(prices[i], i));
			}
		}
		/*장이 끝나고 나서도 떨어지지 않은 주식 가격들 계산*/
		while (!stack.empty()){
			PastPosition pop = stack.pop();
			/*자기 index를 포함하지 않기 위해 -1*/
			int duration = prices.length - pop.index - 1;
			answer[pop.index] = duration;
		}
		return answer;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[]{4,2,2,1,2})));
	}
}

