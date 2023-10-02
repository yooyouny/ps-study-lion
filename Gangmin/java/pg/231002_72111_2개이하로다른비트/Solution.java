
import java.util.*;
class Solution {
     public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            //뒷자리가 11인지 확인
            if(numbers[i] % 4 == 3){
                answer[i] = change(numbers[i]);
            }else {
                answer[i] = numbers[i] + 1;
            }
        }
        return answer;
    }

    private long change(long number){
        String binaryString = Long.toBinaryString(number);
        if(!binaryString.contains("0")){
            binaryString = "0" + binaryString;
            binaryString = binaryString.replaceFirst("01", "10");
            return Long.parseLong(binaryString, 2);
        }else {
            int point = binaryString.lastIndexOf("01");
            StringBuilder sb = new StringBuilder();
            sb.append(binaryString.substring(0, point));
            sb.append("10");
            sb.append(binaryString.substring(point + 2));
            return Long.parseLong(sb.toString(), 2);
        }
    }
}

