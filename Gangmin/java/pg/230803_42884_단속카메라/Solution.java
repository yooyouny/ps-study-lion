import java.util.Arrays;
import java.util.Comparator;

public class Pg42884 {
    public int solution(int[][] routes) {
        int answer = 0;
        int index = 0;
        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));
        int cam = Integer.MIN_VALUE;
        for (int[] route : routes) {
            //카메라가 다음 차의 진입지점보다 작으면 경로가 겹치지 않기 때문에 카메라를 세우고
            //갱신한다.
            if(cam < route[0]){
                cam = route[1];
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int solution = new Pg42884().solution(new int[][] {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}});
        System.out.println(solution);
    }
}

