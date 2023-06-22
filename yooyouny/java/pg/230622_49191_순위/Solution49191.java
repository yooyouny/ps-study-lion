import java.util.*;
class Solution49191 {
	static boolean[][] board;
	private int getWinScore(int r, int n, boolean[] visited){
		int cnt = 1;// 해당 선수 자신도 이긴선수에 포함되므로 1로 초기화
		for(int i=0; i<n; i++){
			if(!board[r][i] || visited[i]) continue;// 재귀탐색 중에 이미 방문했거나 해당 선수에게 이긴 선수가 아닌 경우는 제외
			visited[i] = true;// 해당 인덱스 방문 표시
			cnt += getWinScore(i, n, visited);//
		}
		return cnt;
	}
	private int getLoseScore(int c, int n, boolean[] visited){
		int cnt = 1;// 해당 선수 자신도 진 선수에 포함되므로 1로 초기화
		for(int i=0; i<n; i++){
			if(!board[i][c] || visited[i]) continue;// 재귀탐색 중에 이미 방문했거나 해당 선수에게 진 선수가 아닌 경우는 제외
			visited[i] = true;
			cnt += getLoseScore(i, n, visited);
		}
		return cnt;
	}
	public int solution(int n, int[][] results) {
		board = new boolean[n][n];// 승패관계를 표시할 인접행렬 선언
		for (int[] result : results) {
			board[result[0] - 1][result[1] - 1] = true;// result[0]를 기준으로 승리 표시
		}

		int count = 0;
		for (int i=0; i<n; i++){// 모든 케이스를 전부 탐색하므로 O(n^3)
			int wins = getWinScore(i, n, new boolean[n]) - 1;// i를 기준으로 해당선수가 이긴 선수의 수
			int loses = getLoseScore(i, n, new boolean[n]) - 1;// i를 기준으로 해당선수가 진 선수의 수
			if (wins + loses + 1 == n)// 승패관계를 정확히 알 수 있는 수 체크
				count++;
		}

		return count;
	}
}
