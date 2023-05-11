class Solution68936 {
	int zeroCnt, oneCnt = 0;// 재귀로 호출되는 compress함수에서 호출마다 일정한 값을 유지해야하므로 클래스 변수로 선언
	public void compress(int iStart, int iEnd, int jStart, int jEnd, int[][] arr){

		boolean isSame = true;// 해당 범위의 값이 모두 같은 값인지 체크할 boolean 변수
		int num = arr[iStart][jStart];// 비교할 값

		for (int i = iStart; i < iEnd; i++) {
			for (int j = jStart; j < jEnd; j++) {
				if (num != arr[i][j]) {// 해당 범위에서 값이 동일하지 않으면 false로 반복문을 빠져나옴
					isSame = false;
					break;
				}
			}
			if (!isSame) break;
		}

		if (isSame) {// 해당 영역의 값이 모두 같으면 영역의 범위를 세고 함수 호출을 끝냄
			if(num == 0)
				zeroCnt++;
			else// 배열의 값은 0 아니면 1
				oneCnt++;
			return;// isSame인 경우 압축을 끝냄
		}

		// 영역의 값이 같지 않으면 전체배열을 4개의 부분으로 분할하여 진행
		int iMid = (iStart + iEnd) / 2;
		int jMid = (jStart + jEnd) / 2;

		compress(iStart, iMid, jStart, jMid, arr);// 좌측 상단
		compress(iStart, iMid, jMid, jEnd, arr);// 좌측 하단
		compress(iMid, iEnd, jStart, jMid, arr);// 우측 상단
		compress(iMid, iEnd, jMid, jEnd, arr);// 우측 하단
	}

	public int[] solution(int[][] arr) {
		compress(0, arr.length, 0, arr.length, arr);// 전체 배열 압축부터 시작
		return new int[]{zeroCnt, oneCnt};// 결과배열에 압축된 배열의 0의 개수, 1의 개수 리턴
	}
}
