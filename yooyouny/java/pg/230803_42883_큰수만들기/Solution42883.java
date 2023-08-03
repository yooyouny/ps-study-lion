import java.util.*;

// DFS는 런타임에러,
class Solution42883 {
    public static int max = 0;
    public void comb(char[] numbers, boolean[] check, int start, int cnt){
        if(cnt == 0){
            String answer = toStr(numbers, check);
            max = Math.max(Integer.parseInt(answer), max);
            return;
        }

        for(int i=0; i<numbers.length; i++){
            if(check[i]) continue;
            check[i] = true;
            comb(numbers, check, i + 1, cnt-1);
            check[i] = false;
        }

    }
    public String solution(String number, int k) {
        char[] numbers = number.toCharArray();
        boolean[] check = new boolean[number.length()];

        comb(numbers, check, 0, k);

        return Integer.toString(max);
    }
    private String toStr(char[] numbers, boolean[] check){
        String str = "";
        for(int i=0; i<numbers.length; i++){
            if(!check[i]){
                str += numbers[i];
            }
        }
        return str;
    }
}