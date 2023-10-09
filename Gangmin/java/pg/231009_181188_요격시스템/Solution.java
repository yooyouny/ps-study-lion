import java.util.Arrays;
import java.util.Comparator;
class Solution {
 /**
     * //TODO: 겹치는 구간을 최대로 찾고, 해당 겹치는 구간마다 미사일을 쏘면 최소 미사일을 사용해 문제를 해결할 수 있다.
     * @param targets
     * @return
     */
    public int solution(int[][] targets) {
        int answer = 0;
        //TODO: 겹치는 구간을 최대로 찾기위해 끝지점으로 정렬
        Arrays.sort(targets, Comparator.comparingInt(target ->target[1]));
 
        return shootMissle(targets);
    }

    //TODO: 반복문을 돌며 겹치지 않는 구간이 나올때 마다 ++, flag사용 고려
    private int shootMissle(int[][] targets){
        int count = 0;
        int endPoint = 0;

        for (int[] target : targets) {
            if (target[0] >= endPoint){
                endPoint = target[1];
                count++;
            }
        }
        return count;
    }
}
