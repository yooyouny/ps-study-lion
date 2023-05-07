import java.util.*;
class Solution12916 {
	boolean solution(String s) {
		int cnt = 0;// 개수를 비교해야 하므로 cnt 변수 선언
		s = s.toUpperCase();// 대문자로 변환해버리고
		for(int i=0; i<s.length(); i++){// i로 s문자열 인덱스 접근
			if(s.charAt(i) == 'P') cnt++;// p일경우 cnt 증가
			if(s.charAt(i) == 'Y') cnt--;// y일경우 cnt 감소로
		}
		return cnt == 0; // 둘의 개수가 같아서 0이 되면 true 리턴
	}
}
