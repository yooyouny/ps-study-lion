package java.pg._231109_132265_롤케이크자르기;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/132265
//신규_프로그래머스_lv2_132265_롤케이크자르기
public class Solution_132265    {
    public int solution(int[] topping) {
        int answer = 0;
        int LENGTH = topping.length;
        
        // 0 ~ i 번째까지의 토핑 개수
        int[] startFromZero = new int[LENGTH];
        // LENGTH ~ i 번째까지의 토핑 개수
        int[] endsAtZero    = new int[LENGTH];

        // 종류의 개수를 세기위한 Set 정의
        Set<Integer> startSet = new HashSet<>();
        Set<Integer> endSet   = new HashSet<>();

        // Set 에 값을 하나씩 넣으면서 종류의 개수를 세어 배열에 저장
        for (int i = 0; i < LENGTH; i++) {
            startSet.add(topping[i]);
            endSet.add(topping[LENGTH - i - 1]);
            startFromZero[i] = startSet.size();
            endsAtZero[i]    = endSet.size();
        }

        // i번째 앞 케이크조각과   LENGTH - 1 - 2 번째의 케이크 조각의 토핑 개수를 비교
        // i번째 케이크를 빼고 end ~ i 까지의 조각 개수이기 때문에 ( LENGTH - 1 - 2 )
        for (int i = 0; i < LENGTH - 1; i++) {
            if(startFromZero[i] == endsAtZero[LENGTH - i - 2]) answer++;
        }
        
        return answer;
    }
}
