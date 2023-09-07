class Solution {
      private static final int[] DISCOUNT_RATES = {90, 80, 70, 60};
    private static int maxEPlusCount = Integer.MIN_VALUE;
    private static int maxExpense = Integer.MIN_VALUE;
    public int[] solution(int[][] users, int[] emoticons) {
        recursion(users, emoticons, 0, new int[emoticons.length]);
        return new int[]{maxEPlusCount, maxExpense};
    }

    //TODO: 모든 할인율 경우의 수 탐색 -> 목적을 달성하는 조건을 만족하는 경우 반환값을 update한다.
    private void recursion(int[][] users, int[] emoticons, int depth, int[] rates){
        //TODO: 모든 이모티콘에 대한 할인율을 적용했으면 계산
        if(depth == emoticons.length){
            updateEmojiStatus(rates, users, emoticons);
            return;
        }
        //TODO: 중복을 허용해서 할인율을 모두 고른다.
        for (int discountRate : DISCOUNT_RATES) {
            rates[depth] = discountRate;
            recursion(users, emoticons, depth + 1, rates);
        }
    }

    //TODO: 이모티콘에 적용된 할인율을 기준으로 각 유저의 구매여부를 확인한다.
    //  누적 금액이 해당 유저의 최대 구매 금액을 넘어서면, 이모티콘 구매 누적액을 0으로 갱신하고, plus를 1증가시킨다.
    private void updateEmojiStatus(int[] rates, int[][] users, int[] emoticons){
        //TODO: 유저의 구매여부를 정하는 할인액과 최대 구매 금액을 기준으로 각 이모티콘에대한 누적액과 plus구매 여부를 확인한다.
        int ePlus = 0;
        int totalExpense = 0;
        for (int[] user : users) {
            int discountRate = user[0];
            int maxCost = user[1];
            int expense = 0;

            for (int i = 0; i < emoticons.length; i++) {
                if(100 - rates[i] >= discountRate){
                   expense += emoticons[i] * rates[i] / 100;
                }
                //구매 비용의 합이 일정 가격 이상이 되는 경우
                if(expense >= maxCost){
                    ePlus++;
                    expense = 0;
                    break;
                }
            }
            totalExpense += expense;
        }
        //TODO: 1. 지금까지 구해진 전체 이모티콘 플러스와 비교해서 더 크면 갱신한다.
        //  2. 이모티콘 플러스의 갯수가 같다면, 총 구매가격이 더 큰것으로 갱신한다.
        if(maxEPlusCount < ePlus){
            maxEPlusCount = ePlus;
            maxExpense = totalExpense;
        } else if (maxEPlusCount == ePlus) {
            maxExpense = Math.max(maxExpense, totalExpense);
        }
    }
}
