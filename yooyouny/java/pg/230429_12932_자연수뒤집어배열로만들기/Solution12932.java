class Solution12932 {
	public int[] solution(long n) {

		StringBuilder sb = new StringBuilder(String.valueOf(n));// reverse를 쓰기 위해 StringBuilder 생성
		String[] result = sb.reverse().toString().split("");// reverse한 문자열을 String배열로 만들기
		int[] answer = new int[result.length];// 결과배열의 크기는 input으로 주어진 문자열의 길이

		for(int i=0; i<answer.length; i++){
			answer[i] = Integer.parseInt(result[i]);// 결과배열 int에 맞게 String to int 형변환
		}
		return answer;
	}
}
