
class Solution {
    public int solution(int n) {
    	int[] arr = new int[n+1];
    	arr[0] = 0;
    	arr[1] = 1;
    	
    	if(n == 0) return arr[0];
    	else if(n == 1) return arr[1];
    	else {
            //1234567이상의 수로 연산해버리면 int형의 범위인 2147483647을 아득히 넘어버리기 때문에 정상적인 나머지 값을 가질수 없다. (A + B) % C = (A%C + B%C) % C의 성질을 이용해 int형 범위 내에서 계산 해보자.
    		for (int i = 2; i < n+1; i++) arr[i] = arr[i-1] % 1234567 + arr[i-2] % 1234567;
    		return arr[n] % 1234567;
    	}
    }
}
