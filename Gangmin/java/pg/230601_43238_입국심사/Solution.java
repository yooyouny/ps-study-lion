import java.util.Arrays;

/**
 * yes or no 문제로 바꾸기
 * 임의의 시간안에 n명을 처리할 수 있는가?
 * yes? or no?
 * 최대 시간과 최소 시간 사이 비교
 * 이분 탐색으로 오른쪽에서 가장 왼쪽 값을 찾자
 */


class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        //이분 탐색을 위한 정렬
        Arrays.sort(times);
        //가장 적은 시간
        long left = 0;
        //최악의 시간 - 모든 사람이 가장 오래걸리는 스태프에게 받을 경우
        long right = times[times.length-1] * (long)n; //모든 사람이 가장 느리게 심사받음
        //n명을 처리하는 최소의 시간을 구하는 이분 탐색
        while(left <= right) {
            //mid 시간안에 n명을 처리할 수 있는가
            long mid = (left + right) / 2;
            //mid시간안에 각 스태프가 처리할 수 있는 사람의 양
            long complete = 0;
            for (int i = 0; i < times.length; i++)
                complete += mid / times[i];
                //n보다 작으면 fail - 시간 부족
            if (complete < n) // 해당 시간에는 모든 사람이 검사받을 수 없다.
                left = mid + 1;
                //n보다 크면 처리 할수 있음
            else {
                right = mid - 1;
                answer = mid; // 모두 검사받았으나, 더 최솟값이 있을 수 있다.
            }
        }  
        return answer;
    }
}
