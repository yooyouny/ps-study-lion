
class Solution {
       public String solution(String p) {
        if(checkCorrect(p)) return p;
        return recursion(p);
    }

    public boolean checkCorrect(String str){
        int left = 0;
        if(str.charAt(0) == ')') return false;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(') left++;
            else left--;
            if(left < 0) return false;
        }
        return true;
    }

    private String recursion(String w){
        StringBuilder u = new StringBuilder();
        StringBuilder v = new StringBuilder();
        if(w.isEmpty()) return "";
        int left = 0;
        for (int i = 0; i < w.length(); i++) {
            if(w.charAt(i) == '(') left++;
            else left--;
            if(left == 0){
                u.append(w.substring(0, i + 1));
                v.append(w.substring(i + 1));
                if(checkCorrect(u.toString())){
                    u.append(recursion(v.toString())).toString();
                    break;
                }else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("(").append(recursion(v.toString())).append(")");
                    sb.append(reverse(u.toString()));
                    return sb.toString();
                }
            }
        }
        return u.toString();
    }

    private String reverse(String str){
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < str.length() - 1; i++) {
            if(str.charAt(i) == '(') sb.append(")");
            else sb.append("(");
        }
        return sb.toString();
    }
}
