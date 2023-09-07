package java.pg._230905_150368_이모티콘할인행사;

//https://school.programmers.co.kr/learn/courses/30/lessons/150368
//신규_프로그래머스_lv2_150368_이모티콘할인행사
public class Solution_150368 {
    /**
     * 목표
     * 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
     * 이모티콘 판매액을 최대한 늘리는 것.
     * =========================================
     * 이모티콘마다 할인율은 다를 수 있으며, 할인율은 10%, 20%, 30%, 40% 중 하나로 설정
     * 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매
     * 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 된다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입
     *
     * @param users [비율, 가격]
     * @param emoticons i+1번 이모티콘의 정가
     * @return 이모티콘 플러스 서비스 가입 수와 이모티콘 매출액을 1차원 정수 배열에 담아 return
     */
    int[][] userArr;
    int[] emoticonArr;
    int subscriber = 0; // 최대 구독자 수
    int totalSales = 0; // 최대 매출

    public int[] solution(int[][] users, int[] emoticons) {
        //전역 변수로 저장
        userArr       = users;
        emoticonArr   = emoticons;

        //할인율이 10%, 20%, 30%, 40%로 정해져 있기 때문에
        //사용자가 구매를 선택하는 최소 할일율로 변경 해줌 25% -> 30%
        for (int[] user : users){
            user[0] = user[0] % 10 != 0 ? (user[0] / 10) + 1 : (user[0] / 10);
        }
        rateDfs(new int[emoticons.length], 0);
        return new int[]{subscriber, totalSales};
    }

    // emoticon 들이 가질 수 있는 할인율의 모든 경우의 수를 구함
    public void rateDfs(int[] rates, int count){
        if (count == emoticonArr.length) {  //모든 emoticon의 비율이 정해졌으면 가입인원수와 판매액을 계산
            resultCalc(rates);
            return;
        }

        for (int i = 1; i <= 4; i++) {
            rates[count] = i;
            rateDfs(rates, count+1);
        }
    }

    private void resultCalc(int[] rates) {
        int subCount = 0;   //구독자 수
        int salesCount = 0; //판매 비용

        for (int i = 0; i < userArr.length; i++) {
            int userRate = userArr[i][0];
            int maxSales = userArr[i][1];   //사용자가 이모티콘을 구매하는 최대 비용
            int nowSales = 0;

            boolean subscribe = false;      //구독 유무

            for (int j = 0; j < rates.length; j++) {
                if(userRate > rates[j]) continue;
                nowSales += emoticonArr[j] * ( 10 - rates[j] ) / 10; //사용자가 구매한 이모티콘의 총 비용 계산
                if(nowSales >= maxSales){                            // 비용이 maxSales 를 넘으면 구독
                    subCount++;
                    subscribe = true;
                    break;
                }
            }
            //사용자가 구독하지 않았다면 총 매출에 사용자의 구매액 추가
            if(!subscribe) salesCount += nowSales;
        }

        //기존의 최대 구독자 수보다 현재 구독자 수가 많다면 갱신
        if(subCount > subscriber){
            subscriber = subCount;
            totalSales = salesCount;
        }
        // 구독자 수가 같지만 판매액이 더 크다면 판매액 갱신
        else if(subCount == subscriber && salesCount > totalSales) totalSales = salesCount;
    }


}
