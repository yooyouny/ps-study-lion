import java.util.*;
import java.util.stream.Collectors;

class Solution121686 {
    class Program {
        int priority;
        int startTime;
        int usageTime;

        Program(int priority, int startTime, int usageTime){
            this.priority = priority;
            this.startTime = startTime;
            this.usageTime = usageTime;
        }
    }
    /*
    * 대기 큐, 실행 큐(우선순위큐) 생성
    * 대기큐는 실행시간 기준으로 정렬
    * 실행큐는 우선순위, 실행시간 기준으로 정렬
    *
    * while
    * 대기 큐에서 실행해야할 대상을 실행 큐로 push
    *
    * 실행큐에서 poll해 해당 점수 인덱스에 대기시간 저장
    * 현재시간을 실행한 프로그램의 종료시간으로 업데이트
    * */
    public long[] solution(int[][] program) {
        //대기큐 생성 - 시작시간 기준으로 정렬
        Queue<Program> sortedProgram = Arrays.stream(program)
                .map(p -> new Program(p[0], p[1], p[2]))
                .sorted(Comparator.comparingInt(p -> p.startTime))
                .collect(Collectors.toCollection(LinkedList::new));

        //실행큐 생성 - 우선순위, 시작시간 기준으로 정렬
        PriorityQueue<Program> pq = new PriorityQueue<>( (a, b) -> {
            if(a.priority != b.priority){
                return a.priority - b.priority;
            }
            return a.startTime - b.startTime;
        });

        long[] answer = new long[11];
        int time = 0;

        while(!pq.isEmpty() || !sortedProgram.isEmpty()){
            //대기큐에서 현재 시간보다 실행시간이 작은 프로그램은 실행되어야하므로 실행큐에 offer
            while(!sortedProgram.isEmpty() &&
                    time >= sortedProgram.peek().startTime){
                pq.offer(sortedProgram.poll());
            }

            //offer해도 실행큐에 없는 경우 현재시간 조정하고 다시 실행큐에 offer
            if(pq.isEmpty()){
                time = sortedProgram.peek().startTime;
                continue;
            }

            Program p = pq.poll();

            answer[p.priority] += time - p.startTime;// 프로그램 점수 인덱스에 현재 시간 - 실행시간 = 대기시간 저장
            time += p.usageTime;// 현재시간을 실행한 프로그램의 종료시간으로 설정
        }

        answer[0] = time;// 첫 인덱스는 프로그램 전체 실행시간
        return answer;
    }
}