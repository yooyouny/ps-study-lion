
import java.util.Arrays;

class Solution {
 public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);

        //TODO: 뒤에서 확인해야 시간복잡도가 최소가된다.
        for (int iStart = numbers.length - 2; iStart >= 0; iStart--) {
            for (int i = iStart + 1; i < numbers.length; i++) {
                //TODO: 뒤에서 큰수를 발견한 경우
                if(numbers[iStart] < numbers[i]){
                    answer[iStart] = numbers[i];
                    break;
                //TODO: 뒤에있는 i번쨰 인덱스의 값이 큰수가 아닌 경우    
                }else {
                    //만약 i번째 인덱스의 뒤에 큰수가 없다면, iStart가 i보다 큰 수니까 iStart보다 큰수도 없다는 이야기
                    if(answer[i] == -1){
                        answer[iStart] = -1;
                        break;
                    //만약, i번쨰 인덱스의 수가 iStart번째의 인덱스의 값보다 크다면, numbers[iStart] >= numbers[i]인데
                    //numbers[i]의 뒤에 있는 큰수가 numbers[iStart]보다 크다는 이야기니까 numbers[iStart]의 뒤에 있는 큰수도
                        // 그 수를 뒤에 있는 큰수로 가짐    
                    }else if(numbers[iStart] < answer[i]) {
                        answer[iStart] = answer[i];
                        break;
                    }
                }
            }
        }
        return answer;
    }
}
