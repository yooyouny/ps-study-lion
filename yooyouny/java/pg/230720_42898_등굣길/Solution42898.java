import java.util.*;
/*
* mem[i][j] = mem[i-1][j] + mem[i][j-1] 점화식
* */
class Solution42898 {
    static int MOD = 1000000007;
    public int solution(int m, int n, int[][] puddles) {
        int[][] mem = new int[m+1][n+1];//(1.1) 부터 (m.n) 까지의 경로저장할 배열 생성

        for(int[] puddle : puddles){// 웅덩이는 -1로 표시
            mem[puddle[0]][puddle[1]] = -1;
        }

        mem[1][1] = 1;// base case 초기화, 도착지까지 가는 방법은 1가지

        for(int i=1; i<=m; i++){// 웅덩이를 제외한 모든 위치에 해당 위치로 오는 가짓수 계산
            for(int j=1; j<=n; j++){
                if(mem[i][j] == -1) continue; //웅덩이 위치는 연산에서 제외
                // 현재의 위치로 오는 방법은 위에서 오는 방법 + 왼쪽에서 오는 방법 뿐임
                if(mem[i-1][j] > 0) mem[i][j] += mem[i-1][j] % MOD;// 해당 위치 위가 웅덩이도 아니고 이전 가짓수도 존재하는 경우
                if(mem[i][j-1] > 0) mem[i][j] += mem[i][j-1] % MOD;// 해당 위치 왼쪽이 웅덩이도 아니고 이전 가짓수도 존재하는 경우
            }
        }

        return mem[m][n] % MOD;// 도착지점 가짓수를 더해주기만 했고 나머지를 처리해주진 않았으므로
    }
}