import java.util.*;
class Solution172927 {
    /*
    완전탐색이 아닌 그리디인 이유 
    광물에 대한 피로도가 가중치가 매겨져 있고 광물을 캐는 순서가 정해져있음  
    * */
    public int solution(int[] picks, String[] minerals) {
        int[][] info = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
        int[][] mineralFatigue = new int[minerals.length / 5 + 1][3];// 곡괭이 별 5개씩 채굴이 가능하므로 5개 광물별 곡괭이들의 피로도확인 
        int pickCnt = Arrays.stream(picks).sum();// 광물이 많아도 곡괭이가 없으면 채굴이 불가능하므로 곡괭이의 수 저장
        int answer = 0;// 최소 피로도 

        for(int i=0; i<minerals.length; i+=5){
            if(pickCnt == 0) break; // 곡괭이가 없는 경우
            int[] fatigue = new int[3];// 다이아곡괭이, 철곡괭이, 돌곡괭이로 채굴했을때의 피로도 저장

            for(int j=i; j<i+5 && j<minerals.length; j++){// 
                char mineralChar = minerals[j].charAt(0);
                int mineralIdx = mineralChar == 'i' ? 1 :
                        mineralChar == 's' ? 2 : 0;

                // 곡괭이 별 해당 광물을 채굴 했을때의 피로도 누적합
                fatigue[0] += info[0][mineralIdx];
                fatigue[1] += info[1][mineralIdx];
                fatigue[2] += info[2][mineralIdx];
            }
            mineralFatigue[i/5] = fatigue;// 현재 섹션의 광물별 피로도 저장 
            pickCnt--;
        }

        // 돌로 캤을때의 피로도가 가장 큰 순서대로 정렬하여 다이아몬드 곡괭이로 채굴해야 피로도가 제일 적음
        Arrays.sort(mineralFatigue, (a, b) -> Integer.compare(b[2], a[2]));

        for(int i=0; i<mineralFatigue.length; i++){
            if(picks[0] > 0) {// 다이아몬드 곡괭이부터 우선적으로 채굴
                answer += mineralFatigue[i][0];
                picks[0]--;// 곡괭이를 한번 썼으면 감소
            }else if(picks[1] > 0) {// 다이아몬드 곡괭이를 다 썼으면 철 곡괭이로 채굴
                answer += mineralFatigue[i][1];
                picks[1]--;
            }else if(picks[2] > 0) {// 철 곡괭이를 다 썼으면 돌 곡괭이로 채굴
                answer += mineralFatigue[i][2];
                picks[2]--;
            }
        }
        return answer;
    }
}