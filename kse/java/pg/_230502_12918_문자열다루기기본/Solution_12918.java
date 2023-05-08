package java.pg._230502_12918_문자열다루기기본;
//https://school.programmers.co.kr/learn/courses/30/lessons/12918
public class Solution_12918 {
    //문자열 s의 길이가 4 혹은 6이고, 숫자로만 구성돼있는지 확인해주는 함수
    public boolean solution(String s) {
        boolean answer = true;
        if (s.length() == 4 || s.length() == 6) {
            for (int i = 0; i < s.length(); i++) {
                //숫자가 아니면 false
                if (s.charAt(i) >= 65) {
                    answer = false;
                    break;
                }
            }
        } else {
            answer = false;
        }

        return answer;
    }
}
