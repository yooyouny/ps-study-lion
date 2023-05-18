import java.util.*;
class Solution {
   //subArray에서 command번쨰 idx를 가져오는 함수
    public static int getNumber(int[] array, int[] command){
       //subArray생성 - idx가 1부터 시작
        int[] temp = Arrays.copyOfRange(array, command[0] - 1, command[1]);
       //정령 
        Arrays.sort(temp);
        //문제의 idx가 1부터시작하는조건 때문에 맞춰줘야한다.
        return temp[command[2] - 1];
    }
    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        //commands의 모든 요소에 대해 진행
        for (int i = 0; i < answer.length; i++) {
            answer[i] = getNumber(array, commands[i]);
        }
        return answer;
    }
}
