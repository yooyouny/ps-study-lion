package com.example.algorithm.ps;
import java.util.*;

public class Solution92343 {
    // 신규_프로그래머스_lv3_92343_양과늑대
    static int answer;
    static List<List<Integer>> g = new LinkedList<>();

    public int solution(int[] info, int[][] edges){
        answer = 0;
        for (int i = 0; i < info.length; i++) g.add(new LinkedList<>());
        for(int[] edge : edges) g.get(edge[0]).add(edge[1]); // 현재 노드의 자식 노드 저장

        List<Integer> next = new LinkedList<>();
        next.add(0);
        dfs(info, next, 0, 0, 0);
        return answer;
    }

    public void dfs(int[] info, List<Integer> list, int node, int sheep, int wolf){
        if(info[node] == 0) sheep++;  // 양 개수 갱신
        else wolf++; // 늑대 개수 갱신

        if(sheep <= wolf) return;  // 늑대 수가 많을 경우, 탐색 종료

        answer = Math.max(answer, sheep); // 양 최댓값 갱신

        List<Integer> next = new ArrayList<>(list); // 다음 탐색 노드 복사
        if(!g.get(node).isEmpty())
            next.addAll(g.get(node));  // 현재 위치의 자식 노드들 추가
        next.remove(Integer.valueOf(node)); // 탐색한 현재 위치 제거

        for(int n : next)  // 탐색 노드들 dfs 실행
            dfs(info, next, n, sheep, wolf);
    }

    public static void main(String[] args) {
        System.out.println(new Solution92343().solution(
                new int[]{0,1,0,1,1,0,1,0,0,1,0},
                new int[][]{{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}})
        );
    }
}
