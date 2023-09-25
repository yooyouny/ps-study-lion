import java.util.*;
class Solution42897 {
    public int solution(int[] money) {
        int answer = 0;
        int len = money.length;

        /*
        * 1 2 3 4 5
        -> 0 1 2 3 4, 0 2 3 4 5로 쪼개기
        * 환원구조 = 첫번째 집 선택 or 선택 안하는 경우로 생각
        * 첫번째 인덱스와 두번째 인덱스 비교를 위해 맨 앞에 0 집어넣기
        * */

        int[] startHouse = new int[len];// 첫번째 집을 선택하고 마지막 집을 선택하지 않음
        for(int i=1; i<len; i++){
            startHouse[i] = money[i-1];
        }

        int[] lastHouse = new int[len];// 첫번째 집을 선택하지 않고 마지막 집을 선택
        for(int i=1; i<len; i++){
            lastHouse[i] = money[i];
        }

        // 두가지 경우 탐색 후 가장 큰 머니를 리턴
        answer = Math.max(answer, stealHouse(startHouse));
        answer = Math.max(answer, stealHouse(lastHouse));

        return answer;
    }
    private int stealHouse(int[] house){
        for(int i=2; i<house.length; i++){
            house[i] = Math.max(house[i-1], house[i-2] + house[i]);// 현재 위치 = 이전위치 vs 전전위치 + 현재위치
        }
        return house[house.length-1];// 가장 마지막 인덱스의 값이 주어진 집에서의 최대 머니
    }
}