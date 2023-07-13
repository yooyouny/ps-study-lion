package java.pg._230509_12946_하노이탑;

import java.util.ArrayList;

//복습_프로그래머스_lv3_12946_하노이탑
public class Solution_12946_2 {
	/*
		1. N번 블록을 A에서 C로 옮기기 위해 N-1번째 블록을 B로 옮긴다. N (A->C)   N-1 (A->B)
		2. N번째 블록을 C로 옮기고 N-1번째 블록을 B에서 C로 옮긴다.    N-1 (B->C)
	 */
	public int[][] solution(int n) {
		int[][] answer = {};
		ArrayList<int[]> result = new ArrayList<>();
		hanoi(1, 2, 3, n, result);

		return result.stream().toArray(int[][]::new);
	}
	public void hanoi(int AA, int BB, int CC, int n, ArrayList result){
		if(n==1){
			result.add(new int[]{AA, CC});
			return;
		}else{
			hanoi(AA, CC, BB, n-1, result);
			result.add(new int[]{AA, CC});
			hanoi(BB, AA, CC, n-1, result);
		}
	}
}
