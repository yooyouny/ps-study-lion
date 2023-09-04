import java.util.Arrays;
/*
- 물류창고 위치로부터 가장 먼 거리부터 진행해야 가장 적은 이동거리만에 배달/수거가 가능
- 배달 한 거리만큼 수거하고 수거한 거리만큼 배달함
  배달/수거 이동거리 중 제일 긴 이동거리 * 2
- 누적합으로 진행 시 한번에 가능한 양을 넘지 못한 경우 다음번에 배달, 수거할 위치의 상자를 미리 해준다고 생각해야함
* */
class Solution150369 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int dStart = n - 1;
        int pStart = n - 1;

        int dSum = Arrays.stream(deliveries).sum();// 전체 배달 해야할 양
        int pSum = Arrays.stream(pickups).sum();// 전체 수거 해야할 양

        while(true) {
            int dcap = cap;// 한번에 배달 가능한 양 초기화
            int pcap = cap;// 한번에 픽업 가능한 양 초기화
            int index = Integer.MIN_VALUE;// 인덱스 초기화

            for(int i = dStart; i >= 0; i--) {
                if (deliveries[i] > 0) {// 배달 완료한 경우면 제외
                    index = Math.max(index, i);//상자 양이 cap 이내든 초과든 최대 인덱스를 미리 저장해야 이동거리 계산 가능
                    if (deliveries[i] <= dcap) {
                        dcap -= deliveries[i];// 한번에 배달 가능한 양 차감
                        dSum -= deliveries[i];// 전체 배댤 해야할 양 차감
                        deliveries[i] = 0;// 배달 완료로 초기화
                    } else {// 한번에 배달 가능한 양을 넘기면
                        deliveries[i] -= dcap;// 남은 cap만큼 미리 배달
                        dSum -= dcap;// 전체 배달해야할 양 차감
                        dStart = i;// 다음에 탐색할 인덱스 저장
                        break;
                    }
                }
            }

            for(int i = pStart; i >= 0; i--) {
                if (pickups[i] > 0) {
                    index = Math.max(index, i);
                    if (pickups[i] <= pcap) {
                        pcap -= pickups[i];// 한번에 픽업 가능한 양 차감
                        pSum -= pickups[i];// 전체 픽업헤야할 상자 차감
                        pickups[i] = 0;// 픽업한 상자 초기화
                    } else {
                        pickups[i] -= pcap;// 남은 cap만큼 상자 미리 픽업
                        pSum -= pcap;// 전체 픽업해야할 양 차감
                        pStart = i;// 다음 탐색 인덱스 저장
                        break;
                    }
                }
            }

            if (index >= 0) {//물류창고 위치로부터 이동을 한 경우
                //이동거리는 인덱스 끝에서부터 배달, 픽업 진행한 인덱스 중 가장 긴 인덱스
                answer += (index + 1) * 2; //같은 이동거리로 배달, 픽업을 진행하므로 * 2
            }
            if (dSum == 0 && pSum == 0) {// 더 이상 배달, 탐색 할 상자가 없으면 종료
                break;
            }

        }


        return answer;
    }
}