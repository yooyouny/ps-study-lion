class Solution81302 {
	/*
	* replaceAll(alpha[i], Integer.toString(i))로 인덱스를 활용하는 방법이 있었음.. ^^
	* */
	public int solution(String s) {
		StringBuilder sb = new StringBuilder();// 복원할 문자열을 담을 stringbuilder 선언
		String[] alpha = {"zero","one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};// 문자열 매핑위해 배열로 선언
		while(true){
			if(s.length() == 0) break;// s의 길이를 점차 줄여가므로 s의 길이가 0인 경우 반복문을 빠져나옴
			char character = s.charAt(0);
			if(character >= 'a'){// 시작문자가 영어일경우
				for(int j=0; j<alpha.length; j++){// alpha 배열 순회를 위한 반복문
					if(s.startsWith(alpha[j])){// 특정 패턴으로 발견되면
						sb.append(j);// 숫자로 변환(인덱스 값)을 넣어주고
						s = s.substring(alpha[j].length());// startsWith를 계속 사용해야하기 때문에 매핑되는 문자열의 길이만큼 s를 줄임
					}
				}
			}else{// 시작문자가 숫자일경우
				sb.append(character);// 숫자는 그대로 넣어주고
				s = s.substring(1);// 해당 숫자 길이만큼 s를 줄여주기
			}
		}
		return Integer.parseInt(sb.toString());// sb에 넣은 숫자를 정수형으로 변환해서 리턴하기
	}
}
