import java.util.*;

class Solution {
//교점을 기록할 클래스 Point
    private static class Point {

        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
//배열 리스트 형태로 Point 인스턴스 저장
    List<Point> pointOfIntersection = new ArrayList<>();
//전체 맵크기를 구하기 위한 최대 최솟값
    Long minX = Long.MAX_VALUE;
    Long minY = Long.MAX_VALUE;
    Long maxX = Long.MIN_VALUE;
    Long maxY = Long.MIN_VALUE;


    public String[] solution(int[][] line) {
        String[] answer = {};
//교점을 구하기 위해 각 일차함수의 coeff를 다른 일차함수와 비교한다.
        for (int i = 0; i < line.length - 1; i++) {
	//j는 i보다 1 큰값부터 시작해야 중복 비교를 안할 수 있다.	
            for (int j = i + 1; j < line.length; j++) {
                getPointOfIntersection(line[i], line[j]);
            }
        }
	//교점 x,y의 최대 최소값을 구하기 위한 함수
        getMinPointAndMaxPoint();
//맵 크기 0을 포함하기 때문에 +1
        long height = maxY - minY + 1;
        long width = maxX - minX + 1;
//답을 저장할 String 배열 객체 생성
        answer = new String[(int) height];
//x, y위치에 별표를 찍기 위한 Boolean 배열
        boolean[][] board = new boolean[(int) height][(int) width];
//boolean 배열에 교점 true로 기록
        for (Point point : pointOfIntersection) {
            int x = (int) (point.x - minX);
            int y = (int) (maxY - point.y);

            board[y][x] = true;
        }
        int i = 0;
	//StringBuilder로 true인 곳에 *찍기
        for (boolean[] booleans : board) {
            StringBuilder sb = new StringBuilder();
            for (boolean b : booleans) {
                if (b) {
                    sb.append("*");
                } else {
                    sb.append(".");
                }
            }
	    //한줄 다 찍고 2차원 배열의 행에 저장
            answer[i++] = sb.toString();
        }

        return answer;
    }
//교점의 최대 최솟값 구하는 함수
    private void getMinPointAndMaxPoint() {
        for (Point point : pointOfIntersection) {
            minX = Math.min(minX, point.x);
            minY = Math.min(minY, point.y);
            maxX = Math.max(maxX, point.x);
            maxY = Math.max(maxY, point.y);
        }
    }
//교점 구하는 함수
    private void getPointOfIntersection(int[] A, int[] B) {
        long a = A[0];
        long b = A[1];
        long c = B[0];
        long d = B[1];
        long e = A[2];
        long f = B[2];
//분모 구하는 공식
        long denominator = a * d - b * c;
//0이면 완전히 평행하거나 아예 겹치거나
//문제에서는 이런 경우 안나온다고 했음
	if (denominator == 0) {
            return;
        }
//분자 구하는 공식
        long xNumerator = b * f - e * d;
        long yNumerator = e * c - a * f;
// 정수인 것만 교점에 기록
        if (xNumerator % denominator != 0 || yNumerator % denominator != 0) {
            return;
        }

        long x = xNumerator / denominator;
        long y = yNumerator / denominator;
//배열리스트에 저장
        pointOfIntersection.add(new Point(x, y));
    }
}
