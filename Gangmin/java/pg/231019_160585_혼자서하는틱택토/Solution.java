class Solution {
    public int solution(String[] board) {
        int XCount = 0;
        int OCount = 0;
        for (int i = 0; i < board.length; i++) {
            OCount += countChar(board[i], 'O');
            XCount += countChar(board[i], 'X');
        }
        //TODO : X가 O보다 많은 경우
        if(XCount > OCount) return 0;
        //TODO: O가 X보다 2개 많은 경우
        if(OCount > XCount + 1) return 0;
        //TODO: O가 이겼는데, X가 O의 갯수와 같은 경우
        if(ifWinnerIs('O', board)){
            if(XCount >= OCount){
                return 0;
            }
        }
        //TODO: X가 이겼는데, O가 X보다 많은 경우
        if(ifWinnerIs('X', board)){
            if(OCount > XCount){
                return 0;
            }
        }
        return 1;
    }


    private int countChar(String line, char target){
        return line.length() - line.replace(String.valueOf(target), "").length();
    }

    private boolean ifWinnerIs(char ch, String[] board){
        for (int i = 0; i < board.length; i++) {
            if(board[i].charAt(0) == ch && board[i].charAt(1) == ch && board[i].charAt(2) == ch) {
                return true;
            }
            if(board[0].charAt(i) == ch && board[1].charAt(i) == ch && board[2].charAt(i) == ch){
                return true;
            }
        }
        if(board[0].charAt(0) == ch && board[1].charAt(1) == ch && board[2].charAt(2) == ch){
            return true;
        }
        if(board[0].charAt(2) == ch && board[1].charAt(1) == ch && board[2].charAt(0) == ch){
            return true;
        }
        return false;
    }
}
