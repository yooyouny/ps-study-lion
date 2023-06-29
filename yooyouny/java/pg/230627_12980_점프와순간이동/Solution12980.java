/**
 * top-down방식으로 접근해야하는 문제, 생각을 못하면 못푸는 문제
 * 도착지점에서 생각하면 순간이동으로 왔으면 계속 순간이동으로 가능, 순간이동은 배터리를 사용하지 않음
 * 만약 n이 짝수일 경우 반절 거리부터 도착지까지는 순간이동으로만 계속 갈 수 있는 거리임
 * 따라서 짝수일 경우 반절처리를 해주고 홀수 인 경우엔 점프처리, 배터리증가 연산으로 처리해줌
 */

public class Solution12980 {
	public int solution(int n) {
		int usage = 0;

		while(n>0){
			if(n % 2 == 0){
				n /= 2;
			}else{
				n--;
				usage++;
			}
		}

		return usage;
	}
}
