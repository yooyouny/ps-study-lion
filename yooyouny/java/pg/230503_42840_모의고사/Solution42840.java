import java.util.*;
class Solution42840 {
	public int getScore(int[] pattern, int[] answers){
		int idx = 0;// pattern의 길이를 다시 0으로 리셋해주기 위해 인덱스를 변수로 뺌
		int score = 0;// 해당 패턴으로 answers와 매핑했을때 반환되는 점수 변수
		for(int i=0; i<answers.length; i++){// answers 값들을 순차적으로 접근하기 위한 For문
			if(pattern[idx] == answers[i]) score++;
			idx = (++idx) % pattern.length; // 패턴의 다음 인덱스 접근을 위해 증가시켜주고 만약 패턴의 길이를 벗어날 경우 다시 0으로 리셋하기 위해 나머지 연산 활용
		}
		return score;
	}

	public int[] solution(int[] answers) {
		//사람이 3명이고 일정한 패턴을 유지하니 배열에 넣어줌
		int[][] pattern = {{1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
		int[] score = {0, 0, 0};

		score[0] = getScore(pattern[0], answers);
		score[1] = getScore(pattern[1], answers);
		score[2] = getScore(pattern[2], answers);

		int max = Arrays.stream(score).max().orElse(0);// score 배열의 최댓값을 구하고

		List<Integer> winners = new ArrayList<>();// 결과배열의 크기가 정해지지 않아서 list를 활용
		for(int i=0; i<3; i++){// 최댓값과 같은 사람(인덱스 + 1)을 리스트에 넣음
			if(max == score[i])
				winners.add(i+1);
		}

		return winners.stream().mapToInt(Integer::intValue).toArray(); //list를 int배열로 변환해서 리턴
	}
}
