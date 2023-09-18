class Solution {
  public int solution(int[] stones, int k) {
        int answer = 0;
        int min = 1;
        int max = 200_000_000;
        //TODO: 건널수 있는 사람의 수를 이분탐색한다.
        while (min <= max){
            //건널 사람의 수
            int mid = (min + max) / 2;
            //mid명이 건널수 있으면, 더 큰 수의 사람도 건널 수 있는지 확인
            if(canAcrossBridge(stones, k, mid)){
                min = mid + 1;
                //가능했던 사람의 수를 저장, 이후 Loop에서 더 큰 값이 들어오면 그 값으로 갱신
                answer = Math.max(mid, answer);
            }else {
                //mid명이 건너는게 불가능한 경우
                max = mid - 1;
            }
        }
        return answer;
    }

    private boolean canAcrossBridge(int[] stones, int k, int people){
        int skip = 0;
        for (int stone : stones) {
            if(stone - people < 0){
                skip++;
            }else {
                skip = 0;
            }
            if(skip == k) return false;
        }
        return true;
    }
}
