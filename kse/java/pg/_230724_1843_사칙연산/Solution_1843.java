package java.pg._230724_1843_사칙연산;
import java.util.Arrays;

//https://school.programmers.co.kr/learn/courses/30/lessons/1843
//신규_프로그래머스_Lv4_1843_사칙연산
public class Solution_1843 {

	int SIZE;			//확인해야하는 연산자 개수
	int[] NUMBERS;		//숫자
	char[] OPERATOR;	//연산자
	int[][] minBoard;	//최소값
	int[][] maxBoard;	//최대값

	/**계산식의 최대값 구하기
	 * 1. 계산식1 + 계산식2 -> 계산식1 / 계산식2 각각의 합산이 그 계산식의 최대값이 나와야 한다.
	 * 2. 계산식1 - 계산식2 -> 계산식1는 최대값, 계산식2는 최소값이 나와야 한다.
	 * [A+B-C+D]의 최대값 구하기
	 * [A + BCD] -> A  의 최대값 + BCD 의 최대값
	 * [AB - BD] -> AB 의 최대값 - BD  의 최소값
	 * -> 이렇게 경우의 수를 나눠 계산 한 것의 최대값이 [A+B-C+D]의 최대값이 된다.
	 */
	public int solution(String arr[]) {
		init(arr);
		return calc(true, 0, SIZE);
	}

	private void init(String arr[]) {
		SIZE = arr.length / 2;
		minBoard = new int[SIZE + 1][SIZE + 1];
		maxBoard = new int[SIZE + 1][SIZE + 1];
		NUMBERS  = new int[SIZE + 1];
		OPERATOR = new char[SIZE];

		for(int i=0; i<arr.length; i++){		// NUMBERS, OPERATOR 나눠서 구해주기
			if(i%2 == 0) NUMBERS[i/2] = Integer.parseInt(arr[i]);
			else OPERATOR[i/2] = arr[i].charAt(0);
		}
		for (int i = 0; i < SIZE + 1; i++) {	// 최대값 배열 -> 최소값, 최소값 배열 -> 최대값 채우기
			Arrays.fill(minBoard[i], Integer.MAX_VALUE);
			Arrays.fill(maxBoard[i], Integer.MIN_VALUE);
		}
		for (int i = 0; i < SIZE + 1; i++) {	// [i][i] 본인 숫자 사전에 채워놓기
			minBoard[i][i] = NUMBERS[i];
			maxBoard[i][i] = NUMBERS[i];
		}
	}
	public int calc(boolean findMax, int start, int end){
		// start == end -> [i][i] 출력

		if(findMax & maxBoard[start][end] != Integer.MIN_VALUE)			// 이미 계산한 최대값 구하기
			return maxBoard[start][end];
		else if(!findMax & minBoard[start][end] != Integer.MAX_VALUE)	// 이미 계산한 최소값 구하기
			return minBoard[start][end];

		// 최소값 최대값 구하기 위해 사전에 값 넣어주기
		int findVal = findMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

		if(findMax) {	//최대값 구하는 경우
			for (int mid = start; mid < end; mid++) {
				if (OPERATOR[mid] == '-') {
					findVal = Math.max(findVal, calc(true, start, mid) - calc(false, mid + 1, end));
				} else {
					findVal = Math.max(findVal, calc(true, start, mid) + calc(true, mid + 1, end));
				}
			}
			maxBoard[start][end] = findVal;

		}else{			//최소값 구하는 경우
			for (int mid = start; mid < end; mid++) {
				if(OPERATOR[mid] == '-'){	// - (A-B) -> A-B가 최대여야 뺄 때 최소값 -> A는 최소, B는 최대
					findVal = Math.min(findVal, calc(false, start, mid) - calc(true, mid+1, end));
				}else{						// - (A+B) -> A+B가 최대여야 뺄 때 최소값 -> A는 최대, B는 최대
					findVal = Math.min(findVal, calc(false, start, mid) + calc(false, mid+1, end));
				}
				/*
					A-B가 최대값이 되기 위해 B가 최소값이 되는 경우를 찾아야 하는데,
					B가 C-D로 구성된 경우 C-D는 C는 최대 D는 최소를 구하러가야하지만
					- B를 구하는 문제이기 때문에 -(C-D)  ->  -C+D 가 되므로
					C는 최소 D는 최대를 구하는게 된다.
				 */
			}
			minBoard[start][end] = findVal;
		}
		return findVal;
	}
}
