package java.pg._230715_12945_피보나치수;

//https://school.programmers.co.kr/learn/courses/30/lessons/12945?language=java
//신규_프로그래머스_Lv2_12945_피보나치수
//재귀 사용 시 시간 초과
public class Solution_12945 {

	public int solution(int n) {
		int answer = 0;
		int[] fibonacci = new int[n+1];
		fibonacci[0] = 0;		// f(0) = 0
		fibonacci[1] = 1;		// f(1) = 1
		for (int i = 2; i <= n; i++) {
			// f(n) = f(n-1) + f(n-2)
			// 필요한 건 1234567 이내의 숫자이기 때문에
			// 이 범위를 넘어가는 수를 버려도 문제가 생기지 않는다.
			fibonacci[i] = (fibonacci[i-1] + fibonacci[i-2]) % 1234567 ;
		}
		answer = fibonacci[n];
		return answer;
	}


}
