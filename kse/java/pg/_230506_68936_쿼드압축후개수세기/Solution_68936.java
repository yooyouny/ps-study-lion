package java.pg._230506_68936_쿼드압축후개수세기;
//https://school.programmers.co.kr/learn/courses/30/lessons/68936
public class Solution_68936 {

    //arr의 모든 값이 같은 값인지 확인하는 메서드
    //같지 않으면 -1, 같으면 값을 return한다.
    public int checkS(int[][] arr){
        int temp = arr[0][0];                           //temp에 첫번째 값을 비교군으로 넣어준다.
        for(int i = 0 ; i < arr.length ; i++){
            for(int j = 0 ; j < arr[0].length ; j++){
                if(arr[i][j]!=temp){                    //arr[i][j]값이 temp와 같지 않으면 -1
                    return -1;
                }
            }
        }
        return temp;                                    //for문을 지날 때까지 return되지 않으면 temp return.
    }

    //배열을 4등분으로 자르는 메서드
    public static int[][][] splitArray(int[][] arr) {
        int n = arr.length;                             //n -> arr의 길이
        int[][][] result = new int[4][n/2][n/2];        //배열의 길이 n의 1/2만큼의 길이를 갖는 정사각형을 넣어줄 배열 4개를 만들어준다.

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {               //좌표평면상에서
                if (i < n/2 && j < n/2) {               //2사분면 조건
                    result[0][i][j] = arr[i][j];
                } else if (i < n/2 && j >= n/2) {       //1사분면 조건
                    result[1][i][j-n/2] = arr[i][j];
                } else if (i >= n/2 && j < n/2) {       //3사분면 조건
                    result[2][i-n/2][j] = arr[i][j];
                } else {                                //4사분면
                    result[3][i-n/2][j-n/2] = arr[i][j];
                }
            }
        }

        return result;
    }

    public int[] findNumb(int[][] arr,int[] result){    //재귀 함수
        int count = checkS(arr);                        //입력된 배열 arr의 값이 모두 같은지 확인
        int[] res = {0,0};                              //int[]으로 return 해주기 위한 배열
        if(count!=-1){                                  //입력된 배열의 값이 모두 같은 경우
            result[count]+=1;                           //count번의 값을 1더해준다.
            return result;
        }else{                                          //배열의 값이 모두 같지 않은 경우
            int[][][] temp = splitArray(arr);           //배열을 4등분 내준다.
            for(int i = 0 ; i<4;i++){                   //잘라놓은 4개의 배열을 다시 돌린다.
                findNumb(temp[i],result);
            }
            return result;
        }

    }
    public int[] solution(int[][] arr) {
        int[] answer = {0,0};
        answer = findNumb(arr, answer);
        return answer;
    }
}
