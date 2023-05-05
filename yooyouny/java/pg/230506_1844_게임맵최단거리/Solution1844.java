import java.util.*;
class Solution1844{
	public void bfs(int[][] maps, int r, int c){
		// 동서남북 방향 좌표
		int[] dr = {1,-1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		// bfs이므로 큐 사용, 큐 안에 {r, c} 좌표 값 저장
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{r, c});// 이미 방문해있는 시작좌표 offer
		// 신규 방문지 = 이전 방문지 + 1을 해가며 전진하기 때문에 시작좌표를 1로 초기화
		maps[r][c] = 1;

		while(!queue.isEmpty()){// poll할 이전 좌표가 없으면 종료
			int[] poll = queue.poll();// 이전 좌표값 poll
			r = poll[0];
			c = poll[1];
			for(int i=0; i<4; i++){// 방향좌표의 Idx 접근 위한 for문
				int nr = r + dr[i];// 전진 할 신규좌표를 변수에 저장
				int nc = c + dc[i];
				if(nr>=0 && nr<maps.length && nc>=0 && nc<maps[0].length && maps[nr][nc] == 1){// 신규 좌표가 maps 범위 안에 있으면서 전진 할 신규좌표의 값이 벽이 아닐 경우
					maps[nr][nc] = maps[r][c] + 1;// 신규좌표에 이전좌표 값 + 1을 해주고
					queue.offer(new int[]{nr, nc});// 큐에 신규좌표 값 offer
				}
			}
		}

	}
	public int solution(int[][] maps) {
		int r = maps.length - 1;// maps의 r, c size를 접근하기 위해 변수로 선언
		int c = maps[0].length - 1;

		bfs(maps, 0, 0);// 문제에서 요구하는 것이 maps[r][c]까지의 최단거리이기 때문에 bfs로 생각, 시작 좌표가 0, 0

		return maps[r][c] == 1 ? -1 : maps[r][c];// 목적지 값이 변경되었으면 최소경로의 길이 리턴, 변경되지 않았으면 -1 리턴
	}
}
