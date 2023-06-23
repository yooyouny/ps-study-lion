package java.boj.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main18111 {
	static int B, N, M;
	static int[][] ground;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		ground = new int[N][M];
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

		for(int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++){
				ground[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, ground[i][j]);
				min = Math.min(min, ground[i][j]);
			}
		}

		int answer = Integer.MAX_VALUE, height = 0;
		for(int i=max; i>=min; i--){// 땅의 높이 범위대로 깎거나 높이기
			int time =0;
			int target = i;
			int inventory = B;

			for(int r=0; r<N; r++){
				for(int c=0; c<M; c++){
					if(ground[r][c] != target){
						int diff = Math.abs(target - ground[r][c]);// 차이만큼 땅을 높이거나 깎을 예정
						if(target < ground[r][c]){// 제거해서 인벤토리 채우기 1번 작업
							inventory += diff;
							time += (2*diff);
						}else{// 인벤토리에서 블록 꺼내서 채우기 2번 작업
							inventory -= diff;
							time += diff;
						}
					}
				}
			}
			if(inventory < 0){//블럭이 없어서 인벤토리가 마이너스인 작업은 갱신작업에서 제외시켜야 함
				continue;
			}
			if(time < answer) {
				answer = time;
				height = target;// 기준으로 삼은 높이로 땅을 맞출거니까
			}
		}

		System.out.println(answer + " " +height);
	}
}
