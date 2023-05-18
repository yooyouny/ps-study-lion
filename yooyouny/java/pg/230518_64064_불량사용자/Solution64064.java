import java.util.*;
import java.util.regex.Pattern;

class Solution64064 {
	Set<Set<String>> ids;// 중복데이터를 자동으로 배제하기 위해 set자료형 사용

	private void dfs(HashSet<String> users, String[][] banIds, int idx, int depth){
		// 제재 아이디 목록 저장, 제재 아이디에 일치하는 유저 아이디 목록 저장, 탐색중인 제재 아이디 인덱스, 처리한 제재 아이디 개수
		if(depth == banIds.length){// 마지막 제재 아이디에 도달했을때가 기저조건
			ids.add(new HashSet<>(users));// 생성된 조합을 최종 결과에 add
			return; // 재귀함수 종료
		}
		for(String id : banIds[idx]){
			if(users.contains(id)) continue;// 응모자 아이디가 중복해서 제재 아이디 목록으로 들어가는 일을 방지
			users.add(id);// 해당 id를 조합에 추가
			dfs(users, banIds, idx + 1, depth + 1);// 재귀호출, 다음 제재 아이디 접근을 위해 idx, depth 증가
			users.remove(id);// 재귀호출이 완료되면 해당 아이디를 제거하여 다음 호출에서 새롭게 조합 탐색
		}
	}

	private boolean match(String check_id, String user_id){
		if(check_id.length() != user_id.length())
			return false;
		if(Pattern.matches("^\\*+$", check_id))
			return true;
		for(int i=0; i<check_id.length(); i++){
			char ch = check_id.charAt(i);
			if(ch == '*') continue;
			if(ch != user_id.charAt(i))
				return false;
		}
		return true;
	}

	public int solution(String[] user_id, String[] banned_id) {
		ids = new HashSet<>();

		// 각 제재 아이디에 일치하는 유저 아이디들 뽑기
		//[[], [], []]
		String[][] banIds = Arrays.stream(banned_id)
			.map(banned -> banned.replace('*', '.'))// 제재 아이디 규칙의 *을 임의의 문자를 의미하는 .으로 변경 -> 정규표현식에 맞도록 수정
			.map(banned -> Arrays.stream(user_id)
				.filter(id -> id.matches(banned))// 제재 아이디와 일치하는 유저 아이디만 필터링 하여 새로운 배열로 반환
				.toArray(String[]::new))
			.toArray(String[][]::new);

		dfs(new HashSet<>(), banIds, 0, 0);// 제재 아이디에 조합을 넣기 위한 set, 제재 아이디에 일치하는 유저 아이디들 목록, banIds를 접근하기 위한 idx,

		return ids.size();// 모든 경우의 수 반환
	}
}
