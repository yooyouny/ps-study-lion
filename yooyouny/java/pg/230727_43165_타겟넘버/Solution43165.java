class Solution43165 {
    static int cnt;
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);// 재귀함수 활용
        return cnt;
    }
    public void dfs(int[] numbers, int target, int idx, int sum){
        if(idx == numbers.length){ //기저조건은 idx의 길이가 numbers끝까지 갈 경우
            if(target == sum){// target 숫자에 해당하는 경우만 cnt 증가
                cnt++;
            }
            return;
        }
        // 모든 numbers가 + 인 경우를 시작으로, 끝에서부터 하나씩 -로 변경하여 모든 경우를 탐색
        dfs(numbers, target, idx + 1, sum + numbers[idx]);
        dfs(numbers, target, idx + 1, sum - numbers[idx]);
    }
}