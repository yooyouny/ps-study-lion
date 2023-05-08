package java.pg._230502_12916_문자열내p와y의개수;
//https://school.programmers.co.kr/learn/courses/30/lessons/12916?language=java
public class Solution_12916 {
    //s에 'p'의 개수와 'y'의 개수를 비교해 같으면 True, 다르면 False를 return 하는 solution를 완성하세요.
    //'p', 'y' 모두 하나도 없는 경우는 항상 True를 리턴
    boolean solution(String s) {
        boolean answer = true;
        int cntp = 0;       //p의 개수
        int cnty = 0;       //y의 개수

        for(char spell : s.toCharArray()){
            if(spell == 'p' || spell == 'P'){
                cntp++;
            }else if(spell == 'y' || spell == 'Y'){
                cnty++;
            }
        }
        if(cntp != cnty){
            answer = false;
        }

        return answer;
    }
}
