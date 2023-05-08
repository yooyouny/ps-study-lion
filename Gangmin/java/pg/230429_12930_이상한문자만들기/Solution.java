class Solution {
  public static String solution(String s) {
        String answer = "";
      //대 소문자 바꾸기 위해 split
        String characters[] = s.split("");
      //단어마다 홀짝 번째를 세기 위한 index
        int index = 0;
        for(int i = 0; i < characters.length; i++){
            //공백이면, index 0초기화 새 단어 시작
            if(characters[i].equals(" "))
                index = 0;
            //짝수면 대문자로 변환
            else if(index % 2 == 0){
                characters[i] = characters[i].toUpperCase();
                //단어니까 인덱스를 증가시킴
                index++;
                //홀수면 소문자로 변환
            } else if (index % 2 == 1){
                characters[i] = characters[i].toLowerCase();
                index++;
            }
            //대소문자 변환한 문자열이나, 공백을 합쳐 새 인스턴스 생성
            answer += characters[i];
        }
        return answer;
    }
}
