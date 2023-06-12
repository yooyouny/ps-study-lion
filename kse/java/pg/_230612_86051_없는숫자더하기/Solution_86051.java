package java.pg._230612_86051_없는숫자더하기;

//신규_프로그래머스_lv1_없는숫자더하기
public class Solution_86051 {
    public int solution(int[] numbers) {
        int answer = 45;

        for(int n : numbers) answer -= n;

        return answer;
    }
}
