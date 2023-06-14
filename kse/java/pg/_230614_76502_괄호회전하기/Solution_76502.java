package java.pg._230614_76502_괄호회전하기;

import java.util.Stack;
//신규_프로그래머스_lv2_괄호회전하기
public class Solution_76502 {
    final char[] FRONT = {'[','{','('};
    final char[] BACK  = {']','}',')'};

    /**
     *  앞 괄호인지 확인하고 해당 index를 return하는 메서드
     */
    public int isFront(char temp){
        for(int i = 0; i < 3; i++){
            if(temp == FRONT[i]) return i;
        }
        return -1;
    }

    /**
     * 올바른 괄호인지 확인하는 메서드
     * 1. front 괄호인 경우 push
     * 2. back인 경우
     *      2-1. stack이 비었다면 return false
     *      2-2. stack 마지막 괄호와 한쌍이 되는 경우 stack.pop()
     *      2-3. 한쌍이 되지 못하는 경우 stack.push(temp)
     * @return 올바른 괄호면 true
     */
    public boolean isRight(char[] arr){
        Stack<Character> stack = new Stack<>();
        for(char temp : arr){
            if(isFront(temp)>=0){
                stack.push(temp);
            }else{
                if(!stack.isEmpty()){
                    int check = isFront(stack.peek());
                    if(check>=0 && temp == BACK[check]){
                        stack.pop();
                    }else{
                        stack.push(temp);
                    }
                }else
                    return false;

            }
        }
        return stack.isEmpty();
    }
    public int solution(String s) {
        int answer = 0;
        //문자열 한바퀴씩 돌리기
        for(int i = 0; i < s.length()-1;i++){
            String sTemp = s.substring(i)+s.substring(0,i);
            if(isRight(sTemp.toCharArray())){   //해당 문자열이 올바른 괄호인 경우 answer++
                answer++;
          }
        }
        return answer;
    }
}
