import java.util.*;
class Solution155651 {
    public int solution(String[][] book_time) {
        if(book_time.length == 1) return 1;// 예약 시간이 1개면 방 갯수를 1개로 리턴

        // 현재 방을 사용하고 있는 사람의 종료시간 저장 & 가장 빨리 나오는 사람의 종료시간을 확인하기 위해 pq 사용
        Queue<Integer> queue = new PriorityQueue<>();

        // 시작 시간이 가장 빠른 순서대로 예약시간 배열을 정렬
        Arrays.sort(book_time, (time1, time2) ->
                Integer.compare(parseMinute(time1[0]), parseMinute(time2[0])));

        for(String[] time : book_time){
            int startTime = parseMinute(time[0]);
            int endTime = parseMinute(time[1]) + 10;// 끝난 시간의 10분 후에 입장이 가능

            if(queue.isEmpty()){// 초기 상태
                queue.add(endTime);// 방 점유
            }else{// 누군가 방을 쓰고 있으면 해당 종료시간과 현재 들어온 예약시간의 시작시간을 비교해야함
                int peekEndTime = queue.peek();// 현재 방을 쓰고 있는 사람들 중 가장 빠른 종료시간
                if(peekEndTime > startTime){// 기존에 쓰는 사람이 아직 방을 써야한다면
                    queue.add(endTime);// 지금 들어온 사람은 새로운 방을 써야함
                }else{// 지금 방을 쓰고있는 사람의 종료시간보다 늦게 들어왔으면
                    queue.poll();// 체크아웃 처리 해주고
                    queue.add(endTime);// 해당 방에 들어감
                }
            }
        }
        return queue.size();// 현재 방을 점유하고 있는 사람들의 수 == 큐의 사이즈
    }
    private int parseMinute(String time){// 문자열 시간을 minute로 변환
        String[] parse = time.split(":");
        return Integer.parseInt(parse[0]) * 60 + Integer.parseInt(parse[1]);
    }
}