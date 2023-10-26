import java.util.*;
class Solution154538 {
    /*
    최소 횟수 -> dfs가 아닌 queue로 처리, dfs일 경우 최대 재귀 횟수 초과로 런타임 에러 발생
    시간 초과 -> 이미 이전에 최소횟수로 큐에 넣은 값이 또 나온 경우 중복으로 처리
    * */
    public int solution(int x, int y, int n) {
        if(x == y) return 0;

        Queue<int[]> queue = new LinkedList<>();// 최소 횟수만에 y값을 찾기 위해 bfs
        Set<Integer> set = new HashSet<>();// 중복 체크를 위한 set
        queue.add(new int[]{x, 0});// 변환한 수와 변환 횟수를 함께 저장

        while(!queue.isEmpty()){
            int[] pop = queue.poll();
            int num = pop[0];
            int cnt = pop[1];

            if(num > y) continue;// 변환한 값이 결과값을 초과하게 되면 해당 결과는 연산하지 않음

            if(num == y){// 결과값과 동일하면 현재 변환 횟수 리턴
                return cnt;
            }

            // 중복여부 확인 후 이전에 적은 변환 횟수로 나왔던 수가 아니면 queue, set에 add
            if(!set.contains(num + n)){
                queue.add(new int[]{num + n, cnt+1});
                set.add(num + n);
            }
            if(!set.contains(num * 2)){
                queue.add(new int[]{num * 2, cnt+1});
                set.add(num * 2);
            }
            if(!set.contains(num * 3)){
                queue.add(new int[]{num * 3, cnt+1});
                set.add(num * 3);
            }
        }

        return -1;// 큐가 빌때까지 변환했지만 결과값에 도달하지 못한 경우 -1리턴
    }
}