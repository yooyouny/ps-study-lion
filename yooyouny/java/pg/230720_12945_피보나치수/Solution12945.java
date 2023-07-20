class Solution12945 {
    public int solution(int n) {
        int[] answer = new int[n+1];// 이전 피보나치 결과를 배열에 저장하여 활용함으로써 연산횟수를 줄임
        answer[1] = 1;
        for(int i=2; i<=n; i++){
            answer[i] = (answer[i-2] + answer[i-1]) % 1234567;
            // 오버플로우를 방지하기위해 처음부터 나머지 값을 저장하도록 계산
        }
        return answer[n];
    }
}