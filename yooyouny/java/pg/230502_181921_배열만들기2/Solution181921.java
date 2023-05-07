import java.util.*;
class Solution181921 {
	public boolean isZeroFiveNum(int n){
		/*문자열 형변환 없이 자릿수 계산으로 구해보기 */
		int digit = 0;// 정수 n의 자릿수를 저장할 변수
		while(n > 0){
			digit = n % 10;// n의 끝자리수를 나머지 연산으로 구함
			if(digit != 0 && digit != 5)// 0이나 5가 아니면 다른 자릿수 확인을 할 필요가 없으므로 바로 리턴
				return false;
			n /= 10;// 정수의 첫자리부터 끝자리수-1까지의 정수를 나눗셈 연산으로 구함
		}
		return true;
	}
	public int[] solution(int l, int r) {
		List<Integer> result = new ArrayList<>();// 0과 5로만 이루어진 숫자를 list에 담고 나중에 배열로 변환해서 리턴할거임

		while(l<=r){// l을 r까지 증가시켜갈 거라서 해당 범위를 벗어나면 반복문을 빠져나오도록 함
			if(isZeroFiveNum(l))
				result.add(l);// 0과 5로만 이루어진 정수면 result 리스트에 add
			l++;
		}

		if(result.size() == 0)// 해당되는 정수가 하나도 없으면 -1이 담긴 배열을 리턴
			return new int[]{-1};

		Collections.sort(result);// 해당 되는 정수가 있으면 오름차순으로 정렬을 하고
		int[] answer = new int[result.size()];// 정수형 배열에 담아서 리턴
		for(int i=0; i<answer.length; i++)
			answer[i] = result.get(i);

		return answer;
	}
}
