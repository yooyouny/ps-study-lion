import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Solution72412 {
	private void forEachKey(int index, String prefix, String[] tokens, Consumer<String> action) {
		// 재귀호출로 index를 늘려가며 구분자 -로 모든 요소의 조합 생성
		if (index == tokens.length - 1) {
			action.accept(prefix);
			return;
		}

		forEachKey(index + 1, prefix + tokens[index], tokens, action);// 문자열에 token값 추가
		forEachKey(index + 1, prefix + "-", tokens, action);// 구분자로 - 추가
	}

	private Map<String, List<Integer>> buildScoresMap(String[] info) {
		Map<String, List<Integer>> scoresMap = new HashMap<>();// 토큰 조합 문자열을 key, 점수를 value로 표시

		for (String s : info) {
			String[] tokens = s.split(" ");
			int score = Integer.parseInt(tokens[tokens.length - 1]);
			forEachKey(0, "", tokens, key -> {
				scoresMap.putIfAbsent(key, new ArrayList<>());// 해당 키가 없으면 새로운 리스트 추가
				scoresMap.get(key).add(score);// 이미 있는 경우엔 각 키에 해당하는 점수 추가
			});
		}

		scoresMap.values().forEach(Collections::sort);// 점수 오름차순으로 정렬

		return scoresMap;
	}

	private int binarySearch(int score, List<Integer> scores) {
		int start = 0; // 이진탐색을 위한 start, end값 인덱스 설정
		int end = scores.size() - 1;

		while (end > start) {
			int mid = (start + end) / 2;

			if (scores.get(mid) >= score) {// score보다 크거나 같은 가장 작은 값
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return scores.get(start) < score ? scores.size() : start; //인덱스 값 반환
	}

	private int count(String query, Map<String, List<Integer>> scoresMap) {
		String[] tokens = Arrays.stream(query.split(" "))
			.filter(data -> !data.equals("and"))
			.toArray(String[]::new);

		String key = String.join("", Arrays.copyOf(tokens, tokens.length - 1));// 점수전까지의 문자열

		if (!scoresMap.containsKey(key)) return 0;// 해당 키가 없으면 서치할 필요가 없음
		List<Integer> scores = scoresMap.get(key);// 해당 키에 해당하는 점수 리스트 반환

		int score = Integer.parseInt(tokens[tokens.length - 1]);

		return scores.size() - binarySearch(score, scores);// 해당 점수보다 zmrjsk 크거나 같은 사람 수를 반환
	}

	public int[] solution(String[] info, String[] query) {
		Map<String, List<Integer>> scoresMap = buildScoresMap(info);// 지원자 정보의 모든 토큰 조합을 생성

		int[] answer = Arrays.stream(query)
			.mapToInt(q -> count(q, scoresMap))
			.toArray();

		return answer;
	}
}
