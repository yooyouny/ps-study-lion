import java.util.Arrays;

class Solution42748_2 {
	public int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];

		for (int i = 0; i < commands.length; i++) {
			int start = commands[i][0] - 1;
			int end = commands[i][1];
			int k = commands[i][2];

			int[] temp = Arrays.copyOfRange(array, start, end);
			Arrays.sort(temp);
			answer[i] = temp[k - 1];
		}

		return answer;
	}
}
