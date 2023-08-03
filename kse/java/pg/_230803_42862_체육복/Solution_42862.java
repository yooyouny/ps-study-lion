package java.pg._230803_42862_체육복;
// https://school.programmers.co.kr/learn/courses/30/lessons/42862
// 신규_프로그래머스_Lv1_42862_체육복

import java.util.*;

public class Solution_42862 {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] students = new int[n];
        Arrays.fill(students,1);                //모두 체육복이 있는 상태에서
        for(int temp : lost) students[temp-1]--;    //잃어버린 경우 --
        for(int temp : reserve)students[temp-1]++;  //여분이 있는 경우 ++

        //빌려 주기 전에 체육수업을 받을 수 있는 학생 수
        int answer = n - (int) Arrays.stream(students).filter(val -> val==0).count() ;


        for (int i = 0; i < n ; i++){
            //없는 학생 기준으로
            if(students[i] == 0){
                //앞에서 빌려보기
                if(i>0 && students[i-1]==2){
                    students[i]++;
                    answer++;
                }
                //뒤에서 빌려보기
                else if(i<n-1 && students[i+1]==2){
                    students[i+1]--;
                    answer++;
                }
            }
        }
        return answer;
    }
}
