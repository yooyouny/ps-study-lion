public class Pg92342_answer {
    int maxDiff = Integer.MIN_VALUE;
    int[] res = {-1};
    int[] ryan;



    private void backtrack(int count, int arrowsLimit, int[] info){
        //TODO : 종료 조건 모든 화살을 다 쓴 경우
        if(count == arrowsLimit){
            int apeachPoint = 0;
            int ryanPoint = 0;
            for (int i = 0; i <= 10; i++) {
                if(!(info[i] == 0 && ryan[i] == 0)){
                    //TODO: ryan이 큰 경우 ryan의 점수를 올린다.
                    if(info[i] < ryan[i]){
                        ryanPoint += 10 - i;
                    }else {
                        apeachPoint += 10 - i;
                    }
                }
            }
            //TODO: ryan이 크면, 차이를 최대 차이와 비교한다.
            if(ryanPoint > apeachPoint){
                if(ryanPoint - apeachPoint >= maxDiff){
                    //최대 차이보다 크면 복사
                    res = ryan.clone();
                    maxDiff = ryanPoint - apeachPoint;
                }
            }
            return;
        }
        //TODO: apeach를 이길때 까지 화살을 고르기,
        for (int i = 0; i <= 10 && ryan[i] <= info[i]; i++) {
            ryan[i]++;
            backtrack(count + 1, arrowsLimit, info);
            //화살을 하나씩 분배해서 모든 경우의 수를 구한다.
            ryan[i]--;
        }
    }

    int[] solution(int n, int[] info){
        ryan = new int[11];
        backtrack(0, n, info);
        return res;
    }
    public static void main(String[] args) {
        int[] solution = new Pg92342_answer().solution(5, new int[] {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0});
        System.out.println(Arrays.toString(solution));
    }
}

