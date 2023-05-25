import java.util.*;

class Solution72411 {
	public String[] solution(String[] orders, int[] course) {
		List<String> answer = new ArrayList<>();// 요청한 코스 길이의 최대 등장횟수 조합을 저장할 리스트 생성

		for (int courseLength : course) {// 코스 길이만큼 순회하면서 해당 길이의 메뉴 조합을 구함
			Map<String, Integer> menus = new HashMap<>();
			for (String order : orders) {
				char[] menuArr = order.toCharArray();
				Arrays.sort(menuArr);// 단품메뉴 조합생성을 위해 변환 후 정렬
				combination(menuArr, new boolean[menuArr.length], 0, courseLength, "", menus);// 해당 코스 길이만큼의 조합을 생성하여 menus에 저장
			}

			List<Map.Entry<String, Integer>> entries = new ArrayList<>(menus.entrySet());// 해당 조합의 key, value 접근을 위해 map.entirySet으로 저장
			entries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));//맵의 값인 등장횟수를 기준으로 내림차순으로 정렬


			if (!entries.isEmpty()) {
				int maxCount = entries.get(0).getValue();// 최대 등장횟수와 같은 조합들이 있을 경우 저장하기 위해 미리 저장
				if (maxCount >= 2) {// 최소 두번이상 주문된 조합만 포함
					for (Map.Entry<String, Integer> entry : entries) {
						if (entry.getValue() == maxCount) {// 최대 등장 횟수와 같은 조합이 있는 경우 추가
							answer.add(entry.getKey());
						} else {
							break;
						}
					}
				}
			}
		}

		Collections.sort(answer);// 오름차순 정렬 후
		return answer.toArray(new String[0]); // 문자열 배열로 반환
	}
	// 조합해야할 메뉴가 들어있는 배열, 조합생성을 위한 visited배열, 시작 인덱스, 조합할 길이, 생성한 조합 문자열, 조합을 저장할 맵
	private void combination(char[] menuArr, boolean[] visited, int start, int length, String current, Map<String, Integer> menus) {
		if (length == 0) {// 생성한 조합이 지정한 길이와 같으면
			menus.put(current, menus.getOrDefault(current, 0) + 1);// 해당 조합 문자열을 저장하면서 등장한 횟수도 함께 저장
			return;
		}

		for (int i = start; i < menuArr.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(menuArr, visited, i + 1, length - 1, current + menuArr[i], menus);
				visited[i] = false;
			}
		}
	}
}
