import java.util.*;
class Solution42862 {
    public int solution(int n, int[] lost, int[] reserve) {
        int possible = 0;// 도난상황에서도 여분의 체육복이 있는 사람의 수

        Arrays.sort(reserve); // 여분 체육복 순서대로 잃어버린 번호와 비교하기 위해 둘다 선형으로 정렬
        Arrays.sort(lost);

        // 체육복 여분 있는 사람이 분실 할 수도 있음
        for(int i=0; i<lost.length; i++){
            for(int j=0; j<reserve.length; j++){
                if(reserve[j] == lost[i]){
                    reserve[j] = -1;// 여분 체육복을 분실했으므로 reserve에서 제거 하기위해 -1로 초기화
                    lost[i] = -1; // 여분 체육복이 사라진거지 원래 체육복을 분실한게 아니므로 lost에서 제거하기 위해 -1로 초기화
                    possible++;// 여분의 체육복이 있는 사람수를 증가시킴
                    break;// 다음 분실 번호를 여분 목록에서 찾기 위해 break
                }
            }
        }


        for(int i=0; i<lost.length; i++){
            for(int j=0; j<reserve.length; j++){
                if((reserve[j] + 1 == lost[i]) || (reserve[j] - 1 == lost[i])){ // 여분있는 사람이 분실 번호에게 빌려줄 수 있으면
                    reserve[j] = -1;// 여분체육복을 사용했으므로 -1로 초기화
                    possible++;// 여분 체육복이 있는 사람수를 증가시킴
                    break;// 다음 분실 번호를 확인하기 위해 break
                }
            }
        }

        return n - lost.length + possible;// 전체인원수 - 분실인원수 + 여분의체육복이 있는 인원 수 = 체육수업이 가능한 인원 수
    }
}