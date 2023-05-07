class Solution12949 {
	public int[][] solution(int[][] arr1, int[][] arr2) {
		int[][] answer = new int[arr1.length][arr2[0].length];// 행렬의 크기는 앞쪽의 행 * 뒷쪽의 열

		for(int i = 0 ; i < arr1.length ; i++){// arr1 행의 길이
			for(int j = 0 ; j < arr2[0].length ; j++){// arr2 열의 길이
				for(int k = 0 ; k < arr1[0].length ; k++) {// arr1 열의 길이거나 arr2 행의 길이
					answer[i][j] += arr1[i][k] * arr2[k][j];
				}
			}
		}

		return answer;
	}
}
