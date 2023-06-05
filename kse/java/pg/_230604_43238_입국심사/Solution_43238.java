package java.pg._230604_43238_입국심사;
//신규_프로그래머스_lv3_43238_입국심사
//https://school.programmers.co.kr/learn/courses/30/lessons/43238?language=java#
public class Solution_43238 {
    /**
     * 1. 가장 빨리 끝나는 경우와 가장 늦게 끝나는 경우를 각각 start, end로 저장해준다.
     * 2. 중간 시간대를 구해서 그 안에 모든 입국자가 심사받을 수 있는 경우 end = mid-1로 바꿔주고 answer를 mid값으로 바꿔준다.
     * 3. 모든 입국자가 심사받을 수 없는 경우 start = mid+1로 만들어준다.
     *
     */
    public long solution(int n, int[] times) {
        long answer = 0;

        long start = times[0];
        //********* long으로 자료형을 바꿔주지 않으면 end값이 변형되면서 실패한다.
        long end   = (long)times[times.length-1]*(long)n;
        long mid;
        long sum;
        while(start<=end) {
            mid = (start+end)/2;
            sum = 0;
            // 시간/걸리는 시간 == 그 시간 내에 심사원이 심사할 수 있는 사람의 수
            // sum == 모든 심사원이 심사할 수 있는 사람의 수
            for(int time : times){
                sum += mid/time;
                if(sum >= n) break;
            }
            if(sum>=n) {    //시간 내에 모든 입국자가 심사받을 수 있는 경우
                answer = mid;
                end = mid-1;
            }else {         //시간 내에 모든 입국자가 심사받을 수 없는 경우
                start = mid+1;
            }
        }
        return answer;
    }
}
