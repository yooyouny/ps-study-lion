package java1.boj.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4396 {
	static char[][] map;
	static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
	static int[] dy = {-1, 1, 0, 0, -1, 1, 1, -1};
	private static void findMine(int r, int c, int n){
		int cnt = 0;
		for(int i=0; i<8; i++){
			int nx = r + dx[i];
			int ny = c + dy[i];
			if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
			if(map[nx][ny] == '*') cnt++;
		}
		map[r][c] = Character.forDigit(cnt, 10);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for(int i=0; i<n; i++){
			map[i] = br.readLine().toCharArray();
		}
		boolean mine = false;
		for(int i=0; i<n; i++){
			char[] line = br.readLine().toCharArray();
			for(int j=0; j<n; j++){
				if(line[j] == 'x') {
					if(map[i][j] != '*')
						findMine(i, j, n);
					else mine = true;
				}
			}
		}
		if(!mine) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == '*') {
						map[i][j] = '.';
					}
				}
			}
		}
		for(char[] line : map){
			for(char c : line){
				System.out.print(c);
			}
			System.out.println();
		}
	}

}

