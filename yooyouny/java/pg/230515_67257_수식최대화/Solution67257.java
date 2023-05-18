import java.util.*;
class Solution67257 {
	private long calculate(long first, long second, String op) {
		return switch (op) {
			case "+" -> first + second;
			case "-" -> first - second;
			case "*" -> first * second;
			default -> 0;
		};
	}
	private long calculate(List<String> inputList, String[] operator){
		for(String op : operator){
			for(int i=0; i<inputList.size(); i++){
				if(op.equals(inputList.get(i))){// 만들어놓은 연산자 조합의 연산자와 input연산자의 순서가 동일하면
					long first = Long.parseLong(inputList.get(i - 1));// 숫자, 연산자, 숫자 순서로 들어있기 때문에 i-1, i+1로 인덱스 접근
					long second = Long.parseLong(inputList.get(i + 1));
					inputList.remove(i-1);
					inputList.remove(i-1);
					inputList.remove(i-1);// 사용한 숫자, 연산자, 숫자를 모두 제거시켜주고
					inputList.add(i-1, String.valueOf(calculate(first, second, op)));// 연산한 결과를 해당 위치 앞에 add
					i -= 2;// 다음 연산자 인덱스를 가리키도록 조정
				}
			}
		}
		return Long.parseLong(inputList.get(0));
	}
	public long solution(String expression) {

		String[][] operators  = {{"+","-","*"},{"+","*","-"},
			{"-","+","*"},{"-","*","+"},
			{"*","+","-"},{"*","-","+"}};// 연산자 우선순위 조합을 모두 만듦

		StringTokenizer st = new StringTokenizer(expression, "+-*", true);// 연산자를 구분자로 설정하여 토큰으로 분리, 구분자도 함께 저장
		List<String> inputList = new ArrayList<>();// expression을 숫자, 연산자, 숫자 .. 순으로 리스트에 담음
		while (st.hasMoreTokens()) {// 초기는 true, 토큰이 있을때까지 반복
			inputList.add(st.nextToken());
		}

		long answer = 0;// * 연산으로 범위가 초과될 수 있으므로 long으로 정의
		for(String operator[] : operators){// 정의한 연산자 우선순위 조합과 input 연산자끼리 비교하며 절댓값 계산 후 최댓값 저장
			answer = Math.max(answer, Math.abs(calculate(new ArrayList<>(inputList), operator)));
		}

		return answer;
	}
}
