import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
class Solution {
   public static int solution(String s) {
       
        int answer = 0;
       //문자형 숫자의 끝을 표시하는 end
        int end = 0;
       //StringBuilder로 변환한 숫자 저장
        StringBuilder sb = new StringBuilder();
       //문자형 숫자를 key로, 숫자를 value로 하는 hashmap 
       Map map = new HashMap();
       //map만드는 함수
        makeMap(map);
        for(int i = 0; i < s.length(); i++){
            //숫자면 그냥 대입
            if(Character.isDigit(s.charAt(i))){
                sb.append(s.charAt(i));
            }else{
                //i는 문자의 시작이니까 가장 짧은 길이 3부터 비교 시작
                end = i + 3;
                //key로 찾고 맞으면 멈춤
                while(!map.containsKey(s.substring(i, end)))
                    end++;
                //key에 대한 value를 더해준다.
                sb.append(map.get(s.substring(i, end)));
                //i는 문자열을 비교한 뒤의 end값 이전으로 만들고 for문돌떄 1증가
                i = end - 1;
            }
        }
       //완성된 문자열 int변환
        answer = Integer.parseInt(sb.toString());
        return answer;
    }

    public static void makeMap(Map map){
        map.put("zero", "0");
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        map.put("nine", "9");
    }
}
