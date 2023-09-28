class Solution {

     /**
     * 문제의 핵심은 커서를 어떻게 이동시켜야 최소 이동거리를 구할 수 있냐는 것
     * 1. 최소로 이동 시키려면 어느 한 지점을 기준으로 왼쪽으로가서 오른쪽으로 끝내거나 (왼쪽으로 쭉가는 경우 포함)
     * 2. 오른쪽으로 갔다 왼쪽으로 끝내거나,
     * 3. 오른쪽으로 쭉가는 경우
     */

    int length = 0;
    int leftRightDistance = 0;

    public int solution(String name) {
        int answer = 0;
        leftRightDistance = name.length() - 1;
        length = name.length();
        //TODO: 상하, 좌우 이동거리 구하기
        for (int i = 0; i < length; i++) {
            //TODO: 상하 스틱 이동
            answer += topDown(name.charAt(i));
            //TODO: 좌우 스틱 이동 구하기
            if (i <= length - 2 && name.charAt(i + 1) == 'A') {
                int endA = i + 1;
                while (endA < length && name.charAt(endA) == 'A')
                    endA++;
                leftStart(endA, i);
                rightAndBack(endA, i);
            }
        }
        return answer + leftRightDistance;
    }

    //TODO: 상하로 이동하는 경우 위와 아래 결정
    private int topDown(char target) {
        int topDistance = target - 'A';
        int downDistance = 'Z' - target + 1;
        return Math.min(topDistance, downDistance);
    }

    //TODO: 왼쪽으로 가서 시작하고, 'A'에서 오른쪽으로 진행하는 경우
    private void leftStart(int endA, int cursor) {
        leftRightDistance = Math.min(leftRightDistance, (length - endA) * 2 + cursor);
    }

    //TODO: 오른쪽갔다 왼쪽 채우기
    private void rightAndBack(int endA, int cursor) {
        leftRightDistance = Math.min(leftRightDistance, cursor * 2 + length - endA);
    }

}
