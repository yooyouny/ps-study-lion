import java.util.*;
class Solution {
   
	public static String solution(int[] numbers) {
        //문자열로 변환
		String[] sArray = Arrays.stream(numbers)
			.mapToObj(String::valueOf)
			.toArray(String[]::new);
        //둘이 더했을때 내림차순으로 정렬해야 숫자상 큰값 
		Arrays.sort(sArray, (o1, o2)-> {
			String s1 = o1 + o2;
			String s2 = o2 + o1;
			return s2.compareTo(s1);	
		});
		String answer = "";
        //문자배열 모두 합치기
		for (String s : sArray) {
			answer += s;
		}
        //앞자리가 0이면 그냥 0반환
        if(answer.charAt(0) == '0')
            return "0";
		return answer;
	}
}
