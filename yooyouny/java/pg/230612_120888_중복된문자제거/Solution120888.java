import java.util.*;
import java.util.stream.*;
class Solution120888 {
	public String solution(String my_string) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<my_string.length(); i++){
			char ch = my_string.charAt(i);
			if(sb.indexOf(String.valueOf(ch)) == -1)// 해당 문자의 인덱스가 없으면 -1을 반환하므로 중복제거됨
				sb.append(ch);
		}
		return sb.toString();
	}
}
