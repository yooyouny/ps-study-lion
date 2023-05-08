package java.pg._230504_72410_신규아이디추천;
//https://school.programmers.co.kr/learn/courses/30/lessons/72410
public class Solution_72410 {
    public String solution(String new_id) {
        String answer = "";

        //1_대문자를 소문자로 치환
        new_id = new_id.toLowerCase();

        //2_알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거
        new_id = new_id.replaceAll("[^a-z0-9._-]","");

        //3_마침표 정리
        String temp = "";
        boolean point = false;
        for(char s : new_id.toCharArray()){
            if(s == '.'){
                if(!point){
                    temp += s;
                    point = true;
                }
            }else{
                temp += s;
                point = false;
            }
        }

        //4_처음 끝 제거
        if(!temp.isEmpty()){
            if(temp.charAt(0)=='.')temp = temp.substring(1);
        }
        if(!temp.isEmpty()){
            if(temp.charAt(temp.length()-1)=='.')
                temp = temp.substring(0, temp.length()-1);}

        //5_빈문자열
        if(temp.isEmpty()) temp = "a";

        //6_1 16개 이상일 때 자르기
        if(temp.length()>=16) temp = temp.substring(0,15);

        //6_2 마지막 문자 마침표 제거
        while(temp.charAt(temp.length()-1)=='.')
            temp = temp.substring(0, temp.length()-1);

        //7_길이가 2 이하인 경우
        while(temp.length()<=2) temp += temp.substring(temp.length()-1);

        answer = temp;
        return answer;
    }
}
