import java.util.*;
class Solution86051_2 {
	public int solution(int[] numbers) {
		int answer = 0;
		Set<Integer> number = new HashSet<>();// 중복제거를 위해 Set사용
		for(int n : numbers){
			number.add(n);
		}
		for(int i=1; i<=9; i++){
			if(!number.contains(i))// 없는 숫자들만 더함
				answer += i;
		}
		return answer;
	}
}
