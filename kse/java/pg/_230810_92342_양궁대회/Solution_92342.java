package java.pg._230810_92342_양궁대회;

import java.util.Arrays;

//https://school.programmers.co.kr/learn/courses/30/lessons/92342
//신규_프로그래머스_Lv2_92342_양궁대회
public class Solution_92342 {
    int[] answer = new int[11];
    int maxDiff  = 0;

    public int[] solution(int n, int[] info) {
        int[] lion = new int[11];
        search(info, lion, n, 0);

        if(maxDiff == 0) return new int[]{-1};
        return answer;
    }

    public void search(int[] peach, int[] lion, int arrow, int idx) {
        //화살이 없거나 모든 점수를 계산한 경우
        if(idx==11 || arrow==0) {
            lion[10] += arrow; // 남은 화살은 모두 0점 과녁에
            calcScore(peach, lion);
            lion[10] -= arrow;
            return;
        }

        // idx 번째의 점수를 가져가는 경우
        if(peach[idx] < arrow) {
            lion[idx] += peach[idx]+1;
            search(peach, lion, arrow-peach[idx]-1, idx+1);
            lion[idx] -= peach[idx]+1;
        }

        // 점수를 포기한 경우
        search(peach, lion, arrow, idx+1);
    }

    //라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우를 return
    public boolean moreArrowLowPoint(int[] lion){
        for(int i=10; i>=0; i--) { //낮은 레벨 계산이니까 반대로
            if(lion[i] > answer[i]) return true;
            else if(lion[i] < answer[i]) return false;
            //같으면 다음 점수 계산
        }
        return true;
    }

    public void calcScore(int[] peach, int[] lion){
        int peachScore = 0;
        int lionScore = 0;
        for(int i=0; i<11; i++) {
            if(lion[i] > peach[i]) lionScore += 10-i;
            else if(peach[i] > 0) peachScore += 10-i;
        }

        int diff = lionScore - peachScore;
        // maxDiff 가 0부터 시작이기 때문에
        // diff 가 maxDiff 보다 크면 우승 + 최대차이를 구한다.
        if(diff>=maxDiff) {
            if(maxDiff==diff && !moreArrowLowPoint(lion)) return;

            maxDiff = diff;
            answer = lion.clone();
        }
    }

}
