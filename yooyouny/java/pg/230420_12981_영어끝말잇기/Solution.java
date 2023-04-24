public class Solution12981{
	public int[] solution(int n, String[] words) {
		int[] answer = {0, 0};

		Set<String> followWords = new HashSet<>();
		followWords.add(words[0]);

		for(int i=1; i<words.length; i++){
			if(words[i].length() <= 1 || followWords.contains(words[i]) || words[i].charAt(0) != words[i-1].charAt(words[i-1].length()-1)){
				answer[0] = i % n + 1;
				answer[1] = i / n + 1;
				break;
			}
			followWords.add(words[i]);
		}

		return answer;
	}
}
