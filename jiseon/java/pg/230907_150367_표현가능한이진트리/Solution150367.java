package com.example.algorithm.ps;

import java.util.Arrays;

public class Solution150367 {
    static int[] treeSize = {1, 3, 7, 15, 31, 63};

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String binary = getBinary(numbers[i]);  // 이진수 변환
            if (isBinaryTree(binary)) answer[i] = 1; // 이진트리로 표현할 수 있다면
            else answer[i] = 0;
        }
        return answer;
    }

    public String getBinary(long num) {
        String str = Long.toBinaryString(num);
        int size = str.length();

        //포화 이진트리 만들기
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < treeSize.length; i++) {
            if(size > treeSize[i]) continue;
            if(size == treeSize[i]) break;
            if(size < treeSize[i]){  // 이진수 앞 자리에 필요한 0 개수만큼 채움
                int zero = treeSize[i] - size;
                for (int j = 0; j < zero; j++) sb.append(0);
                break;
            }
        }

        sb.append(str);
        return sb.toString();
    }

    public boolean isBinaryTree(String binary) {
        if (binary.length() == 1) return true; // 리프 노드면 항상 true

        int mid = binary.length() / 2;  // root 위치
        int root = binary.charAt(mid); // 현재 트리의 루트 노드 값

        // 서브 트리
        String left = binary.substring(0, mid);
        String right = binary.substring(mid + 1);

        // 서브 트리의 루트 노드 값
        int leftRoot = left.charAt(left.length() / 2);
        int rightRoot = right.charAt(right.length() / 2);

        if (isBinaryTree(left) && isBinaryTree(right)) { // 서브 트리들이 해당 조건을 만족하는 경우
            if (root == '1') return true;  // 루트 노드가 1 이면
            else if (leftRoot == '0' && rightRoot == '0') return true; // 자식 노드가 모두 0 이면
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution150367().solution(new long[]{7, 42, 5})));
    }
}
