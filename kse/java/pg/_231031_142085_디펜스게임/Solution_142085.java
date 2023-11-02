package java.pg._231031_142085_디펜스게임;


import java.util.PriorityQueue;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/142085
//신규_프로그래머스_lv2_142085_디펜스게임
public class Solution_142085 {
    /**
     * enemy[i]만큼의 적군을 쓰러뜨릴 때 남은 병사 enemy[i]명을 소모한다.
     * 남은 병사의 수보다 현재 라운드의 적의 수가 더 많으면 게임이 종료
     * 무적권을 사용하면 병사의 소모없이 한 라운드를 막을 수 있다.
     * @param n     병사의 수
     * @param k     무적권의 개수
     * @param enemy 라운드별 적군의 수
     * @return 깰 수 있느 최대 라운드
     */
    public int solution(int n, int k, int[] enemy) {
        int answer  = 0;
        int soldier = n;
        int counter = k;

        if(haveMoreCounter(counter, enemy.length)) return enemy.length;

        //처리한 round 의 병사의 수를 담는 queue
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < enemy.length; i++) {
            if(haveMoreCounter(counter, enemy.length - i)) return enemy.length;

            // 현재 적군의 수
            int nowEnemy = enemy[i];

            queue.add(nowEnemy);
            // 남은 병사가 부족하여 무적권을 사용해야하는 경우
            if(!haveMoreSoldier(soldier,nowEnemy)){
                // 무적권이 없는 경우 종료
                if(!haveMoreCounter(counter, 1)) break;
                // 가장 수가 많은 경우에서 무적권을 사용.
                soldier += queue.poll();
                counter--;
            }
            soldier -= nowEnemy;
            answer++;
        }

        return answer;
    }

    public boolean haveMoreSoldier(int soldier, long enemy){
        return soldier >= enemy;
    }
    public boolean haveMoreCounter(int counter, int round){
        return counter >= round;
    }
}
