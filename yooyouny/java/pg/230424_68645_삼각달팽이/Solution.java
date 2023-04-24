import java.util.*;
/*
* 시간복잡도: O(n^2)
* 공간복잡도: O(n^2)
* */
class Solution {
	public int[] solution(int n) {
		int size = (n+1) * n / 2; //결과 배열의 크기는 1부터 n까지의 합이므로

		int[] answer = new int[size];
		int idx = 0; //answer 배열의 인덱스

		int[][] snail = new int[n][n]; //실제로 값을 채울 이차원 배열
		int num = 1;

		int y = 0, x = 0, nx = 0, ny = 0;
		int angle = 0; //dx, dy배열의 인덱스

		//아래, 오른쪽, 대각선 위 순으로 진행
		int[] dx = {0, 1, -1};
		int[] dy = {1, 0, -1};

		while(num<=size){
			snail[y][x] = num;
			ny = y + dy[angle]; //다음 위치로 이동
			nx = x + dx[angle];

			if(0 <= ny && ny < n && 0 <= nx && nx <= ny && snail[ny][nx] == 0){//바뀐 좌표가 snail 범위 내에 있으면서 초깃값인 0인 상태일때 만값을 채워야 하므로
				y = ny; //새로운 위치로 갱신
				x = nx;
			}else{ // 이미 해당 방향으로는 값을 다 채웠을 경우
				angle = (angle + 1) % 3; // dx, dy 인덱스 증가
				y = y + dy[angle];
				x = x + dx[angle];
			}
			num++; // 다음으로 채울 값 증가
		}

		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(snail[i][j] == 0) // 값이 왼쪽으로 몰려있기 때문에 0을 만나면 다음 배열로 접근
					break;
				else
					answer[idx++] = snail[i][j]; // 결과 배열에 값을 넣어줌
			}
		}
		return answer;
	}
}
