package java.pg._230513_67257_수식최대화;
//https://school.programmers.co.kr/learn/courses/30/lessons/67257
public class Solution_67257 {
    /**
     * 1. 계산순서 depth에 맞는 값을 splits에서 꺼내준다.
     * 2. "+"인 경우 calc()의 return값을 더해주고 "-"면 return값을 빼주는 형식으로 계산을 한다.
     * 3. depth가 3이 되면 split으로 연산자가 모두 분리되고 남은 숫자가 String형식으로 남아있는데
     * 4. 이를 long타입으로 바꿔 return 해준다.
     * 5. 재귀로 인해 분리했던 반대의 순서대로 값이 계산되어 최종적으로 result를 구할 수 있다.
     * @param splits    우선순위에 따른 연산자 배열 변수
     * @param depth     계산 순서
     * @param formula   연산식이 들어있는 문자열 변수
     * @return          계산된 값 value를 return 한다.
     */
    public long calc(String[] splits, int depth, String formula){
        if(depth == 3){
            return Long.parseLong(formula);
        }
        String[] temp;
        long value;
        switch(splits[depth]){
            case "+":
                temp = formula.split("\\+");
                value = calc(splits, depth+1,temp[0]);      //연산의 제일 첫번째 값에
                for(int i = 1; i < temp.length ; i++){
                    value += calc(splits, depth+1,temp[i]); //그 뒤의 값을 연산자에 맞게 계산해준다.
                }
                return value;                                     //return으로 계산한 값을 반환한다.
            case "-":
                temp = formula.split("-");
                value = calc(splits, depth+1, temp[0]);
                for(int i = 1; i < temp.length ; i++){
                    value -= calc(splits, depth+1,temp[i]);
                }
                return value;
            case "*":
                temp = formula.split("\\*");
                value = calc(splits, depth+1, temp[0]);
                for(int i = 1; i < temp.length ; i++){
                    value *= calc(splits, depth+1,temp[i]);
                }
                return value;
        }
        return 0;
    }

    /**
     * 1. 연산자 우선순위 경우의 수가 총 6개로 적기때문에 직접 operator 변수에 입력해준다.
     * 2. calc로 우선순위에 따른 정답을 구하고
     * 3. 정답의 절대값이 answer값보다 클 경우 answer 변수에 값을 넣어준다.
     * @param expression 연산식
     * @return answer
     */
    public long solution(String expression) {
        long answer = 0;
        String[][] operator  = {{"+","-","*"},{"+","*","-"},
                                {"-","+","*"},{"-","*","+"},
                                {"*","+","-"},{"*","-","+"}};

        long result = 0;
        for(int i = 0 ; i < operator.length ; i++){
            result = calc(operator[i], 0, expression);
            answer = Math.max(answer, Math.abs(result));
        }

        return answer;
    }
}
