package java.pg._230925_42885_구명보트;

import java.util.Arrays;

//https://school.programmers.co.kr/learn/courses/30/lessons/42885
//신규_프로그래머스_lv2_42885_구명보트
public class Solution_42885 {
    /**
     * 구명보트는 작아서 한 번에 최대 2명씩 밖에 탈 수 없고, 무게 제한도 있다.
     * @param people    사람들의 몸무게를 담은 배열
     * @param limit     구명보트의 무게 제한
     * @return 모든 사람을 구출하기 위해 필요한 구명보트 개수의 최솟값
     */
    public int solution(int[] people, int limit) {
        int answer = 0;
        // 사람 몸무게를 오름차순으로 정렬한다.
        Arrays.sort(people);

        // 가장 작은 값끼리 더했는데 limit 를 넘는다 -> 사람 수 만큼의 배가 필요하다.
        if(people[0] + people[1] >  limit) return people.length;

        int start = 0;
        int end = people.length - 1;
        while (start < end){
            //start 와 end 의 합이 limit 를 넘지 않는 경우
            if(people[start] + people[end] <= limit){
                start += 1;
            }
            //limit 를 넘는 경우 end 는 혼자 가야 한다.
            end -= 1;
            answer += 1;
        }
        // 탑승자가 1명이라 while 을 돌지 않았을 경우 보트는 1대 필요하다.
        if(start == end) answer += 1;
        return answer;
    }
}
