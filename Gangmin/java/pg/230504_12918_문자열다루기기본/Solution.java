class Solution {
    public boolean solution(String s) {
        boolean answer = true;
        //문자열의 길이가 4 또는 6인지 검사
        if(s.length() == 4 || s.length() == 6){
            for(int i = 0; i < s.length(); i++){
                //숫자가 아니면 false
                if(!Character.isDigit(s.charAt(i))){
                    answer = false;
                    break;
                }
            }
        }
        else
            answer = false;
        return answer;
    }
}
