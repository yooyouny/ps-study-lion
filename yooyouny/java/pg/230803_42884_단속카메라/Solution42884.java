import java.util.*;
class Solution42884 {
    /*
    첫번째 카메라는 가장 먼저 나가는 차량의 진출시점에 설치
    나머지 카메라들은 이전에 설치한 카메라가 커버하지 못하는, 진입시점이 카메라 영역보다 큰 경우에 해당 차량의 진출시점에 설치
    * */
    public int solution(int[][] routes) {
        int answer = 0;
        // 차량이 고속도로에서 빠져나가는 진출시점 기준으로 정렬하면 진입시점보다 다른 차량의 영역과 겹칠 확률이 높아짐
        Arrays.sort(routes, Comparator.comparingInt(route -> route[1]));

        int point = -30000;// 차량 진출지점의 최소값으로 세팅
        for(int[] route : routes){
            if(route[0] > point){// 차량의 진입시점이 이전에 설치한 카메라 지점을 벗어나는 경우
                point = route[1];// 진출시점에 카메라 세팅
                answer++;
            }
        }

        return answer;
    }
}