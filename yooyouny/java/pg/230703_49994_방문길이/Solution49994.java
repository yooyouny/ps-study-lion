import java.util.*;
/*
좌표가 지나간 길을 체크하는 경우
이전 좌표와 다음좌표를 함께 저장해서 중복여부를 지속적으로 체크
저장할 때 이전좌표와 다음좌표를 바꿔서 저장해야 같은 선분 중복을 걸러낼 수 있음 양방향으로 저장

hashSet의 경우 객체를 넣게되면 hashcode, equals를 재구현해야하기 때문에 for문으로 체크하는 로직 필요했음

Static 메소드 만들기 복잡하면 그냥 main에서 구현체 만들고 접근할 것

* */
class Solution499994 {
	static int MAX_IDX = 10;//매직넘버 피하기 위해 상수화
	static int MIN_IDX = 0;
	static class Point {
		private int start;
		private int end;
		private int newStart;
		private int newEnd;
		static Set<Point> visited = new HashSet<>();
		static int firstVisitedCnt;// answer

		public Point() {}
		public Point(int start, int end, int newStart, int newEnd) {
			this.start = start;
			this.end = end;
			this.newStart = newStart;
			this.newEnd = newEnd;
		}

		public boolean isAddable(int start, int end, int newStart, int newEnd) {
			for (Point point : visited) {
				if (point.start == start && point.end == end && point.newStart == newStart && point.newEnd == newEnd)
					return false;
			}
			return true;
		}

		public void add(int start, int end, int newStart, int newEnd) {
			if (isAddable(start, end, newStart, newEnd)) {
				visited.add(new Point(start, end, newStart, newEnd));//양방향 체크
				visited.add(new Point(newStart, newEnd, start, end));
				firstVisitedCnt++;
			}
		}
	}
	private int[] move(char dir, int nowX, int nowY){
		switch (dir) {
			case 'L' -> {
				if (nowY - 1 >= MIN_IDX)
					nowY -= 1;
			}
			case 'R' -> {
				if (nowY + 1 <= MAX_IDX)
					nowY += 1;
			}
			case 'U' -> {
				if (nowX - 1 >= MIN_IDX)
					nowX -= 1;
			}
			case 'D' -> {
				if (nowX + 1 <= MAX_IDX)
					nowX += 1;
			}
		}
		return new int[]{nowX, nowY};
	}
	public int solution(String dirs) {
		Point point = new Point();
		int initX = 5, initY = 5;// 좌표 초깃값 설정할 때 0부터 세는지 확인하고 범위 넘어가는 값 체크할때 숫자 주의하기

		for (char dir : dirs.toCharArray()) {
			int[] now = move(dir, initX, initY);// 이동했으나 좌표 범위 벗어나는 경우엔 움직이지 않았음
			if(now[0] == initX && now[1] == initY) continue;// 해당 사항 걸러내주고
			point.add(initX, initY, now[0], now[1]);// visited set에 추가
			initX = now[0];// 이전좌표 갱신
			initY = now[1];
		}

		return point.firstVisitedCnt;
	}
}
