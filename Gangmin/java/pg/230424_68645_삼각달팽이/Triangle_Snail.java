import java.util.Arrays;

public class Triangle_Snail {
//답을 저장할 배열
    static int[][] map;
    
    public static int[] solution(int n) {
   //2차원 배열로 저장하기 위한 x, y
   //x를 0부터 찍기 위해 -1로 초기화
	int x = -1;
        int y = 0;
        int num = 1;
        int index = 0;
        map = new int[n][n];
	//n번만큼 반복한다.
        for(int i = 0 ; i < n ; i++){
            for(int j = i; j < n; j++){
        //x를증가시키는 경우
		if(i % 3 == 0)
                    x++;
	//y를 증가시키는 경우
                if(i % 3 == 1)
                    y++;
	//x와 y를 감소시키는 대각선 방향
                if(i % 3 == 2){
                    x--;
                    y--;
                }
	//j만큼 한 방향으로 증가시키면서 숫자 대입
                map[x][y] = num++;
            }
        }
	//n + n-1 + n-2...1의 크기가 배열의 크기
        int[] answer = new int[n*(n + 1) / 2];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i + 1; j++){
                answer[index] = map[i][j];
                index++;
            }
        }
        return answer;
    }
}

