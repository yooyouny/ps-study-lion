import java.util.*;
class Solution67257 {
	long answer;
	public void comb(char[] operator, boolean[] visited, StringBuilder sb, String expression){
		if(sb.length() == operator.length){
			calculator(sb.toString(), expression);
		}
		for(int i=0; i<operator.length; i++){
			if(!visited[i]){
				visited[i] = true;
				sb.append(operator[i]);
				comb(operator, visited, sb, expression);
				visited[i] = false;
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
	private void calculator(String operator, String expression){
	}
	public long solution(String expression) {
		String operator = "";
		boolean[] chk = new boolean[3];

		char[] input = expression.toCharArray();
		for(char ch : input){
			if(!chk[0] && ch == '+'){
				chk[0] = true;
				operator += ch;
			}
			if(!chk[1] && ch == '-'){
				chk[1] = true;
				operator += ch;
			}
			if(!chk[2] && ch == '*'){
				chk[2] = true;
				operator += ch;
			}
		}
		comb(operator.toCharArray(), new boolean[operator.length()], new StringBuilder(), expression);
		return answer;
	}
}
