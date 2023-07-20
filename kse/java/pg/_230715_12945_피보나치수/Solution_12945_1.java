package java.pg._230715_12945_피보나치수;
//https://school.programmers.co.kr/learn/courses/30/lessons/12945?language=java
//신규_프로그래머스_Lv2_12945_피보나치수
//동적계획법
public class Solution_12945_1 {
	public int solution(int n) {
		int answer = 0;
		int[] result = new int[n+1];
		result[0] = 0;
		result[1] = 1;
		answer = fibonacci(result, n);
		return answer;
	}

	private int fibonacci(int[] arr, int n) {
		if(n==0) 	 return 0;
		if(arr[n]>0) return arr[n];
		arr[n] = (fibonacci(arr, n-1) + fibonacci(arr, n-2))% 1234567;
		return arr[n];
	}
}
