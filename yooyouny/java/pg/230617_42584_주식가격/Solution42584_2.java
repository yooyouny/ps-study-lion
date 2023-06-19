import java.util.Stack;
class Solution42584_2 {
	public int[] solution(int[] prices) {
		int[] answer = new int[prices.length];

		Stack<Integer> stack = new Stack<>();// 인덱스를 저장할 스택

		for(int i=0; i<prices.length; i++){ // 주식가격이 떨어진 경우 저장
			while(!stack.isEmpty() && prices[stack.peek()] > prices[i]){// 비교 인덱스 값이 현재 인덱스 값보다 크면(가격이 떨어지면)
				int index = stack.pop();// 비교 인덱스 pop
				answer[index] = i - index;// 현재인덱스와 비교인덱스 차이(시간)를 저장
			}
			stack.push(i);//가격이 떨어진 적이 없으면 비교 인덱스를 현재 인덱스로 변경해주기 위해 저장
		}

		while(!stack.isEmpty()){// 가격이 떨어지지 않은 경우 저장
			int index = stack.pop();// 해당 인덱스 pop
			answer[index] = prices.length - index - 1;// 전체 price 길이에서 인덱스만큼을 뺀 값 저장 (시간차이)
		}
		return answer;
	}
}
