import java.util.*;
class Solution43236 {
	private boolean isValid(int mid, int[] rocks, int n){
		int previous = 0;// 이전바위
		int cnt = 0;
		for(int rock : rocks){
			if(rock - previous < mid){// 기준 거리보다 작은 바위는 제거
				cnt++;
			}else{
				previous = rock;// 이전 바위 위치를 갱신
			}
		}
		return cnt <= n;// 제거한 바위가 목표보다 작으면 true
	}
	public int solution(int distance, int[] rocks, int n) {

		rocks = Arrays.copyOf(rocks, rocks.length + 1);
		rocks[rocks.length - 1] = distance;// rocks배열에 도착지점까지 추가

		Arrays.sort(rocks);// 이분탐색을 위한 정렬

		int start = 1;
		int end = distance;

		while(start<=end){
			int mid = (start + end) / 2;// 바위 사이의 최소 거리 설정

			if(isValid(mid, rocks, n)){// 해당 거리를 최대로 만들기 위해 없애야 할 바위 계산
				start = mid+1;// 거리를 늘림
			}else{// 제거 해야할 바위가 목표 수보다 클 경우
				end = mid-1;// 거리를 줄임
			}
		}

		return end;
	}
}
