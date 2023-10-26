package java.pg._231026_155651_호텔대실;

import java.util.*;

public class Solution_155651_1 {
    public int solution(String[][] book_time) {
        int answer = 0;
        int length = book_time.length;

        // 시간을 분으로 변경
        int[][] times = new int[length][2];
        for (int i = 0; i < length; i++) {
            times[i][0] = changeToMinutes(book_time[i][0]);
            times[i][1] = changeToMinutes(book_time[i][1]) + 10;
        }
        // 시작 시간을 기준으로 정렬
        Arrays.sort(times, Comparator.comparingInt(a -> a[0]));

        // 현재 시간 변수
        int nowTime = 0;
        // 현재 시간 기준 사용하고 있는 회의실의 목록 : 끝나는 시간을 기준으로 오름차순 정렬
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        for (int i = 0; i < length; i++) {
            nowTime = times[i][0];  //현재 시간을 회의 시작시간으로 변경
            queue.add(times[i]);    //현재 회의의 시간을 queue 에 넣어준다.

            // queue 에 들어있는 회의 중 끝나는 시간이 현재 회의 시작시간과 같거나 작다면 제거
            while (!queue.isEmpty() && queue.peek()[1] <= nowTime) queue.poll();
            // answer 는 queue 의 최대 크기가 된다.
            answer = Math.max(answer, queue.size());
        }
        return answer;
    }

    //시간을 분으로 변경해주는 메서드
    public int changeToMinutes(String time) {
        int[] intTime = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
        return intTime[0] * 60 + intTime[1];
    }
}
