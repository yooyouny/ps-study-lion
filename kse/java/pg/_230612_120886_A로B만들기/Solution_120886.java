package java.pg._230612_120886_A로B만들기;

import java.util.Arrays;

//신규_프로그래머스_lv0_A로B만들기
public class Solution_120886 {
    //문자열을 뒤집는게 아니라 문자 위치를 바꿔서 B를 만드는 문제
    public int solution(String before, String after) {
        int answer = 0;
        String[] bef = before.split("");
        String[] aft = after.split("");
        Arrays.sort(bef);
        Arrays.sort(aft);
        return Arrays.equals(bef, aft) ? 1 : 0;
    }
}
