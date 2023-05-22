import java.util.*;
class Solution12915 {
	public String[] solution(String[] strings, int n) {
		Arrays.sort(strings, new Comparator<String>() {//
			@Override
			public int compare(String first, String second) {
				if (first.charAt(n) == second.charAt(n))// 첫번째 문자가 같으면
					return first.compareTo(second);//사전 순 대로 정렬. 반환값이 양수면 seoncd가 먼저 음수면 first가 먼저
				else// 문자가 다르면
					return first.charAt(n) - second.charAt(n);// 첫번째 문자 기준으로 사전 순 정렬. 반환값이 양수면 seoncd가 먼저 음수면 first가 먼저
			}
		});

		return strings;//정렬된 문자배열 반환
	}
}
