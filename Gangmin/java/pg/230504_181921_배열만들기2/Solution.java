import java.util.*;
class Solution {
      static boolean isNumVaild(int n){
        boolean ret = true;
        while(n > 0){
            if(n % 10 != 5 && n % 10 != 0){
                ret = false;
                break;
            }
            n /= 10;
        }
        return ret;
    }
    public static int[] solution(int l, int r) {
        LinkedList<Integer> list = new LinkedList<Integer>();

        int[] answer;
        for(int i = l; i <= r; i++){
            if(isNumVaild(i)){
                list.add(i);
            }
        }
        if(list.size() == 0){
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }
        answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}
