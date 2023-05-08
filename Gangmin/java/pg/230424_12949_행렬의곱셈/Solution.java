class Solution {
  //행과 열을 곱셈으로 계산해 더한 값-> 행렬의 배열의 요소값 구하는 함수
	public static int proc(int[] row, int[] col){
        int sum = 0;
        for(int i = 0; i < row.length; i++){
            sum += row[i] * col[i];
        }
        return sum;
    }

  //두번째 배열의 행과 열을 뒤바꾸는 함수  
  public static int[][] colToRow(int[][] arr){
        int[][] newArr = new int[arr[0].length][arr.length];
        for(int i = 0; i < arr[0].length; i++){
            for(int j = 0; j < arr.length; j++){
                newArr[i][j] = arr[j][i];
            }
        }
        return (newArr);
    }

    public int[][] solution(int[][] arr1, int[][] arr2) {
        int row = arr1.length;
        int col = arr2[0].length;
        int[][] newArr2 = colToRow(arr2);
        int[][] answer = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int[] rowSample = arr1[i];
                int[] colSample = newArr2[j];
		//구한 값을 해당하는 요소에 넣는다.
		//i는 첫번째 배열의 행, j는 두번째 배열의 열
                int val = proc(rowSample, colSample);
                answer[i][j] = val;
            }
        }
        return answer;
    }
}
