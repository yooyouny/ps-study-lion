package java.pg._230509_12946_하노이탑;

import java.util.ArrayList;

//https://school.programmers.co.kr/learn/courses/30/lessons/12946
public class Solution_12946 {
    /**
     * 재귀 함수
     * 1. 원판 n을    1 -> 3 옮기려면
     * 2. 원판 n-1을  1 -> 2 옮기고
     * 3. 원판 n-1을  2 -> 3 옮겨야 한다.   (1, 3, 2) (from, to, bef)
     *
     * 원판 n-1을  2 -> 3 옮기려면         (2, 3, 1)  (bef, to, from)
     * 원판 n-2를  2 -> 1 옮기고
     * 원판 n-2를  1 -> 3 ....
     *
     * @param result 원판 옮기는 경로를 담아줄 ArrayList int[2]값을 넣어준다.
     * @param n      옮겨야 하는 원판의 번호(넓이)
     * @param from   원판 n을 어디서(from)
     * @param to     어디로(to) 옮겨야 한다.
     * @param bef    n번째 원판을 form에서 to로 옮기기 전에 나머지 원판을 bef로 옮겨야 한다.
     */
    public void hanoi(ArrayList<int[]> result, int n, int from, int to, int bef){
        if(n==1){
            result.add(new int[] {from, to});
            return;
        }else{
            hanoi(result, n-1, from, bef, to);
            result.add(new int[] {from, to});
            hanoi(result, n-1, bef, to, from);
        }

    }

    /**
     *
     * @param n     n개의 원판
     * @return      원판을 옮기는 경로를 int[][2]형태로 return
     */
    public int[][] solution(int n) {
        int[][] answer;
        ArrayList<int[]> result = new ArrayList<>();
        hanoi(result,n,1,3,2);

        answer = new int[result.size()][2];
        for(int i = 0 ; i < result.size() ; i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
}
