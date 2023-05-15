import java.util.*;
class Solution42839 {
	Set<Integer> set;// 중복숫자를 걸러내기 위해 set 사용
	public void comb(char[] numbers, boolean[] visited, StringBuilder sb){
		if(sb.length() > 0){// 조합된 숫자가 있으면
			int checkNum = Integer.parseInt(sb.toString());
			if(!set.contains(checkNum) && isPrime(checkNum))// 이미 체크한 소수가 아니면서 소수인 숫자면
				set.add(checkNum);// 소수로 저장
		}

		for(int i=0; i<numbers.length; i++){// 모든 가능한 조합을 생성하기 위해 i를 0부터
			if(!visited[i]){// 힌반 산텍힌 숫자 조각을 다시 선택하지 않기 위해
				visited[i] = true;// 해당 종이조각 선택 표시
				sb.append(numbers[i]);// 조합대상에 넣음
				comb(numbers, visited, sb);// 재귀호출
				visited[i] = false;// 선택한 종이조각 초기화
				sb.deleteCharAt(sb.length() - 1);// 조합대상에서 제외
			}
		}
	}
	private boolean isPrime(int n){
		if(n < 2) return false;// 0과 1은 소수가 아님

		for(int i=2; i<=n/2; i++){// 빠른 연산을 위해 N/2까지만 체크
			if(n % i == 0)// 1과 자기 자신이 아닌 다른 수로 나눠지면 소수가 아님
				return false;
		}
		return true;// 나눠지는 수가 없으면 소수
	}
	public int solution(String numbers) {
		set= new HashSet<>();// 메모리에 생성
		// numbers를 자릿수 단위로 접근하기 위해 toCharArray 사용, 조합 생성을 위해 boolean 배열 사용, 문자열 생성 위해 StringBuilder 사용
		comb(numbers.toCharArray(), new boolean[numbers.length()], new StringBuilder());
		return set.size();// set에 담겨진 숫자가 모두 소수인 숫자의 수 이므로 size로 리턴
	}
}
