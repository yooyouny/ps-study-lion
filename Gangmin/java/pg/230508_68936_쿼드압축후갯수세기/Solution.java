import java.util.*;
class Solution {
    //정답 저장할 배열
   static int[] sAns = new int[2];
    public static int[] solution(int[][] arr) {
        int[] answer = null;
        //4개로 쪼개는 재귀 함수
        recur_proc(arr, 0, 0, arr.length);
        answer = sAns;
        return answer;
    }
    //같은 값인지 확인하는 함수
    static boolean isPossible(int[][] arr, int row, int col, int size, int val){
        //row에서 size을 더한 인덱스까지 검사
        for(int i = row; i < row + size; i++){
            //col에서 size을 더한 인덱스 까지 검사
            for(int j = col; j < col + size; j++){
                if(arr[i][j] != val)
                    return (false);
            }
        }
        return (true);
    }

    //시작 index를 row와 col에 넣어주고, 이를 기준으로 arr을 등분 및 구별한다.
    static void recur_proc(int[][] arr, int row, int col, int size){
        //첫번쨰 값을 val에 저장하고 다른 값과 비교
        int val = arr[row][col];
        if(isPossible(arr, row, col, size, val)){
            //만약 모두 같다면, val이 1이면 1번째 인덱스 증가
                if(val == 1) sAns[1]++;
            //0이면 0번째 인덱스 증가
                else sAns[0]++;
                return ;
        }
        //size 크기를 2로 계속 나누어 주면 4등분이 된다.
        //왼쪽 상단에서 시작 
        recur_proc(arr, row, col, size / 2);
        //오른쪽 상단에서 시작 -> 열 시작 인덱스를 중간으로
        recur_proc(arr, row, col + size / 2, size / 2);
        //왼쪽 하단에서 시작 -> 행 시작 인덱스를 중간으로
        recur_proc(arr, row + size / 2, col, size / 2);
        //오른쪽 하단에서 시작 -> 행과 열 모두 중간 시작
        recur_proc(arr, row + size / 2, col + size / 2, size / 2);
    }
    
}
