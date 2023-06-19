package java.boj.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main11559 {
	static char[][] map, copy;
	static boolean[][] chk;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};

	static int cnt;

	private static void dfs(int r, int c, Set<int[]> points){
		chk[r][c] = true;

		// 기저조건을 모르겠어.. 여기는 개수 반환만 하는 곳이야

		for(int i=0; i<4; i++) {
			int nr = r + dy[i];
			int nc = c + dx[i];
			if(nr>=0 && nr<12 && nc>=0 & nc<6 && chk[nr][nc] == false && map[r][c] == map[nr][nc]){
				points.add(new int[]{nr, nc});
				cnt++;
				dfs(nr, nc, points);
			}
		}

	}
	private static void pop(Set<int[]> points){
		for(int[] point : points){
			int r = point[0];
			int c = point[1];
			map[r][c] = '.';
			copy[r][c] = '.';
		}
	}
	private static void down(){
		for(int i=0; i<6; i++){
			String line = "";
			for(int j=11; j>=0; j--){
				if(copy[j][i] != '.')
					line+=copy[j][i];
			}
			if(line.length() == 0 || line.equals("............")) continue;
			while(line.length() <= 12){
				line += '.';
			}
			for(int j=11; j>=0; j--)
				copy[j][i] = line.charAt(11 - j);
		}
	}
	private static boolean find(int column){
		for(int i=11; i>=0; i--){
			if(map[i][column] != '.'){
				chk = new boolean[12][6];
				Set<int[]> points = new HashSet<>();
				points.add(new int[]{i, column});
				cnt = 1;
				dfs(i, column, points);
				if(cnt >= 4) {
					pop(points);
					return true;
				}
			}
		}
		return false;
	}
	private static void printMap(){
		for(char[] ch : map){
			for (char c : ch) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[12][6];
		copy = new char[12][6];
		int answer = 0;
		for(int i=0; i<12; i++){
			map[i] = br.readLine().toCharArray();
		}
		copy = map;
		while(true){
			boolean bp = false;
			for(int i=0; i<6; i++){
				if(find(i)){
					bp = true;
				}
				if(i == 5 && !bp) break;
			}
			if(!bp) break;
			down();
			map = copy;
			answer++;
		}
		System.out.println(answer);
	}
}
