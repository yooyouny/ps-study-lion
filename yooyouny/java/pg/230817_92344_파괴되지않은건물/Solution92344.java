class Solution92344 {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int rowLen = board.length;
        int columnLen = board[0].length;
        int[][] building = new int[rowLen + 1][columnLen + 1]; //종료지점보다 한칸 뒤에 표시해야하므로 size + 1
        for(int[] s : skill){
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3] + 1;// 종료지점의 +1 위치에 표시
            int c2 = s[4] + 1;// 종료지점의 +1 위치에 표시
            int degree = s[0] == 1 ? -s[5] : s[5];

            /*
            +  -
            -  + 로 갱신
            * */
            building[r1][c1] += degree;
            building[r1][c2] -= degree;
            building[r2][c1] -= degree;
            building[r2][c2] += degree;
        }

        for(int i=0; i<rowLen; i++){
            for(int j=1; j<columnLen; j++){// 행의 첫번째 값을 다음위치 값에 더해서 누적합 표시
                building[i][j] += building[i][j-1];
            }
        }

        for(int i=1; i<rowLen; i++){// 열의 첫번째 값을 다음위치 값에 더해서 누적합 표시
            for(int j=0; j<columnLen; j++){
                building[i][j] += building[i-1][j];
            }
        }

        for(int i=0; i<rowLen; i++){
            for(int j=0; j<columnLen; j++){
                // 본래의 내구도에 -공격 +회복값을 더한 최종 내구도가 0 이상이면 파괴되지 않은 건물
                if(board[i][j] + building[i][j] > 0) answer++;
            }
        }
        return answer;// 파괴되지 않은 건물의 수 리턴
    }
}