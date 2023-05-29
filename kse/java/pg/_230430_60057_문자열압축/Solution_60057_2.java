package java.pg._230430_60057_문자열압축;
//복습_프로그래머스_lv2_60057_문자열압축
public class Solution_60057_2 {
    public int solution(String s) {
        int answer = s.length();    // 압축되지 않은 문자열의 길이로 초기화
        int count;  //압축 문자열의 총 길이
        int numb;   //압축된 횟수

        //구할 수 있는 패턴의 최대 길이는 s.length()/2
        for(int i = 1 ; i <= s.length()/2; i++){
            String pattern = s.substring(0,i);  // 최초의 패턴 구하기
            count = 0;
            numb  = 1;
            for(int j = i ; j < s.length() ; j+=i){
                String temp = s.substring(j, Math.min(j + i, s.length()));  //마지막 문자열을 자를 때를 대비하여 min 사용
                if(pattern.equals(temp)){   //.temp와 pattern이 같으면 압축된 횟수 numb++;
                    numb++;
                }else{  //temp와 pattern이 다르면
                    count += (numb > 1) ? i+(int)(Math.log10(numb)+1) : i; // 압축 문자열의 총 길이에 문자열 길이 + 횟수의 길이
                    numb = 1;           //numb 초기화
                    pattern = temp;     //pattern 변경
                }
            }
            //마지막 문자열이 이전 문자열과 같아 처리되지 않았을 경우를 위해 실행
            count += (numb == 1) ? pattern.length() : pattern.length()+(int)(Math.log10(numb)+1);
            if(answer > count) answer = count;  //최솟값을 answer에 저장해준다.
        }
        return answer;
    }
}
