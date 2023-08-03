package java.pg._230803_42884_단속카메라;
// https://school.programmers.co.kr/learn/courses/30/lessons/42884
// 신규_프로그래머스_Lv3_42884_단속카메라

import java.util.*;

public class Solution_42884 {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, Comparator.comparingInt(a -> a[1])); // 끝나는 시간 기준으로 정렬
        printArray(routes);

        int camera = -30_001;       //카메라가 놓일 수 있는 경로 중 가장 앞 부분
        for (int[] temp : routes){  //모든 경로를 순회
            if(temp[0] > camera) {  //카메라가 있는 곳이 차가 진입하는 시점의 이전이면
                answer++;
                camera = temp[1];   //차가 지나가는 끝 경로에 카메라를 설치해준다.
            }
        }

        return answer;
    }
    /*
        정렬된 배열
        [-20, -15], [-18, -13], [-14, -5], [-5, -3]
        처음차가 -20 ~ -15 사이를 달리기 때문에
        첫 카메라의 위치가 -15가 된다.
     */

    public void printArray(int[][] array){
        for(int[] temp : array){
            System.out.println(Arrays.toString(temp));
        }
    }
}
