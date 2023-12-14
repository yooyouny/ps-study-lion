import java.util.*;
class Solution17677 {
    public int solution(String str1, String str2) {
        List<String> str1List = new ArrayList<>();
        List<String> str2List = new ArrayList<>();

        str1List = parseStr(str1);
        str2List = parseStr(str2);

        int intersection = 0;
        int union = 0;

        for(String str : str1List){
            if(str2List.contains(str)){
                str2List.remove(str);
                intersection++;
            }
            union++;
        }

        for(String str : str2List){
            union++;
        }

        if(union == 0) return 65536;

        return (int)((double)intersection / union * 65536);
    }
    private List<String> parseStr(String input){
        List<String> result = new ArrayList<>();
        for(int i=0; i<input.length() - 1; i++){
            String str = input.substring(i, i + 2);
            if(str.matches("^[a-zA-Z]*$"))// 문자열 시작^에서 끝$까지 특정문자가 0번이상 반복되는경우 = 모든 문자들만
                result.add(str.toUpperCase());
        }
        return result;
    }
}