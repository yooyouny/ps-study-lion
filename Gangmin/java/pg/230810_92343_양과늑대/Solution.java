public class Pg92343 {
    //왼쪽 노드
    int[] l;
    //
    int[] r;
    //info를 전역으로 변경
    int[] val;
    int[] visited = new int[1<<17];
    int n;
    int ans = 1;
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        n = info.length;
        l = new int[n];
        r = new int[n];
        val = new int[n];
        Arrays.fill(l, -1);
        Arrays.fill(r, -1);
        val = Arrays.copyOf(info, info.length);
        for (int[] edge : edges) {
            if(l[edge[0]] == -1) l[edge[0]] = edge[1];
            else r[edge[0]] = edge[1];
        }
        dfsState(1);
        return answer = ans;
    }

    public void dfsState(int state){
        if(visited[state] == 1) return;
        visited[state] = 1;
        //TODO: 늑대와 양의 갯수를 센다.
        int wolf = 0; int nodeCount = 0;
        for (int i = 0; i < n; i++) {
            //TODO: i노드를 방문한적이 있는 상태인지 확인, 방문한 노드에 대해 양 or 늑대 count
            if((state & (1 << i)) != 0){
                //Wolf 면 1, 양이면 0
                wolf += val[i];
                nodeCount++;
            }
        }
        //TODO: wolf가 더 많은 상태인지 확인
        if(wolf * 2 >= nodeCount) return;
        //TODO: 아니라면, 상태의 양의 max 값보다 큰지
        ans = Math.max(ans, nodeCount - wolf);
        /**
         * TODO: 전체 노드의 방문 노드에 대해서 확인한다.
         * 이때 3가지 경우가 있는데,
         * 1. 이미 방문한 상태인 경우
         * 2. 기존 상태에서 탐색을 진행했을때, 늑대가 더 많아지는 경우
         * 3. 기존 상태에서 탐색을 진행했을때, 위의 두 조건에 걸리지 않는 경우
         * 3번 경우만 지속적으로 아래 방향으로 탐색
         * 예 : 001 -> 011 -> 111 -> 101
         */
        for (int i = 0; i < n; i++) {
            //state에 inode를 방문 하지 않았다면, 하위 노드를 볼 필요 X
            if((state & (1 << i)) == 0) continue;
            //i node의 왼쪽 탐색
            if(l[i] != -1) dfsState(state | (1 << l[i]));
            //i node의 오른쪽 탐색
            if(r[i] != -1) dfsState(state | (1 << r[i]));
        }
    }

    public static void main(String[] args) {
        int solution = new Pg92343().solution(
            new int[] {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
            new int[][] {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}});
        System.out.println(solution);
    }
}
