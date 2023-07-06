package java.pg._230704_64063_호텔방배정;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/64063
//신규_프로그래머스_lv4_호텔방배정
public class Solution_64063 {
    /** 빈 방을 찾는 메서드
     *  1. usingRoom 에 해당 방이 key 값으로 존재하지 않는 경우
     *     아직 배정되지 않은 방이라는 의미 이기 때문에 put(number, number+1)
     *  2. usingRoom 에 해당 방이 key 값으로 존재하는 경우
     *     이미 배정된 방이라는 의미이기 때문에
     *     usingRoom 에 들어 있는 다음 방 번호 usingRoom.get(number)를 가져와서 재귀를 돌려준다.
     */
    public long findEmptyRoom(Map<Long,Long> usingRoom, long number){
        if(!usingRoom.containsKey(number)) {
            usingRoom.put(number, number+1);
            return number;
        }
        long next = findEmptyRoom(usingRoom, usingRoom.get(number));
        usingRoom.put(number, next+1);
        return  next;

    }
    public long[] solution(long k, long[] room_number) {
        //배정된 방을 순서대로 저장해줄 리스트
        List<Long> answer = new ArrayList<>();
        //사용된 방과 다음 방을 알려줄 맵
        Map<Long,Long> usingRoom = new HashMap<>();


        for(long number : room_number){
            //room 배정된 방
            long room = findEmptyRoom(usingRoom, number);

            answer.add(room);
        }
        return answer.stream().mapToLong(Long::valueOf).toArray();
    }
}
