import java.util.*;
class Solution42586 {
	private static int getLeftPercent(int idx, int[] progresses, int[] speeds){
		int result = (100 - progresses[idx]) / speeds[idx];
		return (100 - progresses[idx]) % speeds[idx] == 0 ? result : result + 1;
	}
	public int[] solution(int[] progresses, int[] speeds) {
		Queue<Integer> queue = new LinkedList<>();
		List<Integer> answer = new ArrayList<>();

		for(int i=0; i<progresses.length; i++){
			queue.add(i); //인덱스 저장
		}

		int cnt = 0;
		int day = 0;

		while(!queue.isEmpty()){
			int idx = queue.poll();
			int left = getLeftPercent(idx, progresses, speeds);

			if(left>=day){// 이전 일수보다 현재 인덱스의 작업량이 더 큰 경우, 작업일수가 같으면 같은날에 처리해야하므로 = 포함
				if(day != 0){// day가 초깃값 0인 경우를 제외하고는 리스트에 넣음
					answer.add(cnt);
					cnt = 0;// 다음 작업 체크를 위해 초기화
				}
				day = left;// 작업잔량(일수) 업데이트
			}
			cnt++;// 현재 인덱스의 작업잔량이 더 적으면 이전 작업에 포함되므로 cnt 증가
		}
		answer.add(cnt);// 마지막 인덱스의 작업횟수는 저장되지 않았으므로 추가
		return answer.stream()// list를 int array로 전환
			.mapToInt(Integer::intValue)
			.toArray();
	}
}
