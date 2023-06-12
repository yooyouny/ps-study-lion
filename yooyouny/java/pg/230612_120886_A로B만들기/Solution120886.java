class Solution120886 {
	public int solution(String before, String after) {
		int[] beforeAlpha = new int[26];// 알파벳 소문자는 26가지
		int[] afterAlpha = new int[26];

		for(int i=0; i<before.length(); i++){
			beforeAlpha[before.charAt(i) - 'a']++;// 중복된 문자의 등장 횟수 저장
			afterAlpha[after.charAt(i) - 'a']++;
		}
		for(int i=0; i<beforeAlpha.length; i++){
			if(beforeAlpha[i] != afterAlpha[i])// 횟수가 같지 않으면 순서를 바꿔서 문자열을 조합할 수 없으므로 0 리턴
				return 0;
		}
		return 1;
	}
}
