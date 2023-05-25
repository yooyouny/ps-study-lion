package java.pg._230523_42746_가장큰수;

import java.util.*;
//신규_프로그래머스_lv2_42746_가장큰수
//https://school.programmers.co.kr/learn/courses/30/lessons/42746
public class Solution_42746_2 {
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();

        List<Integer> list = new ArrayList<>(); //1. numbers를 list로 바꿔준다.
        for (int val: numbers) {
            list.add(val);
        }

        //list를 정렬해준다.
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer first, Integer second) {
                // A+B 와 B+A를 비교하여 더 큰값을 앞으로 보낸다.
                String str_1 = first.toString();
                String str_2 = second.toString();
                return -Integer.compare(Integer.parseInt(str_1 + str_2), Integer.parseInt(str_2 + str_1));
            }
        });

        if(list.get(0)==0)  //가장 큰 값이 0이면 return 0
            return "0";

        for (int numb:list) {   // list순서에 맞게 숫자를 합쳐 가장 큰 수를 구한다.
            answer.append(Integer.toString(numb));
        }
        return answer.toString();
    }
}
