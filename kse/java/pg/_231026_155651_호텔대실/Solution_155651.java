package java.pg._231026_155651_호텔대실;

import java.util.Arrays;

//https://school.programmers.co.kr/learn/courses/30/lessons/155651
//신규_프로그래머스_lv2_155651_호텔대실
public class Solution_155651 {
    public int solution(String[][] book_time) {
        int LEN = book_time.length;

        //시간을 분으로 변경
        int[] startTimes = new int[LEN];
        int[] endTimes   = new int[LEN];
        for (int i = 0; i < LEN; i++) {
            startTimes[i] = changeToMinutes(book_time[i][0]);
            endTimes[i]   = changeToMinutes(book_time[i][1]) + 10;
        }
        // 청소시간에 해당하는 date index 의 배열에 값 + 1
        int[] date = new int[1455];
        for (int i = 0; i < LEN; i++) {
            for (int j = startTimes[i]; j < endTimes[i]; j++) {
                date[j]++;
            }
        }
        // date 배열의 최대값이 필요한 방의 개수
        return Arrays.stream(date).max().getAsInt();
    }

    //시간을 분으로 변경해주는 메서드
    public int changeToMinutes(String time) {
        int[] intTime = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
        return intTime[0] * 60 + intTime[1];
    }
}
