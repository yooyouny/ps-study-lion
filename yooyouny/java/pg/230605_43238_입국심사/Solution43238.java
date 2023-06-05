public class Solution {
	private boolean isValid(long t, int n, int[] times) {
		long c = 0;// 시간 내에 심사 가능한 사람 수
		for (int time : times) {
			c += t / time;
		}
		return c >= n;// 크거나 같으면 심시가 가능하므로 true반환
	}

	public long solution(int n, int[] times) {
		long start = 1;// 최소시간
		long end = 1000000000000000000L;// 최대 시간

		while (end > start) {
			long t = (start + end) / 2;

			if (isValid(t, n, times)) {// 현재 시간에서 심사가 가능한지 판별
				end = t;// 가능하면 현재시간을 최대시간으로 갱신
			} else {
				start = t + 1;// 불가능하면 현재시간을 최소시간보다 1증가
			}
		}

		return start;// 최소시간인 start가 n을 처리하는데 필요한 최소시간
	}
}
