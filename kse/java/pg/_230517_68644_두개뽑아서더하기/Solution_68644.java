package java.pg._230517_68644_두개뽑아서더하기;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//https://school.programmers.co.kr/learn/courses/30/lessons/68644
public class Solution_68644 {
    public int[] solution(int[] numbers) {
        int[] answer = {};

        Set<Integer> result = new HashSet<>();

        for(int i = 0 ; i < numbers.length; i++){
            for(int j = 0 ; j < numbers.length ; j++){
                if(i!=j){
                    result.add(numbers[i]+numbers[j]);
                }
            }
        }
        answer = result.stream().mapToInt(i -> i).toArray();
        Arrays.sort(answer);
        return answer;
    }
}
