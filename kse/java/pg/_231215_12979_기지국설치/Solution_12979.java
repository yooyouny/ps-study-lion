package java.pg._231215_12979_기지국설치;

//https://school.programmers.co.kr/learn/courses/30/lessons/12979
//신규_프로그래머스_lv3_12979_기지국설치
public class Solution_12979 {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int LEN    = stations.length;

        // 기존의 기지국의 개수가 많아 계산할 필요가 없는 경우 제외
        if(LEN + 2 * w > n) return 0;

        int point = 1;          // 시작 지점
        int width = 1 + 2 * w;  // 기지국이 전파를 보낼 수 있는 범위
        for (int i = 0; i < LEN; i++) {
            int start = stations[i] - w;    // 해당 기지국의 전파 범위를 계산
            int end   = stations[i] + w;

            // 이전 기지국의 전파끝보다 start 가 더 큰 경우 사이에 전파가 통하지 않는 부분이 있다고 판단.
            if(point < start){
                answer += Math.ceil((double) (start - point) / width);
            }
            // 시작 지점을 갱신
            point = end + 1;
        }
        // 마지막 기지국의 범위가 전체 길이보다 작은 경우 마지막 빈 공간에 필요한 기지국 개수를 구해준다.
        if(point <= n) answer += Math.ceil((double) (n - point + 1) / width);

        return answer;
    }
}
