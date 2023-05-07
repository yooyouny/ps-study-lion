class Solution12918 {
	public boolean solution(String s) {
		if(s.length() != 4 && s.length() != 6)// 길이가 4, 6이 아니면 false return
			return false;
		for(int i=0; i<s.length(); i++){// 문자열 인덱스 접근을 위한 for문
			if(s.charAt(i) >= 'A')// 하나라도 문자면 false 리턴
				return false;
		}
		return true;// 위의 경우가 모두 해당이 안되면 true 리턴
	}
}
