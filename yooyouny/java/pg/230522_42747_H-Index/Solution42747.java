import java.util.*;
class Solution42747 {
	public int solution(int[] citations) {
		Arrays.sort(citations);// h번 이상 인용되었는지 확인하기 위해서 오름차순으로 정렬

		for (int i = 0; i < citations.length; i++) {
			int h = citations.length - i; // 전체 논문 크기 - 현재위치 = 현재 인용 횟수보다 크거나 같은 인용 횟수를 가진 논문 개수

			if (citations[i] >= h)// 현재 인용 횟수가 h보다 크거나 같으면 최대 h-index
				return h;
		}
		return 0;// 루프를 탐색해도 h-index를 찾지 못한 경우
	}
}
