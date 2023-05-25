import java.util.*;
class Solution42746 {
	public String solution(int[] numbers) {

		String[] arr = Arrays.stream(numbers)
			.mapToObj(String::valueOf)// 문자열 형태로 이어붙이기 위해 String으로 변환
			.sorted((s1, s2) -> {// 이어붙인 형태에서 큰 수를 앞으로 정렬, 내림차순으로 정렬
				return (s2+s1).compareTo(s1+s2);
			})
			.toArray(String[]::new);

		if(arr[0].equals("0")) return "0"; // 정렬된 가장 큰 수가 0이면 모든 수가 0이므로 0 반환

		return String.join("", arr);// 문자열 배열을 결합해 결과로 리턴
	}
}

