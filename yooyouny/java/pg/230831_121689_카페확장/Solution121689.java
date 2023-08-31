class Solution121989 {
    /*
    * */
    public int solution(int[] menu, int[] order, int k) {
        int customer = 0;// 현재 카페에 들어와있는 손님 수
        int realTime = 0;// 음료제조를 완료한 시간
        int right = 0; // 윈도우 사이즈를 조절할 인덱스 변수

        //left, right 포인터는 인덱스 0부터 가리키는 것으로 시작
        for(int left=0; left<order.length; left++){
            int arriveTime = k * left;// left 인덱스의 입장시간 계산
            if(realTime < arriveTime)// 현재시간보다 입장시간이 클 경우 현재시간을 갱신
                realTime = arriveTime;

            realTime += menu[order[left]]; // 음료 제조를 완료한 시간을 현재시간에 갱신

            // 현재 시간보다 도착시간이 작은 손님들을 탐색
            while(right < order.length && realTime > arriveTime){
                right++;// 윈도우의 사이즈를 늘려가면서 도착시간이 작은 조건에 맞는지 탐색
                arriveTime = k * right;
            }
            customer = Math.max(customer, right - left);// 윈도우의 사이즈가 현재 카페에 있는 손님 수
        }
        return customer;
    }
}