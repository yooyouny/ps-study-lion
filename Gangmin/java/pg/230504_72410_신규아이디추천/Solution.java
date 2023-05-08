class Solution {
     public static String solution(String new_id) {
        String answer = "";
         //소문자를 대문자로
        answer = new_id.toLowerCase();
         //[]안에 포함된 문자 이외의 정규식 패턴에 매칭되는 문자를 모두 지운다.
        answer = answer.replaceAll("[^-_.a-z0-9]", "");
         //.이 두번이상 나오면 .으로 바꾼다.
        answer = answer.replaceAll("[.]{2,}", ".");
         //첫번째나 마지막에 .이 나오면 삭제한다.
        answer = answer.replaceAll("^[.]|[.]$", "");
         //빈문자열이면 a추가
        if(answer.equals(""))
            answer += "a";
         //길이가 16이상이면 15로 바꾸고 .이 뒤에 있으면 제거
        if(answer.length() >= 16) {
            answer = answer.substring(0, 15);
            answer = answer.replaceAll("[.]$", "");
        }
         //길이가 2이하면
        if(answer.length() <= 2){
            //3보다 커질때 까지 마지막 문자를 붙이는 것을 반복
            while(answer.length() < 3)
                answer += answer.charAt(answer.length() - 1);
        }

        return answer;
    }
}
