
import java.util.Arrays;
import java.util.Stack;

class Solution {
    public String[] solution(int[][] queries) {
        String[] answer = new String[queries.length];
        int index = 0;
        for (int[] query : queries) {
            int generation = query[0];
            int order = query[1] - 1;
            answer[index++] = proc(generation,order);
        }
        return answer;
    }

    private String proc(int generation, int order){

        Stack<Integer> stack = new Stack();
            while(generation != 1){
                //자식의 몇번쨰
                stack.push(order % 4);
                //부모의 순서
                order /= 4;
                generation--;
            }
            while(!stack.isEmpty()){
                Integer parentOrder = stack.pop();
                //부모가 0이면 무조건 RR, 3이면 자식은 무조건 rr
                if(parentOrder == 0) return "RR";
                if (parentOrder == 3) return  "rr";
            }
            return "Rr";
    }
}
