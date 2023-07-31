package java.pg._230730_43162_네트워크;

import java.util.LinkedList;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/43162
//신규_프로그래머스_Lv3_43162_네트워크
//BFS
public class Solution_43162 {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            //방문한적 있는지 확인
            //A-C 로 이미 지나왔다면 C-> 다음 노드는 이미 확인 했을 것
            if(!visited[i]){
                //DFS 또는 BFS
                //아직 방문하지 않은 i부터 탐색
                network(i, n, computers, visited);
                //네트워크 하나 완성
                answer++;
            }
        }
        return answer;
    }

    public void network(int computer, int n, int[][] computers, boolean[] visited ){
        Queue<Integer> toVisit = new LinkedList<>();
        toVisit.offer(computer);    //시작점
        while(!toVisit.isEmpty()){
            int now = toVisit.poll();  //now 번의 컴퓨터가 i번째 컴퓨터와 연결되어 있는가.
            for (int i = 0; i < n; i++) {
                if(computers[now][i] == 1){ //연결되어 있는가
                    if(!visited[i]) {       //방문한 적이 없는가
                        toVisit.offer(i);   //이 다음 연결된 노드부터 다음으로 연결된 노드를 찾는다.
                        visited[i] = true;
                    }
                }
            }
        }
    }
}
