package java.pg._230720_42898_등굣길;

import java.util.Arrays;

//https://school.programmers.co.kr/learn/courses/30/lessons/42898
//신규_프로그래머스_Lv3_42898_등굣길
public class Solution_42898 {
	/**
	 *
	 * @param m			행의 개수
	 * @param n			열의 개수
	 * @param puddles   물 웅덩이의 좌표 [x, y]
	 */
	public int solution(int m, int n, int[][] puddles) {
		int[][] board = new int[n][m];
		//시작 좌표의 값을 1
		board[0][0] = 1;
		//물 웅덩이의 값을 -1로 초기화 해준다.
		for (int[] node : puddles) board[node[1]-1][node[0]-1] = -1;

		// 보드의 y, x 까지의 최소 거리 가능 개수는
		// <y-1, x> 와 <y, x-1>의 합으로 구할 수 있다.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(board[i][j]== -1){board[i][j] = 0;}			// board[i][j]가 웅덩이인 경우는 계산하지 않는다.
				else{
					if(i != 0) board[i][j] += board[i - 1][j];	// i가 0인 경우는 위의 node 값을 더할 수 없으므로 제외해준다.
					if(j != 0) board[i][j] += board[i][j - 1];	// j가 0인 경우는 위의 node 값을 더할 수 없으므로 제외해준다.
					board[i][j] %= 1_000_000_007;				// board[i][j]값이 범위를 벗어나지 않게 계산 할 때마다 나눠준다.
				}
			}
		}
		return board[n-1][m-1];
	}

	/** 테스트 출력용 메서드 */
	public void printBoard(int[][] board){
		for (int[] row : board){
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
	}
}
