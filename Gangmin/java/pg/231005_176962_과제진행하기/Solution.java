import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Solution {
    List<String> answer = new ArrayList<>();
    //작업 큐
    Queue<Homework> workingQueue = new LinkedList<>();
    //시작대기 작업 큐
    PriorityQueue<Homework> waitingQueue = new PriorityQueue<>(Comparator.comparing(o -> o.startTime));
    //보류 작업 큐
    Stack<Homework> delayedQueue = new Stack<>();
    public String[] solution(String[][] plans) {
        initWaitingQueue(plans);
        int NOW = 0;
        int duration = 0;
        //TODO 보류작업 큐와 시작대기큐에 작업이 없을때까지 반복
        while (!(waitingQueue.isEmpty() && delayedQueue.isEmpty())){
            //TODO : 진행중인 작업이 없는 경우
            if(workingQueue.isEmpty()){
                //TODO: 작업 대기 큐가 비어있고, 밀린 작업 큐가 비어있지 않은 경우
                if(waitingQueue.isEmpty()){
                   workingQueue.offer(delayedQueue.pop());
                } // TODO: 작업 대기큐가 비어있지 않고, 밀린 작업 큐가 비어있는 경우
                else if(delayedQueue.isEmpty()){
                    Homework newJob = waitingQueue.poll();
                    //이 경우는 맨 처음 시작하는 경우기 때문에, NOW가 0인상황
                    // NOW를 초기화 해준다.
                    NOW = newJob.startTime;
                    workingQueue.offer(newJob);
                }
                //TODO: 둘다 있는 경우
                else {
                    //TODO: 다음 작업을 시작해야할 시간이 된 경우
                    if(NOW == waitingQueue.peek().startTime){
                        workingQueue.offer(waitingQueue.poll());
                    }
                    //TODO: 다음 작업 시작전에 남은 시간이 있어서, 밀린 작업을 할 수 있는 경우
                    else {
                        workingQueue.offer(delayedQueue.pop());
                    }
                }
            }
            //TODO : 진행중인 작업이 있는 경우
            else {
                //TODO: 진행중인 작업을 다음 작업 시작전에 끝낼수 있는 경우 -> 작업을 미룰 필요가 없는경우
                //  다음 작업이 없는 경우 -> 모든 작업을 끝냈는데, 밀린 작업이 있는 경우
                if(waitingQueue.isEmpty() || NOW + workingQueue.peek().duration <= waitingQueue.peek().startTime){
                    Homework finished = workingQueue.poll();
                    answer.add(finished.name);
                    NOW += finished.duration;
                }
                //TODO: 진행중인 작업을 다음 작업 시작전에 끝낼 수 없는 경우 -> 작업을 미뤄야 하는 경우
                else {
                    Homework toDelay = workingQueue.poll();
                    //TODO: 현재 시간부터 다음작업 시작 시간까지 작업한 만큼 빼주기
                    // 진행작업 시작 시간이 아니라 현재 시간을 빼야하는 이유:
                    // 진행작업의 시작시간을 빼는 경우, 밀린작업을 다시 꺼내서 진행하는 경우 에러가 난다.
                    toDelay.duration -= waitingQueue.peek().startTime - NOW;
                    delayedQueue.push(toDelay);
                    NOW = waitingQueue.peek().startTime;
                }
            }
        }
        //TODO: 해야할 작업과, 밀린 작업이 없는 경우 while문이 끝나기 때문에, workingQueue에 마지막 작업을 넣어줘야한다.
        Homework last = workingQueue.poll();
        answer.add(last.name);
        return answer.toArray(String[]::new);
    }

    //TODO: 시작대기 큐에 작업 넣기
    private void initWaitingQueue(String[][] jobs){
        for (String[] job : jobs) {
            String[] start = job[1].split(":");
            int min = Integer.parseInt(start[0]) * 60;
            min += Integer.parseInt(start[1]);
            int duration = Integer.parseInt(job[2]);
            waitingQueue.offer(new Homework(job[0], duration, min));
        }
    }


    static class Homework{
        String name;
        int duration;
        int startTime;

        public Homework(String name, int duration, int startTime) {
            this.name = name;
            this.duration = duration;
            this.startTime = startTime;
        }
    }
}
