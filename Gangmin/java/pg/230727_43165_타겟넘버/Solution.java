class Solution {
int count = 0;


	public int solution(int[] numbers, int target) {
		int answer = 0;
		recur(numbers, 0, target, 0);
		return answer = count;
	}


	public void recur(int[] numbers, int cand, int target, int depth){
		if(depth == numbers.length){
			if(cand == target){
				count++;
			}
			return;
		}
		int current = numbers[depth];
        //더하는 부분 집합
		recur(numbers, cand + current, target, depth + 1);
		//빼는 부분 집합
        recur(numbers, cand - current, target, depth + 1);
	}


}
