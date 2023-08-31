class Solution {
    public static class Order{
        int enterance;
        int timeToMakeMenu;

        public Order(int enterance, int timeToMakeMenu) {
            this.enterance = enterance;
            this.timeToMakeMenu = timeToMakeMenu;
        }
    }
    public int solution(int[] menu, int[] order, int k) {
        Order[] orders = new Order[order.length];
        for (int i = 0; i < order.length; i++) {
            orders[i] = new Order(i * k, menu[order[i]]);
        }
        int max = 0;
        int currentTime = 0;
        int lastQueueNow = 0;
        for (int start = 0; start < order.length; start++) {
            //TODO: 상대할 손님
            Order jobNow = orders[start];
            //TODO: 손님의 입장시간보다 현재 시간이 더 이를 경우 상대 할 손님의 입장시간은 현재시간으로 갱신
            if(currentTime < jobNow.enterance){
                currentTime = jobNow.enterance;
            }
            //TODO: 손님의 메뉴가 걸리는 시간동안 다음 손님을 못받기 때문에 currentTime에 메뉴 제작시간을 더함
            currentTime += jobNow.timeToMakeMenu;
            //TODO: 현재 메뉴 제작이 끝났을때 까지 입장했지만 대기중이던 손님이 몇번쨰인지를 구한다.
            while (lastQueueNow < order.length && orders[lastQueueNow].enterance < currentTime){
                lastQueueNow++;
            }
            //TODO: 제작이끝났을때 마지막으로 대기 중이던 n번째 손님과, 처리가 끝난 start번째 손님의 차이가
            // 카페에서 대기중이던 손님의 수
            int count = lastQueueNow - start;
            if(max < count){
                max = count;
            }
        }
        return max;
    }
}
