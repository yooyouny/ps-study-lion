import java.util.*;
/*
* 1. 좌표 이동을 규칙적으로 할때 dx dy 배열 이용
* 2. 배열의 인덱스 끝에 도달했을때 다시 0으로 되돌려야 할 경우
* 	- idx = (idx + 1) % 3
* 3. Arrays.stream(arr)
* 	- flatMapToInt(Arrays::stream) 2차원 배열 arr을 1차원으로
* 	- filter(i -> i != 0) 조건에 해당하는 애들만 필터링
* 	- toArray(); 배열로 반환
 */
class Solution_2 {
	public int[] solution(int n) {
		int size = n * (n+1) / 2;
		int[] answer = new int[size];
		int[][] snail = new int[n][n];

		int num = 1;
		int x = 0, y = 0, idx = 0;
		int[] dx = {0, 1, -1};
		int[] dy = {1, 0, -1};

		while(num <= size){
			snail[y][x] = num;
			int nx = x + dx[idx];
			int ny = y + dy[idx];

			if(nx<0 || ny <0 || nx>=n || ny>=n || snail[ny][nx] != 0){
				idx = (idx + 1) % 3;
				y = y + dy[idx];
				x = x + dx[idx];
			}else{
				y = ny;
				x = nx;
			}
			num++;
		}
		answer = Arrays.stream(snail)
			.flatMapToInt(Arrays::stream)
			.filter(value -> value != 0)
			.toArray();
		return answer;
	}
}
