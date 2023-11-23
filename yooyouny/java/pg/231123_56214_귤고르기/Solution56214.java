import java.util.*;
class Solution56214 {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int gyul : tangerine){// 귤 종류, 개수를 함께 저장
            map.put(gyul, map.getOrDefault(gyul, 0) + 1);
        }
        // 개수를 더해 k가 되는지 확인하면 되기 때문에 값만 내림차순으로 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int cnt : map.values()){
            pq.add(cnt);
        }

        int answer = 0;// 종류의 개수, 종류를 최소화하려면 값이 많은 종류 순대로 꺼내면 됨
        while(k > 0){
            if(pq.peek() > k){// 판매할 귤보다 개수가 많으면 해당 종류로 끝낼 수 있음
                answer++;// 종류 개수 증가
                break;
            }else{//판매귤 개수에 미달되는 경우 가짓수를 최소화하기 위해 해당하는 귤을 일단 모두 상자에 담음
                k -= pq.poll();
                answer++;// 종류 개수 증가
            }
        }
        return answer;
    }
}