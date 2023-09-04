class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        //트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리를 return 
        
        int d = 0;
        int p = 0;
        for(int i=n-1; i>=0; i--){
            //i지점의 배달, 수거 해야하는 물건 양
            d -= deliveries[i];
            p -= pickups[i];
            
            //배달, 수거 모두 완료될때까지 i지점을 반복해야되니, OR 반복
            while(d < 0 || p < 0){
                //cap만큼 증가
                d += cap;
                p += cap;
                //반복하는 만큼 i지점을 방문
                answer += (i+1)*2;
            }
        }
        
        return answer;
    }
}
