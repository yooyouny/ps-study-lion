import java.util.*;
/*
* 1. 온 좌표가 정수인 것 찾기
* 	- 나머지가 0이 아닌 것으로 제외하기
* 	- 형변환했을떄 값과 본래 값이 같은지 확인하기 (int)x == x
* 2. input 값에 연산이 있으면 주의깊게 보기
* 	- 최대 input값에 * 연산이 있어서 long으로 해줬어야함 *** 주의
* 3. 4차원 좌표 1차원으로 변환하기
* 	- 좌표 크기를 구하기 위해 가로 세로 최대 최소 값 다 구하기
* 	- result[maxY - p.y][p.y - minX] *** 외우기
* 4. 최대최소 값 구할때 초기값을 0이 아닌 Long.MAX_VALUE로 잡기 *** 주의
* 5. long으로 형변환 된 값을 구하고 싶으면 연산할때마다 (long)값으로 변환해준다음 연산해야함 ***주의
* 6. 배열 초기화 Arrays.fill(arr, '문자');
* 	- 2차원 배열 초기화 Arrays.stream().forEach(row -> Arrays.fill(arr, '문자')
* 7. char 2차원 배열 String 1차원 배열로 변환
* 	- Arrays.stream(charArr).map(String::valueOf).toArray(String[]::new)
* */
class Solution_2{
	class Point{
		long x;
		long y;
		public Point(long x, long y){
			this.x = x;
			this.y = y;
		}
	}
	private List<Point> getPoint(int[][] lines){
		List<Point> points = new ArrayList<>();
		for(int i=0; i<lines.length-1; i++) {
			for (int j = i + 1; j < lines.length; j++) {
				long a = lines[i][0];
				long b = lines[i][1];
				long e = lines[i][2];
				long c = lines[j][0];
				long d = lines[j][1];
				long f = lines[j][2];

				long adbc = (a * d) - (b * c);
				long bfed = (b * f) - (e * d);
				long ecaf = (e * c) - (a * f);

				if (adbc == 0 || bfed % adbc != 0 || ecaf % adbc != 0)
					continue;

				points.add(new Point(bfed / adbc, ecaf / adbc));
			}
		}
		return points;
	}
	private char[][] printStar(List<Point> points){
		long minX = Long.MAX_VALUE;
		long minY = Long.MAX_VALUE;
		long maxX = Long.MIN_VALUE;
		long maxY = Long.MIN_VALUE;

		for(Point p : points){
			minX = Math.min(minX, p.x);
			minY = Math.min(minY, p.y);
			maxX = Math.max(maxX, p.x);
			maxY = Math.max(maxY, p.y);
		}

		int width = (int)(maxX - minX + 1);
		int height = (int)(maxY - minY + 1);

		char[][] result = new char[height][width];

		Arrays.stream(result)
			.forEach(row -> Arrays.fill(result, '.'));

		for(Point p : points){
			result[(int)(maxY - p.y)][(int)(p.x - minX)] = '*';
		}

		return result;
	}
	public String[] solution(int[][] line) {

		List<Point> points = getPoint(line);
		char[][] stars = printStar(points);

		String[] answer = new String[stars.length];

		return Arrays.stream(stars)
			.map(String::valueOf)
			.toArray(String[]::new);
	}
}
