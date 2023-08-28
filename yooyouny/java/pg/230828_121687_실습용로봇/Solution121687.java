class Solution121687 {
    public int[] solution(String command) {
        int x = 0;
        int y = 0;
        int d = 0;// 현재 방향(북)
        int[] dx = {0, 1, 0, -1};// 시계방향으로 오른쪽 90도 회전했을때 전진 시 이동해야하는 좌표 값
        int[] dy = {1, 0, -1, 0};

        for(char c : command.toCharArray()){
            switch(c) {
                case 'R' -> d = (d + 1) % 4;// 현재 방향 기준으로 동쪽을 바라보도록 오른쪽으로 한번 회전
                case 'L' -> d = (d + 3) % 4;// 현재 방향 기준으로 서쪽을 바라보도록 오른쪽으로 세번 회전
                case 'G' -> {// 현재 방향 기준으로 전진
                    x += dx[d];
                    y += dy[d];
                }
                case 'B' -> {// 현재 방향 기준으로 후진
                    x -= dx[d];
                    y -= dy[d];
                }
            }
        }
        return new int[]{x, y};
    }
}