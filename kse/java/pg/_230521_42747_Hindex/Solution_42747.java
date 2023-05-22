package java.pg._230521_42747_Hindex;

import java.util.Arrays;
//신규_프로그래머스_2level_42747_Hindex
//https://school.programmers.co.kr/learn/courses/30/lessons/42747?language=java
public class Solution_42747 {
    /**
     * 1. citations을 정렬하여 인용수를 계산하기 쉽도록 함
     * 2. citations의 길이가 h-index의 최대 크기기 때문에 citations.length만큼 반복문을 돌려줌
     * 3. 논문의 수 : citations.length-i
     *    인용 수 : citations[i]
     *    논문의 수보다 인용수가 크거나 같으면 answer는 citations.length-i
     * @param citations 과학자가 발표한 논문 수[논문의 인용 횟수 ]
     * @return h번 인용된 논문이 h번 이상이고 나머지가 h번 이하인 경우
     */
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++) {
            if(citations.length-i <= citations[i]){
                answer = citations.length-i;
                break;
            }
        }
        return answer;
    }
}
