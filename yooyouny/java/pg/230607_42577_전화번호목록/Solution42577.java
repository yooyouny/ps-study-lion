import java.util.*;
class Solution42577 {
	/*
	* startsWith 비교문자열 길이에 비례해 비교발생
	* 모든 접두사 조합 저장 후 contains로 O(1)만에 확인
	* hashSet으로 중복제거
	* */
	public boolean solution(String[] phone_book) {
		Set<String> prefix = new HashSet<>();// 중복제거를 위해 hashSet 사용
		for(String phoneNumber : phone_book){// 모든 접두사 조합 생성하여 저장
			for(int i=1; i<phoneNumber.length(); i++){
				prefix.add(phoneNumber.substring(0, i));// phoneNumber 길이의 -1까지 생성
			}
		}
		for(String phoneNumber : phone_book){
			if(prefix.contains(phoneNumber))// 접두사 조합에 해당 문자열이 있으면 false 반환
				return false;
		}
		return true;
	}
}
