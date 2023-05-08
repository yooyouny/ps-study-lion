class Solution {
        public static String solution(String s, int n) {
            String answer = "";
            //문자 하나하나 저장할 StringBuilder
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < s.length(); i++){
                //하나하나 charAt으로 확인한다.
                char c = s.charAt(i);
                if(c >= 'A' && c <= 'Z'){
                    //대문자인 경우 65~26개문자 -> 91에 도달하면 0으로 돌아가야 되니 나머지
                    char tmp = (char)((c - 65 + n) % 26 + 65);
                    //문자 저장
                    sb.append(tmp);
                }
                else if(c >= 'a' && c <= 'z'){
                    //소문자의 경우 97부터 시작해 26개 문자 123에 도달하면 0으로 돌아가야 함
                    char tmp = (char)((c - 97 + n) % 26 + 97);
                    sb.append(tmp);
                }
                else// 공백의 경우
                    sb.append(c);
            }
            answer = sb.toString();

            return answer;
        }
}
