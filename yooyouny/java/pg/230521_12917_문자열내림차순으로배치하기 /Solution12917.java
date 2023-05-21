import java.util.*;
class Solution12917 {
	public String solution(String s) {
		char[] arr = s.toCharArray();// Arrays.sort() 활용위해 배열로 전환
		Arrays.sort(arr);// 아스키 순서로 정렬, 대문자 -> 소문자 우선순위로 정렬됨
		StringBuilder sb = new StringBuilder();// reverse 사용 위해 빌더 생성
		sb.append(new String(arr));// 배열을 string으로 전환 후 append
		return sb.reverse().toString();// reverse후 string으로 전환
	}
}
