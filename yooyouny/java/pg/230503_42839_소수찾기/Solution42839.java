import java.util.*;
class Solution42839 {
	Set<Integer> set;
	//numbers에 있는 문자들을 조합해서 set에 담을 조합 함수. 재귀로 구현
	public void comb(char[] numbers, StringBuilder sb, boolean[] visited){
		if(sb.length() > 0){ // sb에 있는 문자열을 set에 넣어줌
			set.add(Integer.parseInt(sb.toString()));
		}

		for(int i=0; i<numbers.length; i++){// numbers에 있는 문자를 하나씩 접근하기 위한 for문
			if(!visited[i]){// sb에 넣지 않은 문자인지 체크
				visited[i] = true;
				sb.append(numbers[i]);
				comb(numbers, sb, visited);// 선택하지 않은 다른 문자들 중 하나를 선택하기 위해 comb를 재귀로 호출
				sb.deleteCharAt(sb.length() - 1);// 재귀 호출이 완료되면 선택했던 문자를 제거하고
				visited[i] = false;// 체크한 선택 유무를 다시 원래대로 돌려놓음
			}
		}
	}
	public boolean isPrime(int n){
		if(n < 2) return false; //1과 0은 소수가 아님

		// 2부터 n의 제곱근(Math.sqrt(n)) or n/2까지 나눴을 때 나누어 떨어지면 소수가 아님
		for(int i=2; i<=n/2; i++){
			if(n % i == 0)
				return false;
		}
		return true;
	}
	public int solution(String numbers) {
		int cnt = 0;// 소수의 개수를 저장할 변수
		set= new HashSet<>();// numbers로 조합 가능한 모든 수를 저장해야하는데 해당 수의 size를 모르고, 중복을 제거해야하므로 set으로 선언
		//numbers의 모든 요소들로 조합 가능한 숫자를 구하는 함수 실행)
		comb(numbers.toCharArray(), new StringBuilder(), new boolean[numbers.length()]);// 조합해야할 문자들이 있는 배열, 문자들을 조합할 sb, sb에 해당 문자를 append했는지 체크할 boolean배열
		for(int n : set){ //set은 Iterator로 접근
			if(isPrime(n))// 해당 숫자가 소수일 경우 cnt 증가
				cnt++;
		}
		return cnt;
	}
}
