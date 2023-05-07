import java.util.*;
/*
* 시간복잡도: O(n^2) // 이중포문 사용
* 공간복잡도: O(n) // 입력값에 따라 저장하는 크기도 달라짐
* */

class Solution87377 {
	class Point { // 좌표값 저장을 위한 클래스 생성
		long x;
		long y;
		public Point(long x, long y){
			this.x = x;
			this.y = y;
		}
	}
	long[] temp; //좌표값 저장을 위한 임시변수, 다른 메소드에서 활용하기 위해 전역변수로 선언
	public Boolean findIntersection(int[] point1, int[] point2){ //교점이 있는지 확인하고 있으면 true, 없으면 false 반환하는 메소드
		long a = point1[0]; // input값이 최대 100000만 10만 * 10만이 될 수 있어서 long으로 저장
		long b = point1[1];
		long e = point1[2];
		long c = point2[0];
		long d = point2[1];
		long f = point2[2];

		long adbc = (a*d) - (b*c);
		long bfed = (b*f) - (e*d);
		long ecaf = (e*c) - (a*f);

		if(adbc == 0){// 두 선분이 평행하거나 일치하는 경우
			return false;
		}else if(bfed % adbc != 0 || ecaf % adbc != 0){ //정수인 좌표만 별로 표시
			return false;
		}else{ // 교점이 있으면서 정수인 좌표만 true 반환
			temp[0] = bfed / adbc; //x좌표
			temp[1] = ecaf / adbc; //y좌표
			return true;
		}
	}

	public String[] solution(int[][] line) {
		Set<Point> points = new HashSet<>(); //좌표가 중복이면 저장을 하지 않아도 되므로 HashSet으로 저장
		temp = new long[2];

		// 1. 정수로 표현된 교점 좌표 구해서 points에 저장
		for (int i = 0; i < line.length - 1; i++) { // n(line.length)개 중 서로 다른 2개를 뽑아 교점이 있는지 확인해야함
			for (int j = i + 1; j < line.length; j++) { // (0,1)과 (1,0)은 같으므로 다음 인덱스 부터 뽑음
				if(findIntersection(line[i], line[j])) // 정수로 표현된 교점 좌표 구하기
					points.add(new Point(temp[0], temp[1]));
			}
		}

		// 2. 별 표시 할 좌표값 계산을 위해 max, min 값 구하기
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

		//3. booelan 2차원 배열에 별 만들기. 별 좌표가 포함되어 있으면서 최소의 사각형으로 해야하기 때문에 max값에서 min값 빼주기
		boolean[][] stars = new boolean[(int)(maxY - minY + 1)][(int)(maxX - minX + 1)]; //배열 인덱스는 0부터 시작하므로 +1씩 해줌

		for(Point p : points){
			int x = (int)(p.x - minX); // 시작점을 0으로 만들어주기 위해 현재 좌표 값에서 제일 작은 값 빼주기
			int y = (int)(maxY - p.y); // y는 배열의 인덱스와 좌표 방향이 반대이므로 가장 큰 값에서 현재 좌표 값 빼주기
			stars[y][x] = true; // true로 별 위치 표시
		}

		//4. 별 좌표를 포함하는 최소 사각형을 만들어서 반환
		String[] answer = new String[stars.length]; //결과값을 String 배열 형태로 반환하기 위한 변수 선언
		int idx = 0; //answer 배열의 인덱스 값

		for(boolean[] star : stars){
			StringBuilder sb = new StringBuilder(); //새로운 문자열을 계속 생성하는 것보다 변경이 잦은 경우 StringBuilder로 사용
			for(boolean chk : star){
				if(chk)
					sb.append("*");
				else
					sb.append(".");
			}
			answer[idx++] = sb.toString(); // 문자열로 변환해서 저장
		}
		return answer;
	}
}
