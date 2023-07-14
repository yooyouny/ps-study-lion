import java.util.*;

// 배열의 길이가 백만이므로 이중포문으로는 안됨 -> 스택 큐를 생각
class Solution154539_2 {
	public int[] solution(int[] numbers) {
		int[] answer = new int[numbers.length];
		Arrays.fill(answer, -1);// 뒤에 큰 수가 없으면 -1로 리턴해야하므로 초기화 해줌
		Stack<Integer> stack = new Stack<>();// 현재 인덱스를 기준으로 다음 인덱스를 순차적으로 비교해야하므로 stack 활용
		for(int i=0; i<numbers.length; i++){
			while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]){// 현재 인덱스와 다음 인덱스를 비교해서 큰 숫자를 발견 할 경우
				int idx = stack.pop();// 만약 peek 인덱스가 계속 큰 수를 발견하지 못했더라면 while문 안에서 전부 pop 됨
				answer[idx] = numbers[i];
			}
			stack.add(i);// 다음 비교 대상으로 넣어줌
		}
		return answer;
	}
}
