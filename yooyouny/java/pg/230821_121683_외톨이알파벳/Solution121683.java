import java.util.*;

class Solution121683 {
    public String solution(String input_string) {
        Set<Character> visited = new HashSet<>();// 중복체크 set
        Set<Character> alone = new TreeSet<>();// 외톨이숫자 set
        char before = ' ';

        for (char c : input_string.toCharArray()) {
            if (before != c) {// 연속되지 않고
                if (visited.contains(c)) {// 이전에 한번 등장했던 문자면 외톨이숫자
                    alone.add(c);
                }
                visited.add(c);// 처음으로 등장한 알파벳은 set에 저장하여 방문처리
                before = c;// 연속문자 체크를 위해 이전문자 저장
            }
        }

        StringBuilder sb = new StringBuilder();// Set to String
        for (char c : alone) {
            sb.append(c);
        }

        return sb.length() == 0 ? "N" : sb.toString();
    }
}
