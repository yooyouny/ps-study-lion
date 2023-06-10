import java.util.*;
class Solution42576 {
	public String solution(String[] participant, String[] completion) {
		String answer = "";
		Map<String, Integer> passer = new HashMap<>();// 참가자 이름, 사람 수를 함께 저장 할 map 생성
		for(String people : participant){
			passer.put(people, passer.getOrDefault(people, 0) + 1);// 이름을 key로 해서 동명이인 일때마다 사람 수 증가
		}
		for(String people : completion){
			passer.put(people, passer.getOrDefault(people, 0) - 1);// 완주자일 시 사람 수 감소
		}
		for(String key : passer.keySet()){
			if(passer.get(key) != 0)// 사람 수가 0이 아니면 제대로 완주하지 않은 이름이므로 해당 이름 반환
				answer = key;
		}
		return answer;
	}
}
