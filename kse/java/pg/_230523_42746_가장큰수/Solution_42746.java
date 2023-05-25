package java.pg._230523_42746_가장큰수;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
//신규_프로그래머스_lv2_42746_가장큰수
//https://school.programmers.co.kr/learn/courses/30/lessons/42746
public class Solution_42746 {
    public String solution(int[] numbers) {
        String answer = "";
        /*
            1    1111...
            12   1212...
            123  1231...
            433  4334...
         */
        int[][] result = new int[numbers.length][2];
        for(int i = 0 ; i < numbers.length ; i++){
            // 값을 4번 반복한 것을 4자리수로 잘라준다.
            // 자릿수를 맞춤으로써 문자로 만들었을 때의 값을 비교하기 쉽게 해준다.
            int temp = Integer.parseInt(Integer.toString(numbers[i]).repeat(4).substring(0,4));
            // 만든 문자열과 원래값을 저장한다.
            result[i] = new int[]{temp, i};
        }
        Arrays.sort(result, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 첫 번째 열을 기준으로 정렬
                return Integer.compare(o1[0], o2[0]);
            }
        });

        if(result[numbers.length-1][0]==0){ //제일 앞의 값이 0이면 return 0
            return "0";
        }

        for (int [] temp: result) {         // answer에 모든 수를 합쳐 값을 구한다.
            answer = numbers[temp[1]] + answer;
        }


        return answer;
    }
}
