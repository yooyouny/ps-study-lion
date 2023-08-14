class Solution {
    int[][] gradiantMap;
    int mapRow;
    int mapColumn;
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        mapRow = board.length + 1;
        mapColumn = board[0].length + 1;
        gradiantMap = new int[mapRow][mapColumn];
        getGradiant(skill);
        return apply(board);
    }

    //누적합을 구한다.
    private void getGradiant(int[][] skill){
        //누적합을 구하기 위한 숫자 배치 작업
        for (int[] each : skill) {
            int degree = each[5];
            int contrast = each[5] * -1;
            if(each[0] == 1){
                gradiantMap[each[1]][each[2]] += contrast;
                gradiantMap[each[1]][each[4] + 1] += degree;
                gradiantMap[each[3] + 1][each[2]] += degree;
                gradiantMap[each[3] + 1][each[4] + 1] += contrast;
            }else {
                gradiantMap[each[1]][each[2]] += degree;
                gradiantMap[each[1]][each[4] + 1] += contrast;
                gradiantMap[each[3] + 1][each[2]] += contrast;
                gradiantMap[each[3] + 1][each[4] + 1] += degree;
            }
        }
        //누적합 구하기
        accumulate();
    }

    private void accumulate(){
        for (int i = 0; i < mapColumn; i++) {
            for (int j = 0; j < mapRow - 1; j++) {
                gradiantMap[j + 1][i] += gradiantMap[j][i];
            }
        }
        for (int i = 0; i < mapRow; i++) {
            for (int j = 0; j < mapColumn - 1; j++) {
                gradiantMap[i][j + 1] += gradiantMap[i][j];
            }
        }
    }
    //누적합을 적용한 map과 board 1대1연산
    private int apply(int[][] board){
        int count = 0;
        for (int i = 0; i < mapRow - 1; i++) {
            for (int j = 0; j < mapColumn - 1; j++) {
                board[i][j] += gradiantMap[i][j];
                if(board[i][j] > 0) count++;
            }
        }
        return count;
    }

    private void printMap(){
        for (int[] ints : gradiantMap) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printBoard(int[][] board){
        for (int[] ints : board) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
