package java.pg._230503_70129_이진변환반복하기;
//https://school.programmers.co.kr/learn/courses/30/lessons/70129
public class Solution_70129 {
    public int[] solution(String s) {
        //{ 이진 변환의 횟수 , 제거된 0의 개수}
        int[] answer = {0,0};

        //s를 배열형태로 쪼개준다.
        String[] spells = s.split("");
        //배열 길이를 담을 변수 length
        int length;
        do{
            length = 0;
            //s.의 문자가 들어있는 배열 spells for문을 돌려
            //1이 나오면 length를 1씩 더해주고
            //0이 나오면 anser[1] : 0의 개수를 더해준다.
            for(String temp : spells){
                if(temp.equals("1"))
                    length++;
                else
                    answer[1]++;

            }
            //s를 문자열 길이 length를 2진 문자열로 바꿔주고 다시 배열로 만들어 준다.
            s = Integer.toBinaryString(length);
            spells = s.split("");
            answer[0]++;
        }while(length>1);

        return answer;
    }
}
