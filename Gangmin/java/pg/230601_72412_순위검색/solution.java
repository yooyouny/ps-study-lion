import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Solution {
	static HashMap<String, List<Integer>> map;

	public static int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		map = new HashMap<String, List<Integer>>();

		//combination을 만들기 위해 info쪼개기
		for (int i = 0; i < info.length; i++) {
			String[] cand = info[i].split(" ");
			//조합 구해서 map에 넣기
			comb(cand, "", 0);
		}

		for (String key : map.keySet())
			Collections.sort(map.get(key));

		for (int i = 0; i < query.length; i++) {
			query[i] = query[i].replaceAll(" and ", "");
			String[] q = query[i].split(" ");
			//key에 해당하는 조합이 없으면 0, 있으면 이분 탐색
			answer[i] = map.containsKey(q[0]) ? binarySearch(q[0], Integer.parseInt(q[1])) : 0;
		}

		return answer;
	}

	// 이분 탐색 -> 리스트에서 이분 탐색
	private static int binarySearch(String key, int score) {
		List<Integer> list = map.get(key);
		int L = 0, R = list.size() - 1;
		while (L <= R) {
			int mid = (L + R) / 2;
			if (list.get(mid) < score)
				L = mid + 1;
			else
				R = mid - 1;
		}
	//
		return list.size() - L;
	}

	// info가 포함될 수 있는 문장 -> info의 각요소로 만드는 조합
	private static void comb(String[] cand, String str, int depth) {
		if (depth == 4) {
			//map에 값이 없으면 리스트 생성후 put
			if (!map.containsKey(str)) {
				List<Integer> list = new ArrayList<Integer>();
				map.put(str, list);
			}//있으면 생성된 리스트에 저장
			map.get(str).add(Integer.parseInt(cand[4]));
			return;
		}
		//"-"가 depth에 들어가는 경우
		comb(cand, str + "-", depth + 1);
		//depth에 cand[depth]가 들어가는 경우
		comb(cand, str + cand[depth], depth + 1);
	}
}
