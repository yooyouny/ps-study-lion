package com.example.javaproject3.psstudy;

import java.util.Stack;

public class Solution12909 {
    boolean solution(String brackets) {
        int sum = 0;  // 괄호 카운트 변수
        for (int i = 0; i < brackets.length(); i++) {
            char tmp = brackets.charAt(i);
            if (tmp == '(') sum++; // 여는 괄호 시 plus
            else if (tmp == ')') {  // 닫는 괄호 시,
                if (sum <= 0) return false;  // sum 이 0 이하면 앞에 여는 괄호가 없다는 의미, false 리턴
                else sum--; // 닫는 괄호 시 minus
            }
        }
        return sum == 0; // sum 이 0이면 올바른 괄호이므로 true 리턴됨
    }

    boolean solution_(String brackets) {
        Stack<Character> stack = new Stack<>(); // 괄호 담을 스택
        for (int i = 0; i < brackets.length(); i++) {
            char tmp = brackets.charAt(i);
            if (tmp == '(') { // 여는 괄호 시, stack에 push
                stack.push(tmp);
            } else { // 닫는 괄호 시,
                if (stack.isEmpty()) return false; // 스택이 비어있으면, false 리턴
                if (stack.peek() == '(') { // 스택의 top 이 여는 괄호라면
                    stack.pop();  // pop
                } else return false;  // 올바르지 않은 괄호이기 때문에 false 리턴
            }
        }
        return stack.size() == 0; // 스택이 비어있으면 올바른 괄호이므로, true 리턴
    }


    public static void main(String[] args) {
        System.out.println(new Solution12909().solution(")()("));

    }
}
