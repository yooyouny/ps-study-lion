package java.pg._230720_43105_정수삼각형;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/43105
//신규_프로그래머스_Lv3_43105_정수삼각형
public class Solution_43105 {
	/**
	 * level n 에서 i 번째의 요소가 갈 수 있는 다음 항목은
	 * level n+1 의 i 번째와 i+1 번째 요소이다.
	 * >>
	 * 삼각형의 아랫 부분에서 n-1번째에 올 수 있는 가장 큰 값만 골라서 올린다고 했을 때,
	 * level n-1번째에 올 수 있는 수는
	 * level n의 i번째 수 또는 level n의 i+1 번째 수 이다.
	 *
	 */
	public int solution(int[][] triangle) {

		for (int i = triangle.length-1; i>0; i--) {
			for (int j = 0; j < i; j++) {
				// j 번째 값과 j+1번째 값 중 큰 값을 위로 올려준다.
				triangle[i-1][j] += Math.max(triangle[i][j], triangle[i][j+1]);
			}
			//printResult(triangle);
		}
		return triangle[0][0];
	}

	/** 테스트 출력용 메서드 */
	public void printResult(int[][] result){
		for (int[] temp : result){
			System.out.printf(Arrays.toString(temp)+"\n");
		}
		System.out.println("-----------------------");
	}

}
