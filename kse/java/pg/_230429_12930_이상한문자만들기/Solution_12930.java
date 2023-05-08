package java.pg._230429_12930_이상한문자만들기;
//https://school.programmers.co.kr/learn/courses/30/lessons/12930
public class Solution_12930 {
    public String solution(String s) {
        String answer   = "";
        String[] spells = s.split("");    //문자열 s를 배열로 바꿔준다.
        boolean check   = true;                 //소문자로 바꿔야 하는지 대문자로 바꿔야 하는지 확인
                                                //true : 대문자 false : 소문자

        for(int i = 0 ; i < spells.length ; i++){
            //공백 문자연 경우
            if(spells[i].equals(" ")){
                answer += " ";      //answer에 공백을 넣어주고
                check = true;       //다음에 문자가 오는 경우 첫글자는 대문자이기 때문에 true
            }else{
                if(check == true){  //짝수인 경우 ( 0,2,4...)
                    answer += spells[i].toUpperCase();
                    check = false;
                }else{              //홀수인 경우 ( 1,3,5...)
                    answer += spells[i].toLowerCase();
                    check = true;
                }
            }
        }
        return answer;
    }
}
