import java.util.Arrays;

public class Solution92342 {
    public int[] solution(int n, int[] info) {
        int[] ryan = dfs(0, new int[11], n, info);// 재귀탐색
        if (ryan == null) {
            return new int[] {-1};
        }
        return ryan;
    }
    private int[] dfs(int index, int[] score, int n, int[] apeach) {//
        if (index == score.length) {// score 끝까지 점수를 채운 경우 점수 비교 후 최댓값 저장
            if (n > 0) return null;// 점수표를 채워도 화살이 남아있는 경우 리턴처리
            if (getScoreDiff(apeach, score) <= 0) return null; // 어피치가 이긴경우 리턴처리
            return Arrays.stream(score).toArray();// 현재 라이언 점수를 저장
        }

        int maxDiff = 0;// 점수차이가 최대여야하므로 점수차이를 저장할 변수
        int[] result = null;// 라이언 점수 배열을 저장할 변수

        for (int hit = 0; hit <= n; hit++) {// 현재 라이언이 맞추는 점수
            score[index] = hit;
            int[] ryan = dfs(index + 1, score, n - hit, apeach);
            if (ryan == null) continue;// 어피치가 이겼거나 화살이 남은 경우

            int diff = getScoreDiff(apeach, ryan);
            if (diff > maxDiff || (diff == maxDiff && isPrior(result, ryan))){// 차이를 최대로 만들어야하므로 diff가 크면 갱신
                //diff가 이전 값과 같은 경우 이전에 저장한 ryan 보다 현재 구한 ryan 점수 중 낮은 점수가 많아야 갱신
                maxDiff = diff;
                result = ryan;
            }
        }

        return result;
    }
    private int getScoreDiff(int[] apeach, int[] ryan) {
        int diff = 0;
        for (int i = 0; i < apeach.length; i++) {
            if (apeach[i] == 0 && ryan[i] == 0) continue;
            if (apeach[i] >= ryan[i]) {// 라이언 기준에서 어피치와의 점수차를 구해야하므로 어피치가 이겼으면 -로
                diff -= 10 - i;
            } else {
                diff += 10 - i;
            }
        }
        return diff;// 라이언과 어피치의 점수차 리턴
    }
    private boolean isPrior(int[] base, int[] comp) {// comp배열이 낮은 점수를 하나라도 먼저 맞췄으면 true리턴
        for (int i = 10; i >= 0; i--) {
            if (comp[i] == base[i]) continue;
            return comp[i] > base[i];
        }
        return false;
    }
}