package java.pg._231030_148652_유사칸토어비트열;
//https://school.programmers.co.kr/learn/courses/30/lessons/148652
//신규_프로그래머스_lv2_148652_유사칸토어비트열
public class Solution_148652 {
    public int solution(int n, long l, long r) {
        int answer = 0;

        // l ~ r 까지의 범위의 숫자를 모두 검사
        for (long i = l-1; i < r; i++) {
            if (check(i)) answer++;
        }

        return answer;
    }

    boolean check(long num) {
        // [1,1,0,1,1] 범위에 있으면서 0에 해당하지 않는 숫자 true
        if (num < 5  && num != 2) return true;
        // n번째 칸토어 비트열에서 0에 해당하는 범위내에 있는 경우 false
        if (num % 5 == 2) return false;

        // n - 1 비트열로 내려서 검사
        return check(num / 5);
    }
}
