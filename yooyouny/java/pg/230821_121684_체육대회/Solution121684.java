class Solution121684 {
    static int answer;// 최대 점수조합
    public int solution(int[][] ability) {
        /*
        * 서로 다른 학생을 종목 개수만큼 뽑고 합산한 점수를 리턴
        * 열 = 학생
        * 행 = 종목
        * */
        dfs(0, 0, new boolean[ability.length], ability);
        return answer;
    }
    private void dfs(int event, int score, boolean[] isSelected, int[][] ability){
        if(event == ability[0].length){// 종목 개수만큼 선택을 끝냈으면
            answer = Math.max(answer, score);// 점수의 최댓값을 갱신
            return;
        }

        for(int student=0; student<ability.length; student++){//
            if(isSelected[student]) continue;
            isSelected[student] = true;// 이전에 선택하지 않았던 학생을 대표로 선출
            dfs(event + 1, score + ability[student][event], isSelected, ability);// 종목 횟수 증가하면서 다음 학생 탐색
            isSelected[student] = false;// 다른 경우의 수 탐색을 위해 i번째 학생 선출을 취소
        }
    }
}