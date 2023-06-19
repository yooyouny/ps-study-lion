import java.util.*;

public class Solution2 {
	static Queue<Integer> queue = new LinkedList<>();

	public static int[] solution(int[] progresses, int[] speeds) {
		return proc(progresses, speeds)
			.stream()
			.mapToInt(Integer::intValue)
			.toArray();
	}

	static List<Integer> proc(int[] progresses, int[] speeds) {
		/*갯수를 셀 count*/
		int count = 0;
		/*배포되는 기능의 갯수를 저장할 list*/
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < progresses.length; i++) {
			/*작업완료에 걸리는 시간구하기*/
			int days = getDays(progresses[i], speeds[i]);
			/*앞에 배치된 작업중 완성되지 못한 작업의 소요시간과 비교, 만약 작다면 그 작업의 완성을 기다려야 하기 떄문에,
			* queue에 push*/
			if (queue.isEmpty() || queue.peek() >= days) {
				queue.offer(days);
			} else {
				/*만약 작업이 queue의 맨앞 작업보다 시간이 더걸리면,
				* 앞서 작업들을 다 완성될수 있기 때문에, queue에서 모두 내보냄
				**/
				while (!queue.isEmpty()) {
					queue.poll();
					count++;
				}
				/*이때 완성되는 작업의 갯수를 list에 저장*/
				list.add(count);
				queue.offer(days);
				count = 0;
			}
		}
		/*위와 같음 - 끝난 경우 큐에 남아있는 작업들 처리*/
		while (!queue.isEmpty()) {
			queue.poll();
			count++;
		}

		if (count != 0)
			list.add(count);

		return list;
	}

	private static int getDays(int progress, int speed) {
		/*작업 시간 대비 남은 작업분량을 게산해 몇일 소요되ㄴ는지 계산*/
		int days = (100 - progress) / speed;
		if ((100 - progress) % speed != 0)
			days++;
		return days;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
	}
}

