class Solution68935 {
	public int solution(int n) {
		StringBuilder sb = new StringBuilder(Integer.toString(n, 3));// reverse를 쓰기 위해 stringBuilder 사용. 3진법 String으로 바꾸고
		return Integer.parseInt(sb.reverse().toString(), 3);// 3진법 String을 10진수로 바꾸기
	}
}
