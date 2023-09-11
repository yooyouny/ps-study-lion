import java.util.*;
class Solution150366 {
    static int[] parent;// 부모인덱스 저장 테이블, 병합 시 같은 부모를 저장
    static final int MAX_SIZE = 2501;// 최대 50 * 50
    public String[] solution(String[] commands) {
        List<String> result = new ArrayList<>();

        parent = new int[MAX_SIZE];// 부모의 인덱스 저장
        String[] table = new String[MAX_SIZE];// 값 저장 테이블, 부모 인덱스에 값이 존재하는 경우 부모 인덱스에 있는(병합되어있는) 값을 봐야함
        for(int i=0; i<MAX_SIZE; i++){
            parent[i] = i;// union-find, array를 자신의 인덱스 번호로 초기화
            table[i] = "";
        }

        for(String command : commands){
            String[] parse = command.split(" ");
            switch(parse[0]){
                case "UPDATE" -> {
                    if(parse.length == 3){// UPDATE value1 value2
                        String value1 = parse[1];
                        String value2 = parse[2];
                        for(int i=0; i<MAX_SIZE; i++){
                            if(table[i].equals(value1))
                                table[i] = value2;
                        }
                    }else{// UPDATE r c value
                        int r = Integer.parseInt(parse[1]);
                        int c = Integer.parseInt(parse[2]);
                        String value = parse[3];
                        int idx = getTableIdx(r, c);// 좌표의 값 인덱스를 찾고
                        // 해당 인덱스의 값이 병합셀이면 부모인덱스의 값을 새로운 값으로 변경
                        table[find(idx)] = value;
                    }
                }
                case "MERGE" -> {// MERGE r1 c1 r2 c2
                    int r1 = Integer.parseInt(parse[1]);
                    int c1 = Integer.parseInt(parse[2]);
                    int r2 = Integer.parseInt(parse[3]);
                    int c2 = Integer.parseInt(parse[4]);
                    int parentIdx1 = find(getTableIdx(r1, c1));// 첫번째 좌표의 부모 인덱스
                    int parentIdx2 = find(getTableIdx(r2, c2));// 두번째 좌표의 부모 인덱스

                    if (parentIdx1 != parentIdx2) {// 같은 부모면 병합 대상이 안됨
                        String changeStr = table[parentIdx1].isEmpty() ? table[parentIdx2] : table[parentIdx1];// 값이 존재하는 좌표의 값으로 변경 값 설정
                        union(parentIdx1, parentIdx2);// 첫번째 좌표 기준으로 부모를 합침
                        table[parentIdx1] = changeStr;// 병합되면 첫번째 좌표에 있는 셀에 값 유지
                        table[parentIdx2] = "";// 병합된 대상은 초기상태로 유지
                    }
                }
                case "UNMERGE" -> {// UNMERGE r c
                    int r = Integer.parseInt(parse[1]);
                    int c = Integer.parseInt(parse[2]);
                    int idx = getTableIdx(r, c);// 현재 좌표 인덱스
                    int root = find(idx);// 병합 셀 확인을 위한 부모 인덱스

                    String temp = table[root];// 병합 셀의 값을 미리 저장
                    table[idx] = temp;// 현재 좌표의 값을 병합 셀의 값으로 변경
                    table[root] = "";// 병합해제를 위해 부모 인덱스 위치의 값을 초기화

                    List<Integer> list = new ArrayList<>();
                    for(int i=0; i<MAX_SIZE; i++){
                        if(root == find(i))// 부모가 같은(같은 병합 그룹) 대상의 인덱스 위치 저장
                            list.add(i);
                    }
                    for(int n : list){// 해당 위치들은 프로그램 초기상태로 변경
                        parent[n] = n;
                    }

                }
                case "PRINT" -> {
                    int r = Integer.parseInt(parse[1]);
                    int c = Integer.parseInt(parse[2]);
                    int root = find(getTableIdx(r, c));// 해당 위치 인덱스의 부모를 찾아
                    result.add(table[root].length() == 0 ? "EMPTY" : table[root]);// 부모 셀의 값 저장
                }
            }
        }
        return result.stream().toArray(String[]::new);
    }
    private int getTableIdx(int r, int c){
        int idx = 50 * (r-1);
        return idx + c;
    }
    private int find(int n){
        if(parent[n] == n){// 부모 인덱스가 자기자신의 인덱스와 같으면 리턴
            return n;
        }
        return find(parent[n]);// 부모 인덱스를 찾기 위해 재귀 탐색
    }
    private void union(int a, int b){
        a = find(a);
        b = find(b);
        if( a == b ) return; // 이미 같은 집합인 경우 합집한 연산할 필요 x
        parent[b] = a;// b의 부모를 a의 부모로 변경
    }
}