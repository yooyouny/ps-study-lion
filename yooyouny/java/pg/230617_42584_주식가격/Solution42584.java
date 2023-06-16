class Solution42584 {
	public int[] solution(int[] prices) {
		int[] answer = new int[prices.length];// 결과배열의 길이는 가격 배열길이와 같음

		for(int i=0; i<prices.length; i++){
			int price = prices[i];// 비교를 할 가격
			int time = 0;
			for(int j=i+1; j<prices.length; j++){
				if(prices[j] < price){// 가격이 떨어진 기간이 하나라도 존재하면
					time = j - i;// 인덱스 차이만큼이 초
					break;// 기간이 하나라도 존재하면 더이상 탐색 할 필요가 없으므로
				}else{// 떨어지지 않았으면 떨어지지 않은 기간을 저장
					time++;
				}
			}
			answer[i] = time;// 해당 인덱스 순서에 해당하는 시간 저장
		}
		return answer;
	}
}
