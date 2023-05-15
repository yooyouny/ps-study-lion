import java.util.*;
import java.util.regex.*;
class Solution {
    //피연산자
 static LinkedList<Long> operands = new LinkedList<>();
    //연산자
    static LinkedList<String> operators = new LinkedList<>();
    //어떤 연산자가 들어왔는지 확인
    static String[] opCheck = {"*", "-", "+"};
    //최댓값
    static long max = Long.MIN_VALUE;

    static void swap(int a, int b){
        String tmp = opCheck[a];
        opCheck[a] = opCheck[b];
        opCheck[b] = tmp;
    }
//연산자와 피연산자를 컬렉션에 저장하는 함수
    public static void makeList(String expression){
        Pattern pattern1 = Pattern.compile("[*\\-+]");
        Matcher matcher1 = pattern1.matcher(expression);
        Pattern pattern2 = Pattern.compile("\\d+");
        Matcher matcher2 = pattern2.matcher(expression);
        while(matcher1.find()){
            operators.add(matcher1.group(0));
        }
        while(matcher2.find()){
            operands.add(Long.parseLong(matcher2.group(0)));
        }
    }

    static long calculation(List<Long> operands, List<String> operators){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < operators.size(); j++){
                //우선순위에 맞는 연산자를 찾으면 연산
                if(operators.get(j).equals(opCheck[i])){
                    switch(opCheck[i]){
                        //j번째 값을 제거하면서 연산하고 j번째에 넣어줌
                        case "*"->operands.add(j, operands.remove(j) * operands.remove(j));
                        case "+"->operands.add(j, operands.remove(j) + operands.remove(j));
                        case "-"->operands.add(j, operands.remove(j) - operands.remove(j));
                    }

                //연산에 사용된 연산자 제거 -> list에서 제거하면 인덱스가 앞으로 당겨지니까
                //바로 뒤에 같은 연산자가 있으면 못본다.
                    operators.remove(j--);
                }
                
                
            }
        }
        //절댓값 반환
        return Math.abs(operands.get(0));
    }

    //중복을 허용하지 않는 순열 구하는 함수
    static void permutation(LinkedList<Long> operands, LinkedList<String> operators, int depth){
        //순열을 완성하면 탈출
        if(depth == 3){
            //연산에 의해 기존의 컬렉션이 영향 받으면 안되니까
            long tmp = calculation((LinkedList<Long>)operands.clone(), (LinkedList<String>)operators.clone());
            //max 갱신
            if(max < tmp)
                max = tmp;
            return;
        }
        //dfs
        for(int i = depth; i < 3; i++){
            //0번 인덱스 고정하고 트리구조로 다른 인덱스의 값을 바꾼다.
            if(i != depth)
                swap(i, depth);
            permutation(operands, operators, depth + 1);   
            //다른 트리가 영향을 받으면 안되기 때문에 다시 기존 상태로 돌려준다.
            if(i != depth)
               swap(i, depth);
        }
    }

    public static long solution(String expression) {
        makeList(expression);
        permutation(operands, operators, 0);
        long answer = max;
        return answer;
    }
}
