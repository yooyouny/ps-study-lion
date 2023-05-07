import java.util.*;
class Solution138477 {
	public int[] solution(int k, int[] score) {
		int[] answer = new int[score.length]; //매일 발표된 점수는 모든 일차의 점수가 담긴 score의 길이와 같음
		int answerIdx = 0;
		List<Integer> top = new ArrayList<>(); //k 길이만큼 저장될 명예의 전당 list

		for(int i=0; i<score.length; i++){//i+1는 프로그램 진행 일차
			if(top.size() < k){// 프로그램 시작 초기에 k번째 까지는 해당 score가 명예의 전당에 오름
				top.add(score[i]);
			}else{// k일 다음부터는
				int min = top.get(0);// 현재 명예의 전당에 있는 최하위 점수와
				if(score[i] > min){// 출연한 가수의 점수가 기존 명예의 전당 목록의 k번째 순위(가장 마지막 등수 = 가장 적은 수)의 가수 점수보다 높으면
					top.remove(0);// 기존 k번째 순위는 명예의 전당에서 내려오고
					top.add(score[i]);// 출연 가수의 점수가 올라가게 됨
				}
			}
			Collections.sort(top);// 명예의 전당 list에 넣을때마다 오름차순으로 정렬
			answer[answerIdx++] = top.get(0);// 해당 i+1일차에 해당하는 최하위 점수를 저장
		}
		return answer;
	}
}
