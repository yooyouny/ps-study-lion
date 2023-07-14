import java.util.*;
/*
주식가격 문제와 동일한 로직

순서가 고정되어있음 -> 해당 수 다음 인덱스 들 중에서 큰 값
애초에 문제가 다음 인덱스의 값이 큰것만 확인함
* */

class Solution154539 {
	public int[] solution(int[] numbers) {
		Stack<Integer> stack = new Stack();
		int[] answer = new int[numbers.length];
		Arrays.fill(answer, -1);// -1로 초기화

		for(int i=0; i<numbers.length; i++){
			while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]){// 대상 인덱스의 값과 비교인덱스의 값을 확인
				// 3 3 5와 같이 연달아 뒤에있는 큰 수인 경우 같은 큰수를 갱신
				answer[stack.pop()] = numbers[i];// 해당되는 인덱스에 찾은 뒤에있는큰수로 갱신
			}
			stack.add(i);// pop할 대상 인덱스 삽입
		}
		//stack에 남아있는 인덱스(갱신이 안된)들은 뒤에있는 큰 수가 없는 인덱스들
		return answer;
	}
}
