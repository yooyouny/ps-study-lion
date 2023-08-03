import java.util.Stack;
import java.util.stream.Collectors;

public class Solution42883_2 {
    /*
    * 문자의 순서는 고정되어있음 -> stack활용
    * 기존 숫자보다 더 큰 숫자가 등장하면 pop으로 계속 제거
    * */
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();
        for (char c : number.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && c > stack.peek()) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        // 역순으로 정렬되어있는 경우 k가 한번도 줄어들지 않았으므로 끝에서부터 제거
        while(k > 0){
            stack.pop();
            k--;
        }

        return stack.stream()
                .map(String::valueOf)// Character를 String형태로 바꿔주고
                .collect(Collectors.joining());// 각 문자열을 하나로 합쳐서 반환
    }
}