import java.util.Arrays;

public class Solution12915_2 {
	public String[] solution(String[] strings, int n) {
		Arrays.sort(strings, (s1, s2) -> {// lambda 표현식으로 작성
			if (s1.charAt(n) == s2.charAt(n)) {
				return s1.compareTo(s2);
			}
			return s1.charAt(n) - s2.charAt(n);
		});
		return strings;
	}
}
