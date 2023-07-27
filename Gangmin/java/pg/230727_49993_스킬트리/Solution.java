class Solution {
 	boolean visited[];

	public int solution(String skill, String[] skill_trees) {
		int answer = 0;

		for (String skillTree : skill_trees) {
			visited = new boolean[skill.length()];
			if(isValidOrder(skill, skillTree)){

				answer++;
			}
		}
		return answer;
	}



	private boolean isValidOrder(String skill, String candidate){
		int prevOrder = -1;
		for (int i = 0; i < candidate.length(); i++) {
			//몇번째 위치 한 문자인
			int currentOrder = skill.indexOf(candidate.charAt(i));
			if(currentOrder == -1) continue;
			if(currentOrder > prevOrder) {
				prevOrder = currentOrder;
				visited[currentOrder] = true;
				if(currentOrder == 0) continue;
				//앞쪽 숫자가 방문이 안되어있으면 false
				if(!visited[currentOrder - 1]) return false;
			}else {
				return false;
			}
		}
		return true;
	}
}
