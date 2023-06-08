import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
    
        Arrays.sort(rocks);
        //나올수 있는 최소거리의 최솟값 
        int left = 1;
        //최소 거리의 최댓값 - But, 나올 가능성 없음 = n이 1 이상
        int right = distance;
        //최소 거리가 mid일때 제거되는 바위의 갯수 -> 제거되는 
        //바위의 갯수가 n이하 인지 yes or no 
        //정렬해서 구하면, 최소거리 mid가 n을 만족하지 않으면, mid이상의 값은 어짜피 만족하지 않을거기 때문에 볼필요가 없다. 
        while(left <= right){
            int mid = (left + right)/2;
            //mid에서 지워지는 바위의 갯수 구하기
            if(getRemovedRockCnt(rocks, mid, distance) <= n){
                //n을 만족하는 mid가 나오면 저장하고, left를 전진시켜, 최솟값중에 최댓값을 찾는다.
                //왼쪽에서 가장 오른쪽 값을 구하는 문제
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1; 
            }
        }
        
        return answer;
    }
    
    public int getRemovedRockCnt(int[] rocks, int mid, int distance){
        // mid가 바위(지점) 간 최소 거리가 되어야 함
        // 그렇게 하기 위해 제거 해야할 바위의 개수를 리턴한다. 
        int before = 0; 
        int end = distance;
        
        int removeCnt = 0;
        for(int i = 0; i < rocks.length; i++){
            //사이거리가 mid보다 작으면 mid가 최소거리가 아니기 떄문에 바위를 제거한다.
            if(rocks[i] - before < mid) {
                removeCnt++;
                continue;
            }
            //mid보다 크면 기준을 옮긴다.
            before = rocks[i];
        }
        //before(0)이랑 비교해서 지우고 나서 끝에 남은게 end랑 거리가 mid보다 최소일수도 있음
        if(end - before < mid) removeCnt++;

        return removeCnt;
    }
    
}
