class Solution12932_2 {
	public int[] solution(long n) {
		String number = String.valueOf(n);// 전체 길이를 구하기 위해 String 타입으로 변환
		int[] digits = new int[number.length()];// 반환타입인 정수형 배열 선언
		for (int i = 0; i < digits.length; i++) {// 결과 배열 인덱스 접근 위한 for문
			digits[i] = (int)(n % 10);// 문자열의 마지막 글자부터 추가
			n /= 10;// 다음 반복 시 추가된 마지막 글자를 제외하고 진행하기 위해 정수 n값 갱신
		}
		return digits;
	}
}
