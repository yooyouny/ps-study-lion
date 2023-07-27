
class Solution {
	//왼손이 마지막에 누른 숫자를 저장
	int left = 10;
	//오른손이 마지막에 누른 숫자를 저장
	int right = 12;




	public String solution(int[] numbers, String hand) {
		String answer = "";
		StringBuilder sb = new StringBuilder();
		for (int number : numbers) {
			//TODO 나머지가 1이면 무조건 왼손
			if(number % 3 == 1){
				left = number;
				sb.append("L");
			}
			//TODO 나머지가 0이면 무조건 오른손
			if(number != 0 && number % 3 == 0){
				right = number;
				sb.append("R");
			}
			//TODO 나머지가 2면 가까운것, 만약 거리가 같다면 hand확인
			if(number % 3 == 2 || number == 0){
				int lDiff = 0;
				int rDiff = 0;
				if(number == 0){
					number = 11;
				}
				lDiff = Math.abs(left - number) / 3 + Math.abs(left - number) % 3;
				rDiff = Math.abs(right - number) / 3 + Math.abs(right - number) % 3;
				if(lDiff > rDiff){
					right = number;
					sb.append("R");
				} else if (lDiff < rDiff) {
					left = number;
					sb.append("L");
				}else {
					if(hand.equals("left")){
						left = number;
						sb.append("L");
					}else {
						right = number;
						sb.append("R");
					}
				}
			}
		}
		return answer = sb.toString();
	}


}
