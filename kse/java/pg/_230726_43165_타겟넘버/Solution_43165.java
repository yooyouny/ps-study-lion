package java.pg._230726_43165_타겟넘버;
//https://school.programmers.co.kr/learn/courses/30/lessons/43165
//신규_Lv2_43165_타겟넘버
public class Solution_43165 {
	int answer = 0;
	public int solution(int[] numbers, int target) {
		calc(numbers, 0, 0, target);
		return answer;
	}
	public void calc(int[] numbers, int index, int sum, int target){
		if(index >= numbers.length){
			if(sum == target) answer++;
		}
		else{
			calc(numbers, index+1, sum+numbers[index], target);
			calc(numbers, index+1, sum-numbers[index], target);
		}
	}
}