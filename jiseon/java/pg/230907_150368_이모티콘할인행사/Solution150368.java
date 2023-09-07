package com.example.algorithm.ps;

import java.util.Arrays;

public class Solution150368 {
    static int[] discount = {10, 20, 30, 40};
    static int subscribe, cost;

    public int[] solution(int[][] users, int[] emoticons) {
        findResult(0, new int[emoticons.length], users, emoticons); //  dfs 재귀 호출
        return new int[]{subscribe, cost};
    }

    public void findResult(int index, int[] discounts, int[][] users, int[] emoticons) {
        if (index == emoticons.length) {
            int s = 0, c = 0;  // 해당 할인 비율 케이스의 가입자 수와 매출액

            for (int[] user : users) {  // 사용자마다 구독 여부, 매출액 계산
                int sum = 0; // 사용자의 이모티콘 구매 비용
                for (int i = 0; i < emoticons.length; i++) {
                    if (discounts[i] >= user[0]) { // 해당 이모티콘의 할인율이 사용자의 할인율보다 크면
                        sum += emoticons[i] / 100 * (100 - discounts[i]);  // 이모티콘 가격 계산
                    }
                }

                if (sum >= user[1]) s++; // 사용자 가격 제한보다 크다면 가입자 수 카운트
                else c += sum; // 아니라면 매출액 카운트
            }

            if (s > subscribe) {  // 가입자 최댓값보다 크다면 갱신
                subscribe = s;
                cost = c;
            } else if (s == subscribe) {  // 같다면 더 큰 매출액으로 갱신
                cost = Math.max(cost, c);
            }
            return;
        }

        for (int i = 0; i < 4; i++) { // dfs 재귀 호출로 이모티콘 할인율 경우의 수를 계산
            discounts[index] = discount[i];
            findResult(index + 1, discounts, users, emoticons);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution150368().solution(new int[][]{{40,10000}, {25, 10000}}, new int[]{7000, 9000})));
    }
}
