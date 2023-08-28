public class Pg121687 {
    //동, 서 이동 -> 첫번째 북에서 1이 증가하면 동을 봐야하기 때문에
    int[] dx = {0, 1, 0, -1};
    //북, 남 -> 첫번째 북을 보고 있기 때문에
    int[] dy = {1, 0, -1, 0};

    public int[] solution(String command) {
        String[] aCommand = command.split("");
        //dx, dy의 인덱스로 활용할 direction
        int direction = 0;
        //x,y좌표
        int x = 0, y = 0;
        //원형 큐와 비슷한 방식
        // 오른쪽 -> 0 -> 1 -> 2 -> 3
        // 왼쪽 0 -> 3((0 -1 + 4) % 4) -> 2 ((3 - 1 + 4) % 4) -> 1 ((2 - 1 + 4) % 4)
        for (String com : aCommand) {
            switch (com){
                case "R" -> direction = (direction + 1) % 4;
                case "L" -> direction = (direction + 3) % 4;
                case "G" -> {
                    x += dx[direction];
                    y += dy[direction];
                }
                case "B" -> {
                    x -= dx[direction];
                    y -= dy[direction];
                }
            }
        }

        return new int[]{x, y};
    }

    public static void main(String[] args) {
        int[] solution = new Pg121687().solution("GRGRGRB");
        System.out.println(Arrays.toString(solution));
    }
}

