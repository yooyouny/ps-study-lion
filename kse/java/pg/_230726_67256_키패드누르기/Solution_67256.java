package java.pg._230726_67256_키패드누르기;
//https://school.programmers.co.kr/learn/courses/30/lessons/67256
//신규_Lv1_67256_키패드누르기
public class Solution_67256 {
	StringBuilder result = new StringBuilder();
	int left = 10;
	int right = 12;

	/**
	 * 1. *, 0, # 계산을 위해 값을 10 11 12 로 바꿔서 계산해주기
	 * 2. [2,5,8,0]는 [left right]값과 숫자의 거리 계산해주기
	 * 3. 거리에 따라 결과 다르게 주기
	 */
	public String solution(int[] numbers, String hand) {
		for(int number : numbers){
			number = number == 0 ? 11 : number;
			switch (number) {
				case 1, 4, 7 -> choiceLeft(number);
				case 3, 6, 9 -> choiceRight(number);
				default -> {
					int Diff = findDiff(left, right, number);
					if (Diff > 0)
						choiceRight(number);
					else if (Diff < 0)
						choiceLeft(number);
					else if (hand.equals("right"))
						choiceRight(number);
					else
						choiceLeft(number);
				}
			}
		}
		return result.toString();
	}

	/**
	 * 계산을 편하게 하기 위해 값 -1
	 *  0  1  2
	 *  3  4  6
	 *  7  8  9
	 * 10 11 12 -> 세로는 % 3, 가로는 / 3으로 계산 가능
	 */
	public int findDiff(int left, int right, int number){
		left--; right--; number--;
		int valX = number % 3;
		int valY = number / 3;
		int lefDiff = Math.abs(left % 3 - valX) + Math.abs(left / 3 - valY);
		int rigDiff = Math.abs(right % 3 - valX) + Math.abs( right / 3 - valY);
		return lefDiff - rigDiff;
	}
	public void choiceLeft(int number){
		result.append("L");
		left = number;
	}
	public void choiceRight(int number){
		result.append("R");
		right = number;
	}
}