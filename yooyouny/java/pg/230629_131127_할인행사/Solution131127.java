import java.util.*;
class Solution131127 {
	private static boolean isWantProduct(String product, String[] want, int[] initNumber){
		for(int i=0; i<want.length; i++){
			if(want[i].equals(product) && initNumber[i] > 0){// 원하는 제품이고 수량도 뺄 수 있으면 true반환
				initNumber[i]--;
				return true;
			}
		}
		return false;
	}
	public int solution(String[] want, int[] number, String[] discount) {
		int answer = 0;

		for(int i=0; i<discount.length; i++){
			int day = 0;
			int[] initNumber = Arrays.copyOf(number, number.length); // copyOf 써야 초깃값 유지한채로 복사됨

			for(int j=i; j<=Math.min(i+9, discount.length-1); j++){
				if(isWantProduct(discount[j], want, initNumber)){//해당 할인 제품이 위시리스트에 있고 수량도 맞으면 day증가
					day++;
				}else{
					break;// 연속으로 10일이어야 하므로 중간에 안맞는 제품 있으면 for문 탈출
				}
			}
			if(day == 10) {// 연속으로 10일 인 경우면 answer증가
				answer++;
			}

		}
		return answer;
	}
}
