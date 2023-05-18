package java.pg._230517_42748_K번째수;

import java.util.Arrays;

//https://school.programmers.co.kr/learn/courses/30/lessons/42748?language=java
public class Solution_42748 {
    /**
     * 1. 배열을 잘라 임시 저장 배열 temp에 넣어준다.
     * 2. temp를 정렬해준다.
     * 3. temp에서 k번때에 해당하는 수를 answer에 넣어준다.
     *
     * @param array     자르고 정렬할 배열
     * @param commands  자를 범위와 자리를 알려주는 배열
     * @return answer
     */
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        int[] temp;
        for (int i = 0 ; i < commands.length ; i++) {
            temp = Arrays.copyOfRange(array,commands[i][0]-1,commands[i][1]);
            Arrays.sort(temp);
            answer[i] = temp[commands[i][2]-1];
        }
        return answer;
    }
    public static void main(String[] args) {
        Solution_42748 solution42748 = new Solution_42748();
        solution42748.solution(new int[]{1, 5, 2, 6, 3, 7, 4},new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}});
    }
}
