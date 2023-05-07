class Solution12926{
	public String solution(String s, int n) {
		StringBuilder sb = new StringBuilder();// 문자열 연산이 잦으므로 stringbuilder 생성
		for(char c : s.toCharArray()){// 문자 하나씩 접근해야해서 char배열로 변환
			if (c == ' '){// input으로 공백, 대문자, 소문자가 들어옴
				sb.append(' ');
				continue;// 공백은 변환없이 넘어감
			}
			int next = c + n;// 문자에 정수를 더하면 정수로 변환됨
			if(c <= 'Z'){// 변환 전의 문자가 대문자 일 경우
				if(next > 'Z'){// 문자 범위를 벗어난 경우
					next -= 26;// 대문자 범위로 다시 돌려놓기
				}
			}else{//변환 전의 문자가 소문자 일 경우
				if(next > 'z'){// 문자 범위를 벗어난 경우
					next -= 26;// 소문자 범위로 다시 돌려놓기
				}
			}
			sb.append((char)next);// 정수를 문자로 형변환해서 더해주기
		}
		return sb.toString();
	}
}
