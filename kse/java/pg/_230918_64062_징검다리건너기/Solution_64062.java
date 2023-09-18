package java.pg._230918_64062_징검다리건너기;


//https://school.programmers.co.kr/learn/courses/30/lessons/64062
//신규_프로그래머스_lv3_64062_징검다리건너기
public class Solution_64062 {
    /**
     *
     * @param stones    디딤돌마다 밟을 수 있는 횟수
     * @param k         뛰어서 건너뛸 수 있는 디딤돌의 수
     */
    public int solution(int[] stones, int k) {
        int min = 0;
        int max = 200_000_000;
        int result = 0;
        //이분탐색으로 가능한 인원 수의 범위를 좁혀가며 계산한다.
        while(min <= max) {
            int mid = (min + max) / 2;
            if(checkCross(mid, k, stones)) {
                min = mid + 1;
                result = mid;
            } else {
                max = mid - 1;
            }
        }
        return result;
    }

    public boolean checkCross(int nowCount, int k, int[] stones) {
        int count = 0;
        for (int stone : stones) {
            if (stone < nowCount) {            // nowCount 보다 적으면 건널 수 없다.
                count++;
                if (count >= k) return false;  // 건널 수 없는 사람이 연속 k명 이면 false
            } else count = 0;
        }
        return true;
    }
}
