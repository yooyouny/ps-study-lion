import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
   PriorityQueue<int[]> waitQueue;
    PriorityQueue<int[]> sleepQueue;
    long[] answer = new long[11];
    public long[] solution(int[][] program) {
        waitQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]){
                //호출시간이 같은 경우, 우선순위가 높은 순서로 정렬
               return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        //sleepQueue는 호출시간 순으로 정렬 -> 일단 호출시간이 되어야 시작할수있고, 둘다 시작이 가능해도 먼저 호출된 프로그램이 실행됨
        sleepQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        for (int[] info : program) {
            sleepQueue.offer(info);
        }
        process();
        return answer;
    }

    private void process(){
        int currentTime = -1;
        int remain = 0;
        while (!(sleepQueue.isEmpty() && waitQueue.isEmpty()) || remain != 0){
            currentTime++;
            if(remain > 0) remain--;
            //TODO : 시작시간이 된 Job들을 꺼내서 waitQueue에 먼저 넣는다.
            //  처음에 작업이 하나도 없어도 waitQueue에 일단 넣는 이유
            //      -> waitQueue의 정렬 조건이 호출시각이 같은 경우, 우선순위로 정렬되기 때문에, 실제 실행시의 조건을 반영함
            //      -> 대기 시간을 측정하는 방법을 실행시 현재 시간 - 호출시간으로 가져가기 때문에 ,
            //      굳이 sleepQueue에서 실행가능한 작업을 다시 확인하는게 번거로움
            while (!sleepQueue.isEmpty() && sleepQueue.peek()[1] == currentTime){
                waitQueue.offer(sleepQueue.poll());
            }
            //현재 실행중인 Job이 없고 대기중인 작업이 있는경우 대기 작업 실행
            if(remain == 0 && !waitQueue.isEmpty()){
                int[] readyJob = waitQueue.poll();
                //TODO: 작업 시간 설정
                remain = readyJob[2];
                //TODO : 대기시간 측정
                answer[readyJob[0]] += currentTime - readyJob[1];
            }
        }
        answer[0] = currentTime;
    }
}
