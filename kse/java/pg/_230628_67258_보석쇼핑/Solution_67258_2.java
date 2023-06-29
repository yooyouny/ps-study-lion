package java.pg._230628_67258_보석쇼핑;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution_67258_2 {
	/**
	 * 1. 쇼핑한 보석의 종류와 구매한 개수를 담아주는 map을 만들어 준다.
	 * 		구매한 개수 -> 구매했는지 여부만 담아줄 경우 startIdx 에서부터 몇 번이나 구매했는지 알 수 없다.
	 * 2. 시작 좌표를 0으로 두고 모든 전시대를 확인한다 (for)
	 * 3. 전열대를 한번 돌 때마다 해당 보석의 구매횟수를 +1해준다.
	 * 4.  shopping.get(gems[startIdx]) > 1
	 * 		처음 구매 했을 때의 value가 1이기 때문에 1이상이면 시작 지점이후에 한 번 더 구매했다는 의미.
	 * 	    시작 idx를 유지해줄 필요가 없기 때문에 startidx를 +1 해주고,
	 * 	    구매 횟수를 1 감소 시켜준다.
	 * 	    -> [ A  C  C  C  B  'A' ]  C  B  D ...
	 * 	    -> A [ C(시작) 'C' C  B  A ]  C  B ...
	 * 	    -> A  C  [ C  C  B  A ]  C  B  D ...
	 * 	    -> A  C  C  [ C  B  A ]  C  B  D ...
	 */
	public int[] solution(String[] gems) {
		int[] answer = {};
		int kinds = new HashSet<>(Arrays.asList(gems)).size();

		//1. 쇼핑한 보석의 종류와 구매한 개수를 담아주는 map을 만들어 준다.
		// 	 * 		구매한 개수 -> 구매했는지 여부만 담아줄 경우 startIdx 에서부터 몇 번이나 구매했는지 알 수 없다.
		Map<String, Integer> shopping = new HashMap<>();

		int startIdx = 0;
		int minLength = 100_001;
		//2. 시작 좌표를 0으로 두고 모든 전시대를 확인한다 (for)
		for (int endIdx = 0; endIdx < gems.length; endIdx++) {

			//이미 구매한 상태에서 보석을 새로 구매한 경우 = 기존 값 + 1
			//구매한 적이 없는 보석을 구매한 경우 = 0 + 1
			String gem = gems[endIdx];
			shopping.put(gem, shopping.getOrDefault(gem, 0) + 1);

			// 처음 구매했을 때의 value 가 1이기 때문에 1이상이면 startIdx 이 후에 한 번 더 구매했다는 의미.
			// startIdx 를 유지해줄 필요가 없기 때문에 startidx 를 +1 해주고, 구매 횟수를 1 감소 시켜준다.
			// 이 작업을 startIdx 의 보석이 endIdx 까지 한 번 구매되었을 떄까지 반복한다.
			while (shopping.get(gems[startIdx]) > 1){
				shopping.put(gems[startIdx], shopping.get(gems[startIdx]) -1 );
				startIdx++;
			}


			if(shopping.size() == kinds){			//shopping 의 사이즈가 kinds 와 같다면 모든 보석을 구매했다는 뜻
				int length = endIdx - startIdx;
				if(minLength > length){				//최소길이인 경우 answer를 갱신해준다.
					minLength = length;
					answer = new int[]{startIdx + 1, endIdx + 1};
				}

			}
		}

		return answer;
	}
}
