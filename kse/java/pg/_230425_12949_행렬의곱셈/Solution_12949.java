package java.pg._230425_12949_행렬의곱셈;



public class Solution_12949 {
    //행렬의 곱셈 크기는 arr1의 가로 길이 x arr2의 세로 길이
    class Solution {
        private static int[][] solution(int[][] arr1, int[][] arr2) {
            int ySize = arr1.length;                //결과 행렬의 세로 길이
            int xSize = arr2[0].length;             //결과 행렬의 가로 길이
            int calc  = arr1[0].length;             //1자리의 값을 계산하는데에 필요한 계산 수

            int[][] answer = new int[ySize][xSize]; //y,x사이즈대로 배열 초기화

            for (int y=0;y<ySize;y++){
                for (int x=0;x<xSize;x++){
                    int value = 0;
                    for(int z=0;z<calc;z++){
                        value += arr1[y][z] * arr2[z][x];
                    }
                    answer[y][x] = value;

                }
            }
            return answer;
        }
    }

}
