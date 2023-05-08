class Solution {
    boolean solution(String s) {
        boolean answer = true;
        //p와 y의 갯수를 세어줄 변수
        int pCount = 0;
        int yCount = 0;
        for(int i =0 ; i < s.length(); i++){
            //p일때 증가
            if(s.charAt(i) == 'p' || s.charAt(i) == 'P')
                pCount++;
            //y일때 증가
            if(s.charAt(i) == 'y' || s.charAt(i) == 'Y')
                yCount++;
        }
        //p와 y의 갯수가 다르면 false
        if(pCount != yCount)
            answer = false;

        return answer;
    }
}
