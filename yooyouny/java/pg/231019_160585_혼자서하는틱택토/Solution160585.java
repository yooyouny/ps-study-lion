import java.util.stream.*;
import java.util.*;
class Solution160585 {
    /*
    선공 - O
    후공 - X

    보드 상태가 잘못된 케이스들
    1. o의 개수보다 x의 개수가 많은 경우
    2. o의 개수는 x보다 하나 많아야 하는데 그것보다 더 많은 경우
    3. o이 이긴 상황인데 x의 개수가 o의 개수보다 같거나 많은 경우
    4. x가 이긴 상황인데 o의 개수가 x의 개수보다 많은 경우
    * */
    public int solution(String[] board) {
        int fail = 0;
        int success = 1;

        String concat = Arrays.stream(board).collect(Collectors.joining(""));
        long oCnt = concat.chars().filter(c -> c == 'O').count(); // 성공 케이스가 O,X 개수에 따라 확인되므로 보드에 있는 O의 개수 구하기
        long xCnt = concat.chars().filter(c -> c == 'X').count();

        char[][] charBoard = new char[3][3];
        charBoard[0] = board[0].toCharArray();
        charBoard[1] = board[1].toCharArray();
        charBoard[2] = board[2].toCharArray();

        boolean oWin = isWin(charBoard, "OOO");// 가로, 세로, 대각선 중 해당 패턴이 한줄이라도 있으면 이긴 상황으로 간주
        boolean xWin = isWin(charBoard, "XXX");

        if(xCnt > oCnt)// case 1
            return fail;

        if(oCnt > xCnt + 1)// case 2
            return fail;

        if(oWin && xCnt >= oCnt)// case 3
            return fail;

        if(xWin && oCnt > xCnt)// case 4
            return fail;

        return success;
    }
    private boolean isWin(char[][] board, String pattern){
        // 가로
        for(int i=0; i<3; i++){
            String s = "";
            for(int j=0; j<3; j++){
                s += board[i][j];
            }
            if(s.equals(pattern)) return true;// 한번이라도 패턴에 맞을 경우 이긴 상황으로 간주
        }

        //세로
        for(int i=0; i<3; i++){
            String s = "";
            for(int j=0; j<3; j++){
                s += board[j][i];
            }
            if(s.equals(pattern)) return true;
        }

        //대각선
        String forward = "";
        String reverse = "";
        for(int i=0; i<3; i++){
            forward += board[i][i];
            reverse += board[i][2-i];
        }
        if(forward.equals(pattern)) return true;
        if(reverse.equals(pattern)) return true;

        return false;
    }
}