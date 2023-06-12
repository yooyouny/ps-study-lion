import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
	public boolean solution(String[] phone_book) {
	
        
		boolean answer = true;
		//O(1)로 확인하기 위해 HashMap사용
        Map<String, Integer> bookdic = new HashMap<>();
		for (int i = 0; i < phone_book.length; i++) {
			bookdic.put(phone_book[i], i);
		}
		for (int i = 0; i < phone_book.length; i++) {
			for (int j = 0; j < phone_book[i].length(); j++) {
                //Hashing하면 containsKey에서 O(1)로 subString을 포함한 Key가 있는지 없는지 찾을수 있다.
				if(bookdic.containsKey(phone_book[i].substring(0, j))){
					return false;
				}
			}	
		}
		return answer;
	}
}
