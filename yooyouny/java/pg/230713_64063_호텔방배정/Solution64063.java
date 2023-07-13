import java.util.*;
class Solution64063 {
	static Map<Long, Long> rooms;// 배정을 완료한 방번호와 그 다음에 있는 빈 방의 번호를 담고있는 방번호 목록으로, 배정이 완료된 방 번호 목록
	static List<Long> visitedRooms;// 이미 투숙객이 있는 방 번호 목록

	public long[] solution(long k, long[] room_number) {
		List<Long> answer = new ArrayList<>();
		rooms = new HashMap<>();
		visitedRooms = new ArrayList<>();

		for(int i=0; i<room_number.length; i++){
			long num = room_number[i];// 고객이 원하는 방 번호
			while(rooms.containsKey(num)){// 이미 해당 방이 차 있으면
				num = rooms.get(num);// 다음 번호로 계속해서 방이 비었는지 탐색
				visitedRooms.add(num);// 이미 배정되어있는 방 번호들의 다음 번호를 추후 진짜로 배정할 번호의 다음번호로 갱신해주기 위해 따로 저장
			}
			rooms.put(num, num + 1);// 배정할 방 번호와 그 다음 빈방을 저장
			answer.add(num);// 배정한 방 번호를 결과 배열에 담아 배정 확정

			if(!visitedRooms.isEmpty())// 이미 배정되었던 방인데 원하는 번호로 들어올 경우 다음 빈방의 위치를 제대로 알려주기 위해 따로 저장한 리스트가 있는지 확인
				resetNextRoomNumtoVisitedRoomNum(num + 1);// 배정을 완료한 방 번호의 다음번호로 빈 방 갱신
			visitedRooms.clear();// 갱신작업이 끝나면 다시 방문리스트를 리셋
		}
		return answer.stream().mapToLong(i -> i).toArray();
	}
	private static void resetNextRoomNumtoVisitedRoomNum(long resetNum){
		for(int i = 0; i<visitedRooms.size(); i++){// 빈 방의 번호가 잘못된 리스트들의 번호를 리셋
			Long visitedRoomNum = visitedRooms.get(i);
			rooms.put(visitedRoomNum, resetNum);
		}
	}
}
