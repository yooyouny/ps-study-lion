package java.pg._230715_49993_스킬트리;

import java.util.List;

//https://school.programmers.co.kr/learn/courses/30/lessons/49993
//신규_프로그래머스_Lv2_49993_스킬트리
public class Solution_49993 {
	public int solution(String skill, String[] skill_trees) {
		int answer = 0;
		//skill 을 나눠 리스트로 저장한다.
		List<String> skills = List.of(skill.split(""));
		for(String tree : skill_trees){
			String temp = "";
			//skills 에 포함되지 않는 나머지 스킬을 모두 버린다.
			for (String spell : tree.split("")) {
				if (skills.contains(spell)) temp += spell;
			}
			//temp 가 skill 의 앞부분과 같다면 스킬트리를 만족한다.
			if(skill.startsWith(temp)) answer++;
		}
		return answer;
	}
}