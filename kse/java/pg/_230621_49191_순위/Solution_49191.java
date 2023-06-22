package java.pg._230621_49191_순위;

import java.lang.reflect.Array;
import java.util.Arrays;

//신규_프로그래머스_lv3_순위
//https://school.programmers.co.kr/learn/courses/30/lessons/49191
public class Solution_49191 {
    /**
     * 1. 승패 기록을 남을 배열을 n * n 사이즈만큼 만들고 초기화 해준다.
     *      - nullable 에러 방지를 위해 값을 초기화
     *      - [i][i]는 자기자신이기 때문에 결과값을 self로 채워준다.
     *      - [A가][B에게] = [ wins || lose ]로 결과를 넣어준다.
     *      - 배열을 int형으로 만들면 과정을 축소할 수 있다.
     *
     * 2. 플로이드-워셜(Floyd-Warshall) 알고리즘 :  '거쳐가는 정점'을 기준으로 모든 최단 경로를 구하는 알고리즘
     *      - https://chanhuiseok.github.io/posts/algo-50/
     *      - x, y에서 전달되는 값이 같은 경우 [y][x]의 값도 같아진다.
     *
     * 3. board의 한 행의 모든 값이 채워진 경우 해당 행의 index 선수의 랭킹을 알 수 있다.
     *
     * @param n       선수의 수
     * @param results 경기 기록
     * @return
     */
    public String[][] initGraph(int n, int[][] results){
        String[][] board = new String[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], "");
            board[i][i] = "self";
        }

        //[A가][B에게] 이겼다 || 졌다
        for(int[] result : results){
            board[result[0]-1][result[1]-1] = "wins";
            board[result[1]-1][result[0]-1] = "lose";
        }
        return board;
    }

    public int solution(int n, int[][] results) {
        int answer = 0;

        //1. 승패 기록을 남을 배열을 n * n 사이즈만큼 만들고 초기화 해준다.
        String[][] board = initGraph(n, results);

        //2. 플로이드-워셜(Floyd-Warshall) 알고리즘
        //x, y에서 전달되는 값이 같은 경우 [y][x]의 값도 같아진다.
        for (int i = 0; i < n; i++) {
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if(board[x][i].equals("wins") && board[i][y].equals("wins"))
                        board[x][y] = "wins";
                    if(board[x][i].equals("lose") && board[i][y].equals("lose"))
                        board[x][y] = "lose";
                }
            }
        }

        //3. board의 한 행의 모든 값이 채워진 경우 해당 행의 index 선수의 랭킹을 알 수 있다.
        for (String[] line : board){
            boolean allFilled = Arrays.stream(line).noneMatch(value -> value.equals(""));
            if(allFilled) answer++;
        }
        return answer;
    }

//    public void printBoard(String[][] board){
//        for (String[] line : board)
//            System.out.println(Arrays.toString(line));
//
//        System.out.println("------------------------------");
//    }

}
