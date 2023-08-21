import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    
    Map<String, Integer> frequency = new HashMap<>();
    List<String> loner = new ArrayList<>();
    
    public String solution(String input_string) {
        String answer = "";
        //
        splitString(input_string);
        for (Map.Entry<String, Integer> stringIntegerEntry : frequency.entrySet()) {
            if(stringIntegerEntry.getValue() >= 2){
                loner.add(stringIntegerEntry.getKey());
            }
        }
        if(loner.isEmpty())
            return "N";
        loner.sort(String::compareTo);
        return String.join("", loner);
    }

    //중복되지 않는 글자가 나올때마다 기존 글자 key, value 형태로 저장
    public void splitString(String input){
        int standard = 0;
        for (int i = 0; i < input.length() - 1; i++) {
            if(input.charAt(i) != input.charAt(i + 1)){
                frequency.put(input.substring(i, i + 1), frequency.getOrDefault(input.substring(i, i + 1), 0) + 1);
                //마지막 문자를 저장하기 위해 standard에 기록
                standard = i + 1;
            }
        }
        //마지막 남은 문자 기록
        frequency.put(input.substring(standard, standard + 1), frequency.getOrDefault(input.substring(standard, standard + 1), 0) + 1);
    }
}
