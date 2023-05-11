import java.util.*;
class Solution12946 {
	List<int[]> list;// 목적지까지 몇 번 옮겨야 할 지 모르므로 list 생성
	public void hanoi(int n, int from, int to, int prev){// from에서 to로 prev는 to로 가기 이전의 중간지점
		if(n == 1){// 기저조건. 원반이 하나면 한번만 목적지로 옮기면 됨
			list.add(new int[]{from, to});
		}else{
			hanoi(n-1, from, prev, to);// 시작지점인 from에 있는 n-1개의 원판을 중간지점 prev로 이동해 놓고
			list.add(new int[]{from, to});// 시작지점인 from에 있는 가장 큰 원반을 목적지 to로 이동
			hanoi(n-1, prev, to, from);// 중간지점인 prev에 있는 n-1개의 원판을 목적지 to로 이동
		}
	}
	public int[][] solution(int n) {
		list = new ArrayList<>();
		hanoi(n, 1, 3, 2);// 1기둥 부터 3기둥 까지 2기둥을 거쳐서
		int[][] answer = new int[list.size()][2];// list에 담긴 전체 이동경로를 int[] 배열에 담아 리턴
		return list.toArray(answer);
	}
}
