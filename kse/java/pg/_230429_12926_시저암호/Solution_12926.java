package java.pg._230429_12926_시저암호;
//https://school.programmers.co.kr/learn/courses/30/lessons/12926
public class Solution_12926 {
    public String solution(String s, int n) {
        String answer = "";
        //문자열 s를 나눠 배열로 만들어 주기
        String[] arr_s = s.split("");

        for(int i = 0 ; i < s.length() ; i++ ){
            //String으로 저장된 문자를 char로 형변환 해주기
            int temp = (int)arr_s[i].charAt(0);

            //대문자인 경우
            if(65 <= temp && temp <= 90){
                //n만큼 이동한 경우가 Z보다 큰 경우 -26을 해준다.
                if(temp+n > 90){
                    arr_s[i]=String.valueOf((char)(temp+n-26));
                }else{
                    arr_s[i]=String.valueOf((char)(temp+n));
                }
            }
            //소문자인 경우
            else if(97 <= temp && temp <= 122){
                //n만큼 이동한 경우가 z보다 큰 경우 -26을 해준다.
                if(temp+n > 122){
                    arr_s[i]=String.valueOf((char)(temp+n-26));
                }else{
                    arr_s[i]=String.valueOf((char)(temp+n));
                }
            }

        }
        //문자 배열 arr_s를 join으로 합쳐준다.
        answer = String.join("", arr_s);
        return answer;
    }
}
