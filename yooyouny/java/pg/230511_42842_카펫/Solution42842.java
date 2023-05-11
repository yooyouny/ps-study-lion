class Solution42842 {
	/*
	* 전체 넓이가 나오는 가로 * 세로 조합을 구함 == 약수를 구함
	* 해당 약수 조합을 -2한 곱이 yellow의 개수와 같으면 탐색 종료
	* */
	public int[] solution(int brown, int yellow) {
		int[] answer = {0, 0};
		int total = brown + yellow;

		for(int i=3; i<=Math.sqrt(total); i++){ // 세로의 최소길이는 3부터 시작하고, 약수는 제곱근까지만 검사하면 충분함
			if(total % i == 0){// 세로 값이 약수이면서
				int j = total / i;// 세로 길이에 맞는 가로값을 구하고
				if((i-2) * (j-2) == yellow){// 해당 조건이 yellow의 개수와 같으면 정답
					answer[0] = j; // 가로
					answer[1] = i; // 세로
					break;
				}
			}
		}
		return answer;
	}
}
