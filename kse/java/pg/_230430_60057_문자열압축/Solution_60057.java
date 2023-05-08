package java.pg._230430_60057_문자열압축;

//https://school.programmers.co.kr/learn/courses/30/lessons/60057

public class Solution_60057 {
    public int solution(String s) {
        int answer = s.length();
        String[] spells = s.split("");

        //i개 이상의 문자열로 합치는 경우를 보기 위해 1~s.length/2까지 확인
        //압축단위가 될 수 있는 문자열의 길이는 최대 s.length/2
        for(int len = 1 ; len <= spells.length/2 ; len++){
            String comp = "";       //압축된 문자열을 넣을 변수
            int cnt = 1;            //문자열 길이

            //처음 확인할 문자열을 미리 잘라서 저장
            String temp = s.substring(0,0+len);

            //처음 문자열을 제외하고 시작하기 위해 i=len
            //문자열 길이만큼 증가시키기 위해 증감연산자는 i=i+len
            for(int i = len; i <= spells.length-len ; i=i+len){
                //temp와 i부터 len만큼 잘라낸 문자열 길이가 같다면 다음으로 넘어감
                if(temp.equals(s.substring(i,i+len))){
                    cnt++;
                }
                //다르다면 이 이상 temp 문자열로 압축이 불가능하기 때문에
                //comp에 저장해주기 위한 계산을 진행
                else{
                    if(cnt==1){         //cnt가 1이면 앞에 숫자를 붙줄 필요는 x
                        comp += temp;
                    }else{              //1 이상인 경우 몇개 압축하는지 + 문자열을 추가 해줌
                        comp += Integer.toString(cnt)+temp;
                    }
                    //다음 계산을 위해 재초기화
                    cnt = 1;
                    temp = s.substring(i,i+len);
                }

            }
            //마지막에 잘라낸 string은 다음 string과 연산을 진행할 수 없기 때문에
            //한 번 따로 실행 해준다.
            if(cnt==1){
                comp += temp;
            }else{
                comp += Integer.toString(cnt)+temp;
            }
            //n개씩 자르는 경우 마지막에 잘라지지 않는 남는 문자열이 있을 수 있다.
            //남는 문자열을 comp에 넣어준다.
            if(s.length()%len!=0){
                comp += s.substring(s.length()-s.length()%len, s.length());
            }

            //answer보다 comp의 길이가적으면 answer = comp.length()
            if(answer > comp.length()){
                answer = comp.length();
            }
        }


        return answer;
    }
}
