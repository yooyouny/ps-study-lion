import java.util.*;
class Solution {
	public boolean bfs(String[] place, int x, int y){
		Queue<int[]> queue = new LinkedList<>();// 좌표 배열을 넣을 큐
		queue.offer(new int[]{x, y});

		int[] dx = {-1, 1, 0, 0};// 상하좌우 탐색
		int[] dy = {0, 0, -1, 1};

		while(!queue.isEmpty()){// 큐가 빌때까지 진행
			int[] position = queue.poll();// x, y좌표 값

			for(int i=0; i<4; i++){
				int nx = position[0] + dx[i];// 좌표 이동
				int ny = position[1] + dy[i];

				if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || (nx == x && ny == y))// 범위를 벗어나거나 기준좌표와 같을 경우는 제외
					continue;

				int direction = Math.abs(nx - x) + Math.abs(ny - y);// 맨해튼 거리

				if(direction <= 2 && place[nx].charAt(ny) == 'P')// 상하좌우 방향에서 맨해튼 거리가 2이상이고 사람이면
					return false;// 거리두기를 지키고 있지 않으므로 false 반환
				else if(direction < 2 && place[nx].charAt(ny) == 'O')// 맨해튼 거리가 2미만이지만 빈테이블 일 경우
					queue.offer(new int[] {nx, ny});// 큐에 중심좌표로 넣고 중심좌표 주변에 있는 P를 탐색하도록 함
			}
		}
		return true; //큐에 있는 좌표가 비었을 경우 거리두기를 모두 지켰으므로 true 반환
	}

	public int[] solution(String[][] places) {
		int[] answer = new int[places.length];

		for(int i=0; i<places.length; i++){
			String[] place = places[i];
			boolean result = true;// 결과가 0아님 1이므로 boolean 타입으로 선언

			for(int x=0; x<5; x++){// place의 길이는 5로 고정
				for(int y=0; y<5; y++){
					if(place[x].charAt(y) == 'P'){// 사람을 발견하면 해당 좌표 기준으로 bfs 탐색
						if(!bfs(place, x, y))
							result = false;
					}
				}
			}
			answer[i] = result ? 1 : 0;
		}

		return answer;
	}
}
