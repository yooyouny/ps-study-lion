package java.pg._230503_181921_배열만들기2;

import java.util.ArrayList;

public class Solution_181921_2 {
    public int[] solution(int l, int r) {
        int[] answer = {};
        //쓸데없는 짓을 때려치고 그냥 처음부터 끝까지 검사해주는 방식으로 바꿨다..
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = l ; i <= r ; i++){
            int temp = i;
            boolean check = true;
            while(temp>0){
                if(!(temp%10 == 0 || temp%10 == 5)){
                    check = false;
                    break;
                }
                temp/=10;
            }
            if(check == true){      //check == true 이면 result에 값을 넣어준다.
                result.add(i);
                System.out.println(i);
            }
        }
        //result가 비었으면 -1을 넣어준다.
        if(result.isEmpty()) result.add(-1);
        answer = result.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}
