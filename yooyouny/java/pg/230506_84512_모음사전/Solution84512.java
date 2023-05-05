class Solution84512 {
	//재귀함수에서 이전호출에서 변경한 값을 계속 유지하기 위해 클래스 변수 사용
	int answer, cnt; // 결과값을 저장할 answer, 단어 순서를 저장할 cnt

	public void findWordCnt(StringBuilder sb, char[] words, String word){
		if(sb.toString().equals(word)){// 조합한 sb의 문자열이 타겟 문자열과 같으면
			answer = cnt;// 결과 변수에 담고 함수 종료
			return;
		}
		if(sb.length() == 5) return;// 조합한 문자열의 최대 길이는 5이므로 길이가 5면 함수 종료

		for(int i=0; i<5; i++){// words idx 접근
			sb.append(words[i]);// 문자를 append하고
			cnt++;// 해당 문자 조합의 번호를 증가
			findWordCnt(sb, words, word);// 다음 문자 append를 위한 재귀 호출
			sb.deleteCharAt(sb.length() - 1);//최대길이로 채우고 나면 뒤의 글자를 지우고 다음 글자를 append하므로
		}
	}
	public int solution(String word) {
		char[] words = {'A', 'E', 'I', 'O', 'U'};
		//계속 새로운 문자열을 생성해야하므로 sb, 문자열 요소가 담긴 배열, target 문자열
		findWordCnt(new StringBuilder(), words, word);
		return answer;
	}
}
