class Solution12930 {
	public static String solution(String s) {
		StringBuilder sb = new StringBuilder();// 문자열 더하기 연산이 잦으면 StringBuilder로
		int wordIdx = 0; // 공백을 기준으로 짝/홀수 인덱스를 다시 갱신해주기 위한 변수 선언
		for(int i=0; i<s.length(); i++){
			char ch = s.charAt(i);
			if(ch == ' '){// 공백을 만나면 단어의 인덱스를 리셋해주고
				wordIdx = 0;
				sb.append(" ");// 공백은 변환없이 넣어줌
				continue;
			}
			if(wordIdx % 2 == 0){// 단어의 짝수번째 인덱스면 대문자로
				sb.append(Character.toString(ch).toUpperCase());
			}else{// 단어의 홀수번째 인덱스면 소문자로
				sb.append(Character.toString(ch).toLowerCase());
			}
			wordIdx++;// 단어의 인덱스를 증가시켜줌
		}
		return sb.toString();
	}
}
