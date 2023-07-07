public class Solution {

	Map<Long, Long> map = new HashMap<>();

	public long[] solution(long k, long[] room_number){
		int n = room_number.length;
		long[] answer = new long[n];

		for (int i = 0; i < n; i++) {
			//원하는 방중에 비어있는 크거나 같은 방을 찾는다.
			answer[i] = findEmptyRoom(room_number[i]);
		}
		return answer;
	}

	private long findEmptyRoom(long room){
		if(!map.containsKey(room)){
			//방이 비어있으면 Key로 채워주고, 다음 방을 Value로 넣어준다.
			map.put(room, room + 1);
			//채운 방을 반환해서 answer에 저장
			return room;
		}
		//이미 찬 방은 Value로 넣어진 다음 방을 가져온다.
		long nextRoom = map.get(room);
		//다음 방 중에 빈 방 찾기
		long emptyRoom = findEmptyRoom(nextRoom);
		//빈방을 찾으면 지금까지 다음 방으로 거쳐온 모든 방들의 다음 방을 찾은 빈방으로 바꿔준다.
		map.put(room, emptyRoom);
		//마지막으로 찾은 빈방을 answer에 반환해서 저장
		return emptyRoom;
	}
}
