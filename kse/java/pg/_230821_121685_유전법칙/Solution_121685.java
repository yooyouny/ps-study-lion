package java.pg._230821_121685_유전법칙;

import java.util.ArrayList;
import java.util.List;

//https://school.programmers.co.kr/learn/courses/15008/lessons/121685
//신규_프로그래머스_lv1_121685_유전법칙
public class Solution_121685 {

    // Rr 을 기준으로 나오는 자식은 RR Rr Rr rr
    // 4번째 자식의 경우 앞의 1/4은 반드시 RR 끝의 1/4은 rr이 나온다.
    // 이를 배제하고 남은 값을 여러번 계산해주면서 값을 구한다.
    public String[] solution(int[][] queries) {
        List<String> result = new ArrayList<>();

        for (int[] query : queries) {
            int start = 0;
            int end = (int) Math.pow(4, query[0] - 1);  //해당 세대의 총 자식의 수
            int number = query[1];  //내가 찾을 자식의 번호

            while (end - start >= 4) {
                int quarter = (end - start) / 4;    // 1/4 지점

                if (number <= start + quarter) {
                    result.add("RR"); break;
                }
                else if (number <= start + quarter * 2) {
                    start += quarter;
                    end = start + quarter;
                }
                else if (number <= start + quarter * 3) {
                    start += quarter * 2;
                    end = start + quarter;
                }
                else {
                    result.add("rr");  break;
                }
            }

            if (end - start < 4) result.add("Rr");  //end 와 start 의 차가 4이하인 경우 Rr이 나온다.
        }

        return result.toArray(new String[0]);
    }
}
