class Solution72410 {
	public String solution(String new_id) {

		new_id = new_id.toLowerCase();//1단계
		new_id = new_id.replaceAll("[^0-9a-z-_.]", "");//2단계

		//3단계
		StringBuilder sb = new StringBuilder();// 2개이상의 .을 .하나로 치환될 문자열을 담기 위한 StringBuilder
		boolean isPrevDot = false;// 이전 문자가 마침표일 경우

		for(int i=0; i<new_id.length(); i++){// 아이디 문자열 인덱스 접근을 위한 for문
			char c = new_id.charAt(i);
			if(c == '.'){// 현재 문자가 마침표면서 이전 문자열도 마침표인 경우 append를 하지 않음
				if(!isPrevDot)// 그러나 이전 문자열이 마침표가 아니면 마침표 한개는 넣어줘야함
					sb.append(c);
				isPrevDot = true;// 현재 문자가 마침표이므로 이전 문자 체크를 true로 갱신
			}else{// 마침표가 아니면 치환대상이 아니므로 그냥 append
				sb.append(c);
				isPrevDot = false;// 현재 문자가 마침표가 아니니까 이전 문자 체크를 false로 갱신
			}
		}
		new_id = sb.toString();

		if(new_id.startsWith("."))//4단계
			new_id = new_id.substring(1);
		if(new_id.endsWith("."))
			new_id = new_id.substring(0, new_id.length()-1);

		if(new_id.length() == 0)//5단계
			new_id += "a";

		if(new_id.length() >= 16){//6단계
			new_id = new_id.substring(0, 15);
			if(new_id.endsWith("."))
				new_id = new_id.substring(0, new_id.length()-1);
		}
		if(new_id.length() <= 2){//7단계
			char lastChar = new_id.charAt(new_id.length()-1);
			while(new_id.length() < 3){
				new_id += lastChar;
			}
		}
		return new_id;
	}
}
