class Solution {
   public int solution(int[][] ability) {
        int row = ability.length;
        int col = ability[0].length;
        visited = new boolean[row][col];
        dfs(ability, 0, 0);
        return maxScore;
    }
    int maxScore=Integer.MIN_VALUE;
    boolean[][] visited;

    private void dfs(int[][]ability, int depth, int total){
        if(depth == ability[0].length){
            //이전까지의 최대 점수와 비교
            if(maxScore < total){
                maxScore = total;

            }
            return;
        }

        for (int i = 0; i < ability.length; i++) {
            if(!isOverlapped(i, depth)){
                visited[i][depth] = true;
                dfs(ability, depth + 1, total + ability[i][depth]);
                visited[i][depth] = false;
            }
        }
    }

    //겹치는 칸이 있으면 유효하지 않은 경우
    private boolean isOverlapped(int row, int col){
        for (int i = row; i >= 0; i--) {
            if(visited[i][col]){
                return true;
            }
        }
        for (int i = col; i >= 0; i--) {
            if(visited[row][i]){
                return true;
            }
        }
        return false;
    }
}
