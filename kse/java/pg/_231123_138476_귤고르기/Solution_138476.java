package java.pg._231123_138476_귤고르기;

import java.util.*;
//https://school.programmers.co.kr/learn/courses/30/lessons/138476
//신규_프로그래머스_lv2_138476_귤고르기
public class Solution_138476 {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        // 1. 귤 사이즈 별 개수를 구해준다.
        Map<Integer, Integer> sizes = new HashMap<>();
        for (int size : tangerine){
            sizes.put(size, sizes.getOrDefault(size, 0) + 1);
        }
        // 2. 사이즈 별 개수를 내림차순 정렬해준다.
        Queue<Integer> count = new PriorityQueue<>(Comparator.reverseOrder());
        for (Map.Entry<Integer, Integer> temp : sizes.entrySet()){
            count.add(temp.getValue());
        }
        // 3. 사이즈 별 귤 개수를 빼면서 k개를 채워준다.
        while (k > 0 && !count.isEmpty()){
            k -= count.poll();
            answer++;
        }
        return answer;
    }
}
