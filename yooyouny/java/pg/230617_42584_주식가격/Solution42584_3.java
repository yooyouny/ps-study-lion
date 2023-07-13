import java.util.*;
class Solution42584_3 {
	public int[] solution(int[] prices) {
		int len = prices.length;
		int[] answer = new int[len];
		Stack<Integer> stack = new Stack<>();// 현재 인덱스를 기준으로 다음에 있는 인덱스들과 비교해야하므로 스택 사용
		for(int i=0; i<len; i++){
			while(!stack.isEmpty() && prices[stack.peek()] > prices[i]){// 감소하는 가격 구간이 있으면
				int idx = stack.pop();
				answer[idx] = i - idx;// 비교할 인덱스와 기준 인덱스 차이만큼
			}
			stack.push(i);// 비교 인덱스 삽입
		}
		while(!stack.isEmpty()){// 현재까지 남아있는 인덱스는 한번도 주식가격이 떨어지지 않은 것이므로
			int idx = stack.pop();
			answer[idx] = prices.length - 1 - idx;// 전체 길이에서 현재 인덱스 길이만큼 빼준 길이로 갱신
		}
		return answer;
	}
}
