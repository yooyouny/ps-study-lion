import java.util.*;

public class Solution {
        static List<String> list;
    public static String[] solution(String[] strings, int n) {
        //String sort를 위해 List<String> 생성   
        list = Arrays.asList(strings);
        //Compartor구현
           list.sort((o1, o2) -> {
            char c1 = o1.charAt(n);
            char c2 = o2.charAt(n);
            if(c1 == c2){
                //같으면 문자열 전체 비교
                return o1.compareTo(o2);
            }
            else{
                //같지 않으면 n번째 문자 비교
                return Character.compare(c1, c2);
            }
           }); 
        //배열로 반환
           return list.stream().toArray(String[]::new);
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"abce", "abcd", "cdx"}, 2)));
    }
}
