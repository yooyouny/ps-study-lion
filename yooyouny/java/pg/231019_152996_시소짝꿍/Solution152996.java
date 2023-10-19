import java.util.*;
class Solution152996 {
    public long solution(int[] weights) {
        long answer = 0;// 순서쌍의 개수
        Arrays.sort(weights);// 몸무게보다 작거나 큰 값으로만 비교하기 위해 정렬
        Map<Double, Integer> map = new HashMap<>();// 몸무게에 해당하는 사람수를 저장하기 위해 hash사용

        for(int w : weights) {
            double weight = w * 1.0;
            // 정렬을 통해 몸무게보다 같거나 큰 경우만 비교하면 되므로 현재 몸무게의 2/2, 2/4, 3/4배 한 경우만 고려
            double m2 = (w*2.0) / 3.0;
            double m3 = (w*1.0) / 2.0;
            double m4 = (w*3.0) / 4.0;

            // 해당 몸무게, 배수에 해당하는 인원 수 만큼 순서쌍의 개수도 증가
            if(map.containsKey(weight))
                answer += map.get(weight);
            if(map.containsKey(m2))
                answer += map.get(m2);
            if(map.containsKey(m3))
                answer += map.get(m3);
            if(map.containsKey(m4))
                answer += map.get(m4);

            map.put((w*1.0), map.getOrDefault((w*1.0), 0) + 1);// 해당 몸무게가 처음으로 등장 한 경우 초기화해주고 인원 수 증가
        }

        return answer;
    }
}