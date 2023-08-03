import java.util.*;
class Solution {
     boolean[] reserveVisited;
    int answer;
    public int solution(int n, int[] lost, int[] reserve) {
        answer = n - lost.length;
        //정렬되지 않으면 항상 왼쪽 학생 먼저 고를 수 없음
        Arrays.sort(lost);
        Arrays.sort(reserve);
        //빌린 학생 기록
        reserveVisited = new boolean[reserve.length];
        //잃어버린 학생과 빌린 학생 겹치는 경우 제거
        remove(lost, reserve);
        //남아있는 학생중 빌릴수 있는 학생에게 빌리기
        for (int i : lost) {
            if(borrow(i, reserve)){
                answer++;
            }
        }
        return answer;
    }

    public void remove(int[] lost, int[] reserve){
        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if(lost[i] == reserve[j]){
                    reserveVisited[j] = true;
                    lost[i] = -1;
                    answer++;
                }
            }
        }
    }

    private boolean borrow(int check, int[] reserve){
        for (int i = 0; i < reserve.length; i++) {
            //정렬되어있으니 왼쪽 먼저 만나면 무조건 왼쪽 사람한테 빌리기
            if(check - 1 == reserve[i] && !reserveVisited[i]){
                reserveVisited[i] = true;
                return true;
            } else if (check + 1 == reserve[i] && !reserveVisited[i]) {
                reserveVisited[i] = true;
                return true;
            }
        }
        return false;
    }
}
