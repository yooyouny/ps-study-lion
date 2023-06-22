package java.pg._230621_42583_다리를지나는트럭;

import java.util.ArrayDeque;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/42583
//신규_프로그래머스_lv2_다리를지나는트럭
public class Solution_42583 {
    /**
     * [다리에 트럭을 올릴 수 있는 경우]
     * 1. 다리에 올라가 있는 트럭 개수가 최대 개수보다 작고
     * 2. 다리에 올려진 트럭의 무게의 총합이 weight보다 작아야 한다.
     * -------------------------------------------------------
     * [조건을 만족하지 못한 경우 (while)]
     * 1. 다리에 올라가 있는 트럭이 내려올 때까지 기다린다.
     * 2. 조건에 맞을 때까지 반복한다.
     *
     * @param bridge_length 다리를 건너는데에 걸리는 시간
     * @param weight 다리가 견딜 수 있는 무게
     * @param truck_weights 각 트럭들의 무게
     * @return 모든 트럭이 다리를 건너는데에 필요한 최소 시간
     */
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        int now = 0;
        Queue<int[]> bridge = new ArrayDeque<>();
        for (int truckWeight : truck_weights) {
            //[조건을 만족하지 못한 경우 (while)]
            while (bridge.size() >= bridge_length || now + truckWeight > weight) {
                int[] temp = bridge.remove();       //Queue에서 가장 앞 트럭을 제거
                now   -= temp[0];                   //무게 감소
                answer = Math.max(answer, temp[1]); //최대 시간 구하기
            }
            //[다리에 트럭을 올릴 수 있는 경우]
            bridge.add(new int[]{truckWeight, answer + bridge_length}); //Queue에 트럭 추가
            now += truckWeight; //무게 증가
            answer += 1;        //시간 +1
        }

        while (!bridge.isEmpty()) {
            int[] temp = bridge.remove();
            answer = temp[1];
        }
        return answer;
    }
}
