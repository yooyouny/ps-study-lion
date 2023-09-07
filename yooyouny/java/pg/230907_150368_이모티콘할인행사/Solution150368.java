import java.util.*;
/*
이모티콘 배열의 최대길이 7, 할인율 4가지 중복조합 = 약 15만 * 유저 배열 최대길이 100 = 천만
1억번 연산 미만이면 완전탐색으로 진행
* */
class Solution150368 {
    static double[] discountPercent = new double[]{0.1, 0.2, 0.3, 0.4};
    static int profitPrice;// 할인율에 따른 이모티콘 수익

    public int[] solution(int[][] users, int[] emoticons) {
        List<double[]> discountList = new ArrayList<>();// 이모티콘 별 할인율을 중복으로 선택하여 조합
        generateComb(discountList, new double[emoticons.length], emoticons.length, 0);

        int[] answer = new int[2];// 최대 플러스서비스 가입자 수, 해당 할인율로 얻을 수 있는 이모티콘 수익

        // 할인율 조합 중 하나를 고르고 유저 배열을 순회하며 기준에 해당하는 멤버수, 수익 구하기
        for(int i=0; i<discountList.size(); i++){
            double[] percent = discountList.get(i);

            // 할인율을 key로, 해당 할인율에 적용된 이모티콘 가격들을 합한 가격을 value로 하는 map 생성
            Map<Integer, Integer> discountedPriceMap = getDiscountPrice(percent, emoticons);

            int plusMember = 0;// 할인율 조합에 따른 플러스 멤버 수
            profitPrice = 0;// 할인율 조합에 따라 플러스 멤버가입을 안하는 유저들의 이모티콘 수익

            for(int j=0; j<users.length; j++){
                int limitPercent = users[j][0];
                int limitPrice = users[j][1];

                if(isPlusMember(discountedPriceMap, limitPercent, limitPrice)){// 기준 할인율 이상인 가격들의 합이 기준 가격 이상인지 확인
                    plusMember++;// 기준가격 이상이면 플러스 멤버에 가입
                    // 이모티콘 수익을 전역변수 profitPrice에 저장
                }

                if(plusMember>answer[0]){// 항상 플러스 멤버를 많이 가입시키는 것을 우선적으로 하고
                    answer[0] = plusMember;
                    answer[1] = profitPrice;
                }else if(plusMember == answer[0]){
                    if(profitPrice>answer[1]){// 이모티콘 판매 수익을 극대화 해야함
                        answer[0] = plusMember;
                        answer[1] = profitPrice;
                    }
                }
            }
        }

        return answer;
    }
    // 기준할인율 이상의 가격들의 합이 기준가격 이상이면 플러스 멤버에 가입, 미만이면 가입하지 않으므로 이모티콘 판매액에 누적
    private boolean isPlusMember(Map<Integer, Integer> map, int limitPercent, int limitPrice){
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getKey() < limitPercent) continue;// 기준 할인율 미만이면 제외
            sum += entry.getValue();// 기준 할인율 이상이면 해당 할인율의 이모티콘을 전부 구입하므로 sum
        }
        if(limitPrice <= sum){
            return true;// 플러스 이모티콘 서비스에 가입
        }else{
            profitPrice += sum;// 기준가격 미만으로 플러스 서비스 가입을 안하므로 이모티콘 판매액에 포함시킴
            return false;
        }
    }
    //할인율에 따라 이모티콘들의 할인가격을 구하는데 원하는 할인율에 해당되면 전부 구입하므로 가격을 누적해서 맵으로 저장
    private Map<Integer, Integer> getDiscountPrice(double[] percent, int[] price){
        Map<Integer, Integer> discountedPrice = new HashMap<>();
        for(int i=0; i<price.length; i++){
            int key = (int)(percent[i] * 100);
            int total = (int)(price[i] - (percent[i] * price[i]));
            discountedPrice.put(key, discountedPrice.getOrDefault(key, 0) + total);
        }
        return discountedPrice;
    }
    // 이모티콘 별 할인율을 중복으로 선택하는 조합 생성
    private void generateComb(List<double[]> list, double[] discount, int len, int cnt){
        if(cnt == len){
            double[] copy = discount.clone();// 할인율을 배열에다가 저장했으므로 같은 주소를 가르키지 않기위해 clone해서 저장
            list.add(copy);
            return;
        }
        for(int i=0; i<4; i++){// 순회할떄마다 인덱스 0부터 시작해서 중복으로 선택
            discount[cnt] = discountPercent[i];
            generateComb(list, discount, len, cnt + 1);
        }
    }
}