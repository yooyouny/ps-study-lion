import java.util.*;
class Solution67258 {
	public int[] solution(String[] gems) {
		int[] answer = new int[2];
		Set<String> products = new HashSet<>(Arrays.asList(gems));// 전체 상품 종류 수 확인을 set으로 확인
		Map<String, Integer> gemCount = new HashMap<>();
		int start = 0;// 윈도우 시작 위치
		int range = gems.length;// 최소 범위 길이 저장

		for (int end = 0; end < gems.length; end++) {//윈도우 끝 위치를 증가시켜나감
			gemCount.put(gems[end], gemCount.getOrDefault(gems[end], 0) + 1);

			while (gemCount.size() == products.size()) {// 모든 보석 개수를 포함하고 있는 범위를 찾으면

				if (range > end - start) {// 기존 범위보다 작은 범위인 경우 answer 변수에 업데이트를 해주고
					range = end - start;// 기존 범위 값 업데이트
					answer[0] = start + 1;// 정답 배열에 저장
					answer[1] = end + 1;
				}

				gemCount.put(gems[start], gemCount.get(gems[start]) - 1);// start 증가해서 윈도우의 사이즈를 줄이기 전에 시작위치의 보석 개수를 감소
				if (gemCount.get(gems[start]) == 0) {// 0이 되어버리면 보석 목록에서 제거
					gemCount.remove(gems[start]);
				}

				start++;// 윈도우의 시작위치 증가
			}
		}

		return answer;
	}
}
