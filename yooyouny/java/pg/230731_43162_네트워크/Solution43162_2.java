class Solution43162_2 {
    private int find(int n, int[] parent){
        if(parent[n] == n){// 자기 자신이 부모인 경우
            return n;
        }
        return find(parent[n], parent);// 다른 집합에 속해있는 경우면 해당 집합의 부모 찾기
    }
    private void union(int a, int b, int[] parent){// 같은 집합인 경우 작은 숫자로 부모를 변경
        a = find(a, parent);
        b = find(b, parent);
        if( a == b ) return; // 이미 같은 집합인 경우 합집한 연산할 필요 x
        if(a>b){// 숫자가 작은 번호로 부모를 변경
            parent[a] = b;// a의 부모는 b로
        }
        else{
            parent[b] = a;// b의 부모는 a로
        }
    }
    public int solution(int n, int[][] computers) {
        int answer = 0;
        int[] parent = new int[n];
        for(int i=0; i<n; i++) parent[i] = i;// 각 집합의 부모를 자기 자신으로 세팅

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i!=j && computers[i][j] == 1)// 인접행렬에서 1인 경우 같은 집합으로 묶어줌
                    union(i, j, parent);
            }
        }

        for(int i=0; i<n; i++){
            if(i == parent[i]) answer++; // 자기 자신이 상위노드(parent)인 케이스가 집합의 개수
        }

        return answer;// 집합의 개수 반환
    }
}