import java.util.*;
class Solution176962 {
    class Study{
        String name;
        int start;
        int playTime;
        public Study(String name, String start, String playTime){
            this.name = name;
            this.start = getMinute(start);
            this.playTime = Integer.parseInt(playTime);
        }
        public int getMinute(String time){// String 타입을 minute으로 변환
            String[] parse = time.split(":");
            return (Integer.parseInt(parse[0]) * 60) + Integer.parseInt(parse[1]);
        }
    }

    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        Queue<Study> plan = new PriorityQueue<>((s1, s2) -> s1.start - s2.start);// 빨리 시작하는 순서대로 정렬
        Stack<Study> waiting = new Stack<>();// 진행중인과제, 대기중인 과제를 넣을 스택

        for(String[] p : plans){
            plan.offer(new Study(p[0], p[1], p[2]));// 시간이 빠른 순대로 과제를 넣기 위해 pq 생성
        }

        int nowTime = 0;
        int idx = 0;

        while(!plan.isEmpty() || !waiting.isEmpty()){
            if(waiting.isEmpty()){// 진행해야하는 작업이 없으면 새 작업꺼내서 진행중인 스택
                nowTime = plan.peek().start;// 새로운 작업 시작에 따라 현재시간 조정
                waiting.push(plan.poll());// 새로운 작업 진행
            }
            else if(plan.isEmpty()){// 새 작업이 없으므로 진행중이었던 과제 꺼내서 진행 완료
                answer[idx++] = waiting.pop().name;
            }else{// 현재 작업도 진행중이고 새로운 작업도 진행해야 하는 경우
                if(plan.peek().start < nowTime + waiting.peek().playTime){// 새 작업 시간이 더 빨라서 진행중인 작업을 대기상태로 만들어야하는 경우
                    waiting.peek().playTime -= plan.peek().start - nowTime;// 진행 중이었던 작업의 잔여시간 계산
                    nowTime = plan.peek().start;// 새로운 작업 시작에 따라 현재시간 조정
                    waiting.push(plan.poll());// 새로운 작업 진행
                }else{// 새작업 시작 전에 진행 작업 완료
                    nowTime += waiting.peek().playTime;// 진행 중인 작업 끝난 시간으로 현재시간
                    answer[idx++] = waiting.pop().name;// 작업완료
                }
            }
        }
        return answer;
    }
}