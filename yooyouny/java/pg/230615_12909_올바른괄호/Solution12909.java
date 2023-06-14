import java.util.*;
class Solution12909 {
	boolean solution(String s) {
		Stack<Character> stack = new Stack<>();// 짝지어서 제거하기 위해 LIFO 구조인 stack 활용

		for(char ch : s.toCharArray()){
			if(ch == '(') stack.push(ch);// '('를 만날 경우 push
			else{
				if(stack.isEmpty()) return false;// 비어있을 경우 짝이 안맞는 경우니 바로 리턴
				stack.pop();// ')'일 경우 pop
			}
		}

		return stack.isEmpty();// pop 과정을 거쳐 비어있는 경우면 짝이 맞으니 true, 남아있는게 있으면 짝이 안맞는 경우 false 리턴
	}
}
