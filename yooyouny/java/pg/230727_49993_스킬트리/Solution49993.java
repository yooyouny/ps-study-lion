import java.util.*;
class Solution49993 {
    public int solution(String skill, String[] skill_trees) {
        return (int) Arrays.stream(skill_trees) // long to int
                .map(tree -> tree.replaceAll("[^" + skill + "]", ""))// skill에 없는(^) 문자를 제외한 tree 추출
                .filter(tree -> skill.startsWith(tree))// skill 문자열이 tree 문자열로 시작되는 케이스인 경우만 필터링
                .count(); // 필터링 된 케이스를 숫자로 셈
    }
}