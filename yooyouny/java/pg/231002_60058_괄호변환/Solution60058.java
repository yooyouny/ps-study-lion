class Solution60058 {
    public String solution(String p) {
        String answer = "";
        if(isCorrectString(p))// 올바른 괄호 문자열일 경우 변환하지 않고 반환
            return p;
        else
            answer = toBalnacedString(p);
        return answer;
    }
    private boolean isCorrectString(String s){//짝이 맞는 괄호
        if(s.charAt(0) == ')') return false;// 처음부터 )면 짝이 맞을 수 없으므로 false 리턴

        int cnt = 0;
        for(char c : s.toCharArray()){
            if(c == '(')
                cnt++;
            else
                cnt--;
            if(cnt < 0) return false;// 이미 마이너스면 더 이상의 확인이 필요없음
        }
        return cnt == 0;// 짝이 맞으면 true 리턴
    }
    private String toBalnacedString(String w){// 균형잡힌 문자열 u를 리턴

        if(w.isBlank()) return "";// 기저조건

        // 재귀시마다 u와 v를 새로 만들어줘야함
        StringBuilder u = new StringBuilder();
        StringBuilder v = new StringBuilder();

        int cnt = 0;
        for(int i=0; i<w.length(); i++){
            if(w.charAt(i) == '('){
                cnt++;
            }else{
                cnt--;
            }
            if(cnt == 0){// 균형잡힌 문자열로 분리 할 수 없을때 = 가장 처음으로 균형잡힌 문자열이 만들어졌을때
                u.append(w.substring(0, i+1));
                v.append(w.substring(i+1, w.length()));
                if(isCorrectString(u.toString())){
                    u.append(toBalnacedString(v.toString()));
                    break; //더 이상의 연산을 수행하지 않고 바로 반환
                }else{
                    String newStr = "";
                    newStr += "(";
                    newStr += toBalnacedString(v.toString());
                    newStr += ")";
                    newStr += reverseString(u.toString());
                    return newStr;// 생성된 새로운 문자열을 반환
                }
            }
        }
        return u.toString();
    }
    private String reverseString(String s){
        StringBuilder sb = new StringBuilder();
        s = s.substring(1, s.length()-1);// 첫번째, 마지막 문자 제거
        for(char c : s.toCharArray()){// 반대로 변환
            if(c == '(')
                sb.append(')');
            else
                sb.append('(');
        }
        return sb.toString();
    }
}