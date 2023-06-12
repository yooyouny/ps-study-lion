import java.util.*;
class Solution42576_2 {
	public String solution(String[] participant, String[] completion) {
		//완주하지 못한 선수는 무조건 한명이므로 정렬 후 순서가 같지 않은 사람을 리턴
		Arrays.sort(participant);
		Arrays.sort(completion);

		for(int i=0; i<completion.length; i++){// 완주자 배열이 한개차이로 적음
			if(!participant[i].equals(completion[i]))
				return participant[i];
		}

		return participant[participant.length - 1];// 모두 동일하면 마지막 참여자가 완주하지 않은 경우가 됨
	}
}
