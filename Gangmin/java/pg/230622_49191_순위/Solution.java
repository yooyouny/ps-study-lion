import java.util.*;
public class Solution {
	public int solution(int n, int[][] results) {

		int answer = 0;
		/*경기 결과를 저장할 그래프*/
		int[][] graph = new int[n+1][n+1];
		//현재 발표된 경기 결과를 그래프에 반영
		for(int i = 0; i < results.length; i++)
			//[4, 3] -> graph [4][3] = 1 --> 이긴 경우 1 표시
			graph[results[i][0]][results[i][1]] = 1; //이김
		/*플로이드 워셜 알고리즘 적용*/
		for(int i = 0; i <= n; i++) {
			/*j 선수가 z 선수를 이길 수 있는가?*/
			for(int j = 0; j <= n; j++) {
				/*상대는 z 선수*/
				for(int z = 0; z <= n; z++) {
					/*j선수가 i 선수를 이겼고, i선수가 z선수를 이겼다면
					* j선수가 z선수를 이길수 있다는 의미이기 때문에 1을 대입*/
					if (graph[j][i] == 1 && graph[i][z] == 1)
						graph[j][z] = 1;
				}
			}
		}
		/*각 i선수들의 경기 결과 탐색*/
		for (int i = 1; i <= n; i++) {
			/*확정된 경기결과의 수를 저장할 game
			* 초기화해서 중복된 경기결과는 제거*/
			int game = 0;
			/*상대 j선수와 경기 결과에 대해 확인*/
			for (int j = 1; j <= n; j++) {
				if (graph[i][j] == 1 || graph[j][i] == 1)
					/*만약 경기결과가 발표되었으면 game을 증가시킨다.*/
					game++;
			}
			/*자기 자신을 제외한 n-1개의 경기결과가 나왔다면, 답을 증가시킨다.*/
			if (game == n-1)
				answer++;
		}
		return answer;
	}
}

