package java.pg._230731_43163_단어변환;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/43163
//신규_프로그래머스_Lv3_43163_단어변환
//BFS
public class Solution_43163 {
	public int solution(String begin, String target, String[] words) {
		int answer = 0;
		// words 에 target 이 없으면 return 0
		// "hit" -> "cog"	["hot", "dot", "dog", "lot", "log"]	 0
		if(!Arrays.asList(words).contains(target)) return answer;
		int len = words.length;

		int[] visited = new int[len];	//방문 확인


		Queue<Integer> toVisit = new LinkedList<>();
		for (int i = 0; i < len; i++) { 		//begin 에서 도달할 수 있는 words 내의 단어를 넣는다.(한글자 차이나는 경우)
			if(findNext(begin, words[i])){
				toVisit.offer(i);
				visited[i] = 1;
			}
		}

		while (!toVisit.isEmpty()){
			int now = toVisit.poll();	//현재 while 에서 확인할 index
			String next = words[now];

			if(next.equals(target)){ //target 을 맞춘 경우 끝
				answer = visited[now];
				break;
			}
			for (int i = 0; i < len; i++) {
				//한글자 차이나고 방문하지 않은 경우
				if(findNext(next, words[i]) & visited[i]==0){
					toVisit.offer(i);
					visited[i]  = visited[now] + 1;
				}

			}
		}
		return answer;
	}

	/** word와 target에서 한 글자만 다른 경우 ( 변환할 수 있는 경우 )를 찾는 메서드 */
	public boolean findNext(String word, String target){
		int cnt = 0;
		for (int i = 0; i < word.length(); i++) {
			if(word.charAt(i) != target.charAt(i)) cnt++;
		}
		return cnt == 1;
	}
}
