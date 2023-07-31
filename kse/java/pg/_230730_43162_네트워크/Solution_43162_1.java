package java.pg._230730_43162_네트워크;

import java.util.Stack;
//https://school.programmers.co.kr/learn/courses/30/lessons/43162
//신규_프로그래머스_Lv3_43162_네트워크
//DFS
public class Solution_43162_1 {
	public int solution(int n, int[][] computers) {
		int answer = 0;
		boolean[] visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				network(i, n, computers, visited);
				answer++;
			}
		}
		return answer;
	}

	public void network(int start, int n, int[][] computers, boolean[] visited) {
		Stack<Integer> toVisit = new Stack<>();
		toVisit.push(start);

		while (!toVisit.isEmpty()) {
			int now = toVisit.pop();

			for (int i = 0; i < n; i++) {
				if (computers[now][i] == 1 && !visited[i]) {
					toVisit.push(i);
					visited[i] = true;
				}
			}
		}
	}
}
