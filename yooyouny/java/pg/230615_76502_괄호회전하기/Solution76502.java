import java.util.*;
class Solution76502 {

	private String rotate(String s, int start){
		StringBuilder sb = new StringBuilder(s.substring(start, s.length()));// start 인덱스를 시작으로 슬라이싱
		int idx = 0;// 뒤에 붙여줄 문자를 접근하기 위한 인덱스
		while(idx < start){// start 인덱스 전까지의 문자만 해당되므로
			char c = s.charAt(idx);// 원본인 s를 기준으로 idx 순서의 문자를 파싱
			sb.append(c);
			idx++;// idx를 증가시켜가며 start 이전 문자들을 append
		}
		return sb.toString();
	}

	private boolean isValid(String s){
		Stack<Character> stack = new Stack<>();

		for (char ch : s.toCharArray()) {
			switch (ch) {
				case '(' -> stack.push(')'); // 직전 괄호랑 같은지 비교하기 위해 열린괄호 시 닫힌괄호 push
				case '{' -> stack.push('}');
				case '[' -> stack.push(']');
				case ')', '}', ']' -> {
					if (stack.isEmpty() || stack.pop() != ch) return false;// 이전에 삽입한 닫힌 괄호와 같은지 비교
				}
			}
		}
		return stack.isEmpty();// 짝이 맞으면 empty, 맞지 않으면 남이있으므로 boolean 값 리턴
	}

	public int solution(String s) {
		int answer = 0;

		for(int i=0; i<s.length(); i++){
			String str = rotate(s, i);
			if(isValid(str)) answer++;// 회전한 문자열이 규칙에 일치하면 answer 값 증가
		}

		return answer;
	}
}
