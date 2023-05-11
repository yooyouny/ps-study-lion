import java.util.*;

class Solution {
  public static int setScore(int[] cand, int[] answers, int size){
        int score = 0;
        for(int i = 0; i < answers.length; i++){
            //인덱스 실수
            //size만큼 반복되니까 나머지로 확인
            if(answers[i] == cand[i % size]){
                score++;
            }
        }
        return score;
    }
    public static int[] solution(int[] answers) {
        //1,2,3번 수포자의 정답
        int[][] cand = {{1,2,3,4,5}, {2,1,2,3,2,4,2,5}, {3,3,1,1,2,2,4,4,5,5}};
        //각 수포자의 점수
        int[] scoreSet = new int[3];
        //최댓값 확인
        int max = 0;
        //가변길이를 위한 컬렉션
        ArrayList<Integer> al = new ArrayList<>();
        //점수 계산
        for(int i = 0; i < 3; i++){
            scoreSet[i] = setScore(cand[i], answers, cand[i].length);
        }
        //최댓값확인
        max = Math.max(scoreSet[0], Math.max(scoreSet[1], scoreSet[2]));
        for(int i = 0; i < 3; i++){
            //중복되는 1등이 있으면 컬렉션에 담기
            if(max == scoreSet[i]){
                al.add(i + 1);
            }
        }
        //1등의 수만큼 배열 생성
        int[] answer = new int[al.size()];
        for( int i = 0; i < al.size(); i++){
            answer[i] = al.get(i);
        }
        return answer;
    }
}

