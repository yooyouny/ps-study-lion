package java.pg._230429_68935_3진법뒤집기;
//https://school.programmers.co.kr/learn/courses/30/lessons/68935
public class Solution_68935 {
    class Solution {
        public int solution(int n) {
            int answer = 0;
            String[] trit = (Integer.toString(n,3)).split("");
            String reverseTrit = "";
            for(int i = trit.length-1 ; i >= 0 ; i--){
                reverseTrit += trit[i];
            }
            answer = Integer.parseInt(reverseTrit,3);
            return answer;
        }
    }
}
