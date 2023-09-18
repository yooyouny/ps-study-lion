package java.pg._230918_42578_의상;

import java.util.HashMap;
import java.util.Map;
//https://school.programmers.co.kr/learn/courses/30/lessons/42578?language=java
//신규_프로그래머스_lv2_42578_의상
public class Solution_64062 {
    /**
     *
     * @param clothes [ 옷 이름, 옷 종류 ]
     * @return 입을 수 있는 옷의 조합
     */
    public int solution(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> clothesMap = new HashMap<>();
        //옷 종류 별로 가짓수를 구한다.
        for (String[] temp : clothes){
            clothesMap.put(temp[1], clothesMap.getOrDefault(temp[1], 0) + 1);
        }
        //종류 별 개수 + 1(입지 않는 경우) 로 곱하여 조합의 수를 구하고
        for ( Map.Entry<String, Integer> set : clothesMap.entrySet()){
            answer *= (set.getValue() + 1);
        }
        //아무것도 입지 않는 경우 1 을 빼서 정답을 return 해준다.
        return answer - 1;
    }
}
