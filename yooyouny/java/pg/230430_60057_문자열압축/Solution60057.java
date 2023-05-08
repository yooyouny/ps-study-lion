class Solution60057 {
	public int solution(String s) {
		StringBuilder sb = new StringBuilder();// 문자열 변경이 잦으므로 StringBuilder 생성
		int answer = s.length();// 하나도 압축하지 못했을 경우 원본 길이로 리턴해야하니까 answer에 s의 길이값을 넣어줌

		/*
		* 문자열 패턴을 생성하고
		* 그 패턴에 맞으면 StringBuilder에 일치하는 횟수+패턴을 append해주는 방식
		* 패턴에 맞지않으면 이전 패턴의 인덱스 다음부터 시작하는 패턴을 다시 생성하고 다시 일치하는지 비교
		* */
		for(int i=1; i<=s.length()/2; i++){// i는 패턴의 길이, 압축 가능한 패턴의 길이는 1부터 시작, 최대로 압축할 수 있는 반복 문자열의 길이는 전체 길이의 절반
			String pattern = s.substring(0, i);// 0부터 i까지 패턴 생성
			int cnt = 1; // 압축 횟수를 증가시킬 cnt 변수 생성
			sb.setLength(0);// 패턴의 길이를 늘려 새롭게 만들었을 경우 이전 압축 문자열이 들어있는 sb 갱신

			for(int j=i; j<s.length(); j+=i){// 비교 문자열은 패턴의 마지막 인덱스 부터 시작해야하고 패턴의 길이를 의미하는 i만큼 계속해서 j를 증가시켜야함
				if(pattern.equals(s.substring(j, Math.min(j+i, s.length())))){// i+j가 전체 문자열 길이를 벗어나지 않도록함
					cnt++; //패턴의 길이와 비교할 문자열이 같으면 압축 횟수 증가
				}else{// 패턴의 길이와 비교할 문자열이 같지 않으면
					if(cnt > 1) // 이전에 일치하는 패턴이 있었을 경우
						sb.append(cnt); // sb에 압축횟수와
					sb.append(pattern); // 패턴을 넣어줌
					pattern = s.substring(j, Math.min(j+i, s.length())); // 패턴의 문자열 인덱스 다음부터 패턴의 길이만큼 잘라낸 새로운 패턴을 생성함
					cnt = 1; // 새로운 패턴의 압축횟수를 셀 수 있도록 cnt값을 갱신해줌
				}
			}
			if(cnt > 1) //s 문자열의 끝까지 패턴이 반복된 경우는 위의 포문에서 append해주지 않았으므로 압축 가능한 문자열이면
				sb.append(cnt); // 횟수를 넣어주고
			sb.append(pattern); // 마지막 남은 문자열을 sb에 넣어줌
			answer = Math.min(answer, sb.length()); // 최소로 압축했을 때의 문자열 길이를 리턴해야하므로 sb의 길이를 계속해서 갱신
		}
		return answer;
	}
}
