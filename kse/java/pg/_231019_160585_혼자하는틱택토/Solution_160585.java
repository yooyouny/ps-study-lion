package java.pg._231019_160585_혼자하는틱택토;

import java.util.Arrays;

//https://school.programmers.co.kr/learn/courses/30/lessons/160585
//신규_프로그래머스_lv2_160585_혼자하는틱택토
public class Solution_160585 {
    /**
     * 진행 순서 : 0 -> X
     * -----------------
     * 정상 게임 조건
     * 1. O와 x의 개수가 같거나 O가 하나 더 많아야 한다.
     * 2. O와 X의 성공조건을 둘다 만족 했을 때
     * 3. O가 X보다 1개 많을 때 O가 이기거나 결과가 안나와야 한다.
     * 4. O와 X의 수가 같을 떄, X가 이기거나 결과가 안나와야 한다.
     */
    public int solution(String[] board) {
        char[][] chars = Arrays.stream(board).map(String::toCharArray).toArray(char[][]::new);

        // index 0 -> 'O' 1 -> 'X'
        // 말 개수를 센다.
        int[] count = countCheck(chars);
        // 성공 조건을 만족했는지 확인
        boolean[] finish = checkFinish(chars);

        switch (count[0] - count[1]){
            // O와 X의 수가 같을 떄, X가 이기거나 결과가 안나와야 한다.
            case 0 -> {
                if((!finish[0] && finish[1]) || (!finish[0] && !finish[1]))
                    return 1;
            }
            // O가 X보다 1개 많을 때 O가 이기거나 결과가 안나와야 한다.
            case 1 -> {
                if((finish[0] && !finish[1]) || (!finish[0] && !finish[1]))
                    return 1;
            }
        }

        return 0;
    }

    private boolean[] checkFinish(char[][] board) {
        boolean finishO = false;
        boolean finishX = false;

        for (int i = 0; i < 3; i++) {
            //가로 일치 확인
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == 'O') finishO = true;
                else if (board[i][0] == 'X') finishX = true;
            }
            //세로 일치 확인
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] == 'O') finishO = true;
                else if (board[0][i] == 'X') finishX = true;
            }
        }
        //대각선 확인
        if((board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0])){
            if (board[1][1] == 'O') finishO = true;
            else if (board[1][1] == 'X') finishX = true;
        }

        return new boolean[]{finishO, finishX};
    }

    public int[] countCheck(char[][] board){
        int[] count = new int[2];
        for (char[] line : board) {
            for (char temp : line) {
                if (temp == 'O') count[0]++;
                else if(temp == 'X') count[1]++;
            }
        }
        return count;
    }
}

