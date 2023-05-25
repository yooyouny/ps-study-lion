import java.util.*;
class Solution {
    //조합 문자열과, 등장 횟수를 저장할 map
	static HashMap<String, Integer> map;
    //list에 담아 정렬
	static ArrayList<String> list = new ArrayList<>();

    //조합 구하기 dfs
	public static void dfs(String order, StringBuilder sb, int idx, int cnt, int n){
		if(cnt == n){
            //조합에 해당하는 문자열 key, 없으면 1씩 증가
			map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
			return ;
		}

		for (int i = idx; i < order.length(); i++) {
			sb.append(order.charAt(i));
			dfs(order, sb, i + 1, cnt + 1, n);
			sb.delete(cnt, cnt + 1);
		}
	}
	public static String[] solution(String[] orders, int[] course) {
		String[] answer;
		//정렬해서 조합찾기
        sortList(orders);
		for (int i = 0; i < course.length; i++) {
			map = new HashMap<>();
			int max = Integer.MIN_VALUE;
			for (int j = 0; j < orders.length; j++) {
				StringBuilder sb = new StringBuilder();
				//course보다 길이가 길어야만 조합을 만들 수 있음
                if(course[i] <= orders[j].length()){
                    //dfs로 조합 만들기
					dfs(orders[j], sb, 0, 0, course[i]);
				}
			}
            //가장 많이 등장한 메뉴확인
			for(Map.Entry<String,Integer> entry : map.entrySet()){
				max = Math.max(max,entry.getValue());
			}
			for(Map.Entry<String,Integer> entry : map.entrySet()){
				//2개이상이고 max와 같으면 list에 저장
                if(max >=2 && entry.getValue() == max)
					list.add(entry.getKey());
			}
		}
		answer = new String[list.size()];
        //정렬후 저장
		Collections.sort(list);
		int i = 0;
		for(String menu: list){
			answer[i] = menu;
			i++;
		}
		return answer;
	}

	private static void sortList(String[] orders) {
		for (int i = 0; i < orders.length; i++) {
			char[] charArr = orders[i].toCharArray();
			Arrays.sort(charArr);
			orders[i] = String.valueOf(charArr);
		}
	}
}
