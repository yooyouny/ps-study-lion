package java.pg._230502_81301_숫자문자열과영단어;
//https://school.programmers.co.kr/learn/courses/30/lessons/81301?language=java
public class Solution_81301 {
    public int solution(String s) {
        //미리 영문자열을 배열로 넣어준다.
        final String[] NUMBERS = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        int answer = 0;
        String result = ""; //변환 후 문자열을 넣어줄 변수
        String[] arrs = s.split("");

        int i = 0;
        //문자열을 처리할 때 i값이 문자열 길이만큼 증가해야하기 때문에 while
        while(i<arrs.length){
            //s.charAt(i)가 숫자인 경우
            if(s.charAt(i)<=57){
                result += s.substring(i,i+1);
                i++;
            }
            //영문자인 경우
            else{
                //영문자 철자가 3~5개이기 때문에 3~5개씩 날라
                //NUMBERS에서 찾아 해당하는 배열의 인텍스를 result에 추가 해준다.
                for(int j = 3; j <= 5 ; j++){
                    String temp = s.substring(i,i+j);
                    for(int k = 0 ; k < 10 ; k++){
                        if(temp.equals(NUMBERS[k])){
                            result += Integer.toString(k);
                            i+=j;
                            j=6;
                            break;
                        }
                    }
                }
            }
        }
        answer = Integer.parseInt(result);
        return answer;
    }
}
