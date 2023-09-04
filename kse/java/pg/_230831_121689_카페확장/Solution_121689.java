package java.pg._230831_121689_카페확장;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

//https://school.programmers.co.kr/learn/courses/15009/lessons/121689
//신규_프로그래머스_lv2_121689_카페확장
public class Solution_121689 {
    // 한 손님이 카페에서 나감과 동시에 다른 손님이 카페에 들어올 경우, 나가는 손님이 먼저 퇴장한 다음 들어오는 손님이 입장
    public int solution(int[] menu, int[] order, int k) {
        int answer = 1;

        // 모든 주문의 시간이 들어있는 큐
        Queue<Integer> orders = Arrays.stream(order)
                .mapToObj(index -> menu[index])
                .collect(Collectors.toCollection(LinkedList::new));

        //현 시간 기준으로 쌓여있는 주문 큐
        Queue<Integer> nowOrders = new LinkedList<>();
        //0초부터 주문이 들어온다
        nowOrders.add(orders.poll());
        int remainTime = 0;
        int nowOrderTime = nowOrders.peek();
        boolean isWorking = true;

        //할 작업이 있고, 남은 시간이 있는 경우 반복
        while (!orders.isEmpty() || !nowOrders.isEmpty()){
            //한바퀴가 손님이 들어오는 turn 인 k초
            remainTime = k;
            //할 작업이 있고, 남은 시간이 있는 경우 반복
            while (!nowOrders.isEmpty() && remainTime > 0) {
                if(!isWorking){ // 작업할 주문이 없어
                    nowOrderTime = nowOrders.peek();
                    isWorking = true;
                }
                if (remainTime - nowOrderTime >= 0){        // 이번 텀에 종료되는 작업이 있는 경우
                    nowOrders.poll(); //끝난거 제거
                    remainTime -= nowOrderTime;
                    isWorking = false;
                }
                else{                                       // 이번 텀에 작업을 종료하지 못하는 경우
                    int diff = nowOrderTime - remainTime;
                    nowOrderTime = diff;
                    remainTime = 0;
                    isWorking = true;
                }
            }
            //orders가 비어있지 않다면 새로운 주문 추가
            if(!orders.isEmpty()) nowOrders.add(orders.poll());
            //가장 손님이 많은 경우 탐색
            answer = Math.max(answer, nowOrders.size());

        }
        return answer;
    }
}
