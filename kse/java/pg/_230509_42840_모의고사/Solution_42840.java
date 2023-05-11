package java.pg._230509_42840_모의고사;

import java.util.ArrayList;
import java.util.Arrays;
//https://school.programmers.co.kr/learn/courses/30/lessons/42840
public class Solution_42840 {
    /**
     * 1. 수포자 3인이 찍는 패턴을 int[][]로 만들어 미리 초기화 해준다.
     * 2. 수포자 3인의 패턴의 길이를 lens에 미리 초기화 해준다.
     * 3. answer를 비교해 주며, answer의 index를 패턴길이 lens로 나눠가며
     *    패턴 배열과 함께 비교해 채점 해준다.
     * 4. score 배열에 3인의 점수를 집계 해준다.
     * 5. 집계한 점수를 바탕으로 answer를 구해준다.
     *
     * @param answers 채점해야 하는 정답 int[]
     * @return 정답자를 많이 맞춘 순서대로 넣은 int[]  + 0점받은 사람 포함 x
     */
    public int[] solution(int[] answers) {
        int[] answer = new int[3];
        int[] score  = new int[3];          //각 학생들의 점수를 담을 배열
        int[][] pattern = {                 //수포자 1,2,3의 패턴을 2차원 int 배열로 생성한다.
                {1,2,3,4,5},
                {2,1,2,3,2,4,2,5},
                {3,3,1,1,2,2,4,4,5,5}
        };
        int[] lens = {5,8,10};              //패턴의 길이를 담은 배열


        for(int i = 0 ; i < answers.length ; i++){      //모든 문제들을 채점
            for(int j = 0; j < 3; j++){
                if(answers[i]==pattern[j][i%lens[j]])   //문제 번호 i를 패턴길이로 나눈 나머지값으로 해당 찍은 번호를 찾아 비교한다.
                    score[j]++;
            }
        }

        int max = Arrays.stream(score).max().getAsInt();    //최고 점수를 구한다.

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < 3 ; i++){
            if(max == score[i])
                result.add(i+1);
        }
        answer = result.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
