class Solution {
 public int[] solution(String s) {
     //원본 문자열 길이 저장
        int original = 0;
     //삭제 된 0의 길이 저장
        int deleted = 0;
     //삭제 이후의 문자열 길이 저장
        int shortened = 0;
     //반복 횟수 저장
        int count = 0;
        //문자열의 삭제를 위한 StringBuffer 객체
        StringBuffer sb = new StringBuffer(s);
        //답을 저장할 String
        String answerSheet = new String();
        //"1"이 될때까지 확인하는 while반복문
        while(!s.equals("1")){
            //삭제된 0의 갯수 확인을 위해 원본 문자열의 길이를 저장할 origingal
            original = sb.length();
            //0을 삭제하는 for문
            for(int i = sb.length() - 1; i >= 0; i--){
                if(sb.charAt(i) == '0'){
                    sb.deleteCharAt(i);
                }
            }
            //삭제 이후 문자열 길이저장 
            shortened = sb.length();
            //삭제된 0의 갯수
            deleted += original - shortened;
            //StringBuffer초기화
            sb.setLength(0);
            //삭제 이후 문자열의 길이를 2진법 변환
            sb.append(Integer.toString(shortened, 2));
            //equals 오버라이딩 안되는 final클래스 여서 String에 다시 저장
            s = sb.toString();
            count++;
        }
        int[] answer = new int[2];
        answer[0] = count;
        answer[1] = deleted;
        return answer;
    }
}
