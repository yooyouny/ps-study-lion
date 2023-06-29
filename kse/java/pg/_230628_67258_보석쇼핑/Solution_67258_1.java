package java.pg._230628_67258_보석쇼핑;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//https://school.programmers.co.kr/learn/courses/30/lessons/67258
//신규_프로그래머스_lv3_보석쇼핑
//효율성 테스트 망함
public class Solution_67258_1 {
	public boolean noFalse(boolean[] check){
		for(boolean isFalse : check){
			if(!isFalse) return false;
		}
		return true;
	}

	public int shopping(String[] gems, List<String> jewel, boolean[] check, int index){
		if(noFalse(check)) return index;
		if(gems.length <= index) return 100_000;

		int idx = jewel.indexOf(gems[index]);
		//아직 구매하지 않은 보석인 경우
		if(!check[idx]) check[idx] = true;
		return shopping(gems, jewel, check, index+1);
	}

	/**
	 * 진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매
	 * 중간 번호가 빠지면 안됨 1~n사이의 모든 진열대의 보석을 구매
	 *
	 * dfs를 사용하여 시작 좌표 i를 기준으로 끝나는 시점을 가져와 min값을 구해준다.
	 * 효율성 통과 xxxx
	 *
	 * @param gems 진열대에 놓여있는 보석의 배열
	 * @return { 가장 짧은 구간의 시작 진열대 번호, 끝 진열대 번호 }
	 */
	public int[] solution(String[] gems) {
		int[] answer = {};
		List<String> jewel = Arrays.stream(gems).distinct().collect(Collectors.toList());

		int min = 100_001;
		for (int i = 0; i <= gems.length - jewel.size(); i++) {
			int endIndex = shopping(gems, jewel, new boolean[jewel.size()], i);
			if(min > endIndex - i){
				answer = new int[]{i+1, endIndex};
				min = endIndex - 1;
			}

			System.out.printf("%d -> %d : %d\n",i, endIndex, endIndex - i );
		}
		return answer;
	}
}
