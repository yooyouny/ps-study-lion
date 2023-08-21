package com.example.algorithm.ps;

import java.util.Arrays;
import java.util.Stack;

public class Solution121685_ {
    static String[] arr = new String[]{"RR", "Rr", "Rr", "rr"};
    static String[] answer;

    public  String[] solution(int[][] queries) {
        answer = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            getGene(i, queries[i]);
        }
        return answer;
    }

    public void  getGene(int idx, int[] pose) {
        int level = pose[0];  // 레벨
        int cnt = pose[1] - 1;
        Stack<Integer> stack = new Stack<>();

        if(level == 1){   // level 1인 경우, Rr
            answer[idx] = "Rr";
            return;
        }
        if (level == 2) {  // level 2 인 경우, arr 값 저장
            answer[idx] = arr[cnt];
            return;
        }

        while (level != 1) {  // 스택에 조상 형질들을 저장
            int tmp = cnt % 4; //  나머지 값이 조상 형질
            cnt /= 4;
            level -= 1;
            stack.push(tmp);
        }

        while (!stack.isEmpty()) {  // 조상 중에 하나라도 RR or rr 이라면 자신은 무조건 RR or rr이다.
            String str = arr[stack.pop()];
            if (str.equals("RR") || str.equals("rr")) {
                answer[idx] = str;
                return;
            }
            answer[idx] = str;
        }
    }

    public static void main(String[] args) {
        int[][] queries = {{4, 34}};
        System.out.println(Arrays.toString(new Solution121685_().solution(queries)));
    }
}
