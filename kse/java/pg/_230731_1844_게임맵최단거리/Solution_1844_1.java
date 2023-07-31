package java.pg._230731_1844_게임맵최단거리;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_1844_1 {
	/**
	 BFS를 사용하여 결과값이 하나라도 나왔다면 거기서 정지할 것
	 */
	public int solution(int[][] maps) {
		int n = maps.length;		//세로 길이
		int m = maps[0].length;		//가로 길이

		// 방문 여부를 저장하는 배열
		boolean[][] visited = new boolean[n][m];

		// 상하좌우 이동 저장
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{0, 0, 1}); 	// 시작 위치 (0, 0)와 이동 횟수 1

		while (!queue.isEmpty()) {		// Queue 가 비었다면 더이상 갈 경로가 x -> return -1;
			int[] current = queue.poll();	//현재 좌표
			int nowX = current[0];
			int nowY = current[1];
			int count = current[2];

			// 도착 지점에 도달한 경우 최단 경로를 업데이트하고 종료
			if (nowX == m - 1 && nowY == n - 1) {
				return count;
			}

			// 현재 위치를 방문 처리
			visited[nowY][nowX] = true;

			// 상하좌우로 이동하며 유효한 위치인지 확인하고 큐에 추가
			for (int i = 0; i < 4; i++) {
				int nx = nowX + dx[i];
				int ny = nowY + dy[i];

				// 유효한 위치이고, 벽이 아니며, 방문하지 않았다면 큐에 추가
				if (nx >= 0 && nx < m && ny >= 0 && ny < n && maps[ny][nx] == 1 && !visited[ny][nx]) {
					queue.add(new int[]{nx, ny, count + 1});
					visited[ny][nx] = true; // 큐에 추가한 위치를 방문 처리
				}
			}
		}

		return -1;
	}
}
