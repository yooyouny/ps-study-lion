package java.pg._231005_176962_과제진행하기;

import java.util.*;
//https://school.programmers.co.kr/learn/courses/30/lessons/176962
//신규_프로그래머스_lv2_176962_과제진행하기
public class Solution_176962 {
    class Plan{ // 과제 정보 Class
        String name;    // 이름
        int start;      // 시작 시간 (분)
        int playtime;   // 수행 시간

        public Plan(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
        public int getStart() {
            return start;
        }
    }

    public String[] solution(String[][] plans) {
        //정답을 넣을 List
        List<String> answer = new ArrayList<>();

        // 시작시간을 기준으로 정렬된 Queue 에 plan 의 값을 넣어준다.
        Queue<Plan> queue = new PriorityQueue<>(Comparator.comparingInt(Plan::getStart));
        for (String[] plan : plans) queue.add(new Plan(plan[0], timeToMinutes(plan[1]), Integer.parseInt(plan[2])));

        // 수행하다 중지된 plan 을 저장하는 stack
        // 가장 최근 정지된 plan 을 우선으로 꺼내야 하기 때문에 stack을 사용
        Stack<Plan> ready = new Stack<>();

        Plan nowPlan;   // 현재 plan
        int nowTime;    // 현재 시간

        while (!queue.isEmpty()){
            nowPlan = queue.poll();
            nowTime = nowPlan.getStart();

            //다음 Plan 이 없는 경우 도중에 멈출 일이 없기 때문에 현재 plan 을 끝마치고 while 문을 나온다.
            if(queue.isEmpty()){
                answer.add(nowPlan.name);
                break;
            }
            //다음 Plan 이 있는 경우
            else{
                //다음 Plan 의 정보를 받아준다.
                Plan nextPlan = queue.peek();
                int  nextTime = nextPlan.getStart();

                //다음 과제 시작 시간 전에 기존 과제를 끝낼 수 있는 경우
                if (nowTime + nowPlan.playtime <= nextTime) {
                    //현재 과제를 끝마친다.
                    answer.add(nowPlan.name);
                    nowTime += nowPlan.playtime;
                    //남는 시간동안 ready stack 의 과제를 수행한다.
                    while (nowTime < nextTime && !ready.isEmpty()) {
                        Plan readyPlan = ready.pop();
                        if (nowTime + readyPlan.playtime <= nextTime) {
                            answer.add(readyPlan.name);
                            nowTime += readyPlan.playtime;
                        } else {
                            readyPlan.playtime -= (nextTime - nowTime);
                            ready.push(readyPlan);
                            nowTime = nextTime; // while 을 나오기 위해 nowTime 갱신
                        }
                    }
                }
                //현재 과제를 끝마치지 못한 경우
                else {
                    //ready stack 에 plan 을 넣어준다.
                    nowPlan.playtime -= (nextTime - nowTime);
                    ready.push(nowPlan);
                }
            }
        }
        //수행하다 만 과제들을 차례대로 수행해준다.
        while (!ready.isEmpty()){
            answer.add(ready.pop().name);
        }
        return answer.toArray(new String[0]);
    }

    // "hh:mm"의 형태의 시작 시간을 분 수로 계산해준다.
    public int timeToMinutes(String timeString){
        String[] times = timeString.split(":");
        int hour = Integer.parseInt(times[0]);
        int minutes = Integer.parseInt(times[1]);

        return hour * 60 + minutes;
    }
}
