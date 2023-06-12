import java.util.*;
class Solution {
	public static String solution(String[] participant, String[] completion) {
		String answer = "";
		Map<String, Integer> completeDic= new HashMap<>();
		for (int i = 0; i < completion.length; i++) {
            //Key값이 존재하면 더하기
			if(completeDic.containsKey(completion[i])){
				completeDic.put(completion[i], completeDic.get(completion[i]) + 1);
			} else{
                //없으면 생성
				completeDic.put(completion[i], 1);
			}
		}

		for (int i = 0; i < participant.length; i++) {
			if(completeDic.containsKey(participant[i])){
                //찾으면 하나씩 value뺴기
				completeDic.put(participant[i], completeDic.get(participant[i]) - 1);
				//value가 0이면 해당 key삭제
                if(completeDic.get(participant[i]) == 0){
					completeDic.remove(participant[i]);
				}
            //없으면 answer에 저장
			}else{
				answer = participant[i];
                break;
			}
		}
		return answer;
	}
}
