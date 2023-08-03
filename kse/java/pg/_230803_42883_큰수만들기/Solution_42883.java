package java.pg._230803_42883_큰수만들기;
// https://school.programmers.co.kr/learn/courses/30/lessons/42883?language=java
// 신규_프로그래머스_Lv2_42883_큰수만들기
public class Solution_42883 {
    public String solution(String number, int k) {
        StringBuilder result = new StringBuilder();
        int startIndex = 0; //탐색 시작 index
        int maxIndex;       //범위 내의 최고값의 index

        for (int i = 0; i < number.length() - k; i++) { //내가 answer 를 만들 때 넣어야하는 숫자 개수
            int maxDigit = 0;       //최대값
            maxIndex = startIndex;

            for (int j = startIndex; j <= k + i; j++) { //탐색할 범위 k개 없애야 하니까 i번째 숫자 구할 때 + k
                int digit = number.charAt(j) - '0';     //숫자의 아스키 값 구하기

                if (digit > maxDigit) {                 //범위 내의 최대값 구하기
                    maxIndex = j + 1;
                    maxDigit = digit;
                }
            }

            result.append(maxDigit);    // max 앞의 숫자를 지울 때 가장 숫자가 커질 수 있다.
            startIndex = maxIndex;
        }

        return result.toString();
    }
}
