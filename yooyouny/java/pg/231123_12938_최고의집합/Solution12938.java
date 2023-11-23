class Solution12938 {
    // n가지 조합으로 합했을때 s가 나오는, 그 중 곱했을떄 최대가되는 집합 구하기
    public int[] solution(int n, int s) {
        // 몫을 이용하여 숫자를 할당하고, 남은 합을 계속해서 줄여가는 방식
        if(n > s) return new int[] {-1};// s가 n보다 작으면 조합을 찾을 수가 없으므로 -1로 리턴
        int[] answer = new int[n];
        int idx = 0;
        while(n > 0){// n이 0보다 커야 나눌 수 있으므로
            int quotient = s/n;
            answer[idx++] = quotient;// 현재 최대 값인 몫을 저장
            s -= quotient;// 현재 몫만큼 더해졌으므로 s에서 감소
            n--;// 숫자 한개를 뽑았으므로 n 감소
        }
        return answer;
    }
}
