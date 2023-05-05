class Solution43165 {
    int cnt;// 문제에서 요구하는 타겟넘버의 수를 클래스 변수로 선언
    public int solution(int[] numbers, int target) {
        int answer = 0;
        dfs(numbers, target, 0, 0);// numbers의 순서를 바꾸지 않고
        return cnt;
    }
    public void dfs(int[] numbers, int target, int idx, int num){
        if(idx == numbers.length){// idx가 범위를 벗어나면 연산을 종료
            if(num == target) cnt++;// 연산의 결과가 타겟넘버일 경우 타겟넘버의 수 증가
            return;
        }
        dfs(numbers, target, idx+1, num + numbers[idx]);// numbers의 값을 더해가며 idx값 증가
        //처음에 호출했던 idx, num의 값을 유지하고 있기 때문에 값을 다시 수정해줄 필요가 없음
        dfs(numbers, target, idx+1, num - numbers[idx]);// numbers의 값을 빼가며 idx값 증가
    }
}
