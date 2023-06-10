import java.util.*;
class Solution86051 {
	public int solution(int[] numbers) {
		return 45-Arrays.stream(numbers).sum();// 전체 숫자에서 있는 숫자만 빼면 없는 숫자의 합
	}
}
