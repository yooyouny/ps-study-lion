package java.pg._230614_12909_올바른괄호;

import java.util.Stack;
//신규_프로그래머스_lv2_올바른괄호
public class Solution_12909 {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        for(char temp : s.toCharArray()){
            if(temp == '('){        //앞 괄호면 stack.push(temp)
                stack.push(temp);
            }else if(temp == ')') { //뒷 괄호인 경우
                if(stack.isEmpty() || !(stack.peek() == '(')){  //stack이 비어서 짝이 없거나, 앞 문자가 앞괄호가 아닌 경우
                    return false;
                }else{          //앞문자와 짝이 되는 경우 stack.pop으로 앞 괄호를 제거해준다.
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();
    }
}
