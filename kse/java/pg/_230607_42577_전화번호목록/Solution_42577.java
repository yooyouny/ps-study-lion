package java.pg._230607_42577_전화번호목록;

import java.util.Arrays;

//https://school.programmers.co.kr/learn/courses/30/lessons/42577?language=java
//신규_프로그래머스_lv2_전화번호목록
public class Solution_42577 {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        //1. phone_book 배열을 사전식으로 정렬해준다.
        //   [ 12 123 45 458 90 ]
        Arrays.sort(phone_book);

        //2. (앞부분은 같으면서도 더 긴 문자열이 올 수 있는) 뒤에오는 값과 앞의 값을 비교해준다.
        for(int i = 0; i < phone_book.length-1; i++){
            if(phone_book[i+1].startsWith(phone_book[i]))
                return false;
        }
        return answer;
    }
}
