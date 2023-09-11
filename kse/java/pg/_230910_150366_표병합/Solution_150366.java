package java.pg._230910_150366_표병합;

import jav.algorithm.D_queue.BreadthFirstSearch;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/150366
//신규_프로그래머스_lv3_150366_표병합
public class Solution_150366 {
    int[] parent;
    String[] board;
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        parent = new int[2501];      // 표의 부모 저장
        board  = new String[2501];   // 표의 값 저장

        init(); //초기화

        for(String command : commands){
            String[] splits = command.split(" ");
            switch (splits[0]){
                case "UPDATE" -> {
                    if(splits.length == 4){ // 특정 좌표에 값 넣는 경우
                        int index = findIndex(Integer.parseInt(splits[1]), Integer.parseInt(splits[2])); //좌표값 찾기
                        int root  = findParent(index);  //좌표값의 부모 찾기
                        board[root] = splits[3];        // 부모의 값을 바꾸면 merge 된 경우 merge 좌표 전체값을 변환 가능
                    }
                    else{ // 특정 값을 전부 변환하는 경우
                        changeValue(splits[1], splits[2]);
                    }
                }
                case "MERGE" -> {   // start 좌표와 end 좌표를 병합
                    int start = findIndex(Integer.parseInt(splits[1]), Integer.parseInt(splits[2]));
                    int end = findIndex(Integer.parseInt(splits[3]), Integer.parseInt(splits[4]));
                    int startRoot = findParent(start);
                    int endRoot = findParent(end);
                    if(startRoot == endRoot) continue; // 같은 셀인 경우 무시

                    // 시작값이 null 인 경우를 제외하고는 startRoot 값을 따른다
                    String rootString = board[startRoot].equals("") ? board[endRoot] : board[startRoot];
                    // 이전값과 상관없이 둘 중 어느걸 골라도 병합된 셀(root)를 찾도록 한다.
                    // 분할 했을 때 실행 초기의 상태로 돌아가기 위해 값을 비워준다.
                    board[startRoot] = "";
                    board[endRoot] = "";
                    union(startRoot, endRoot);
                    board[startRoot] = rootString;

                }
                case "UNMERGE" -> {
                    int index = findIndex(Integer.parseInt(splits[1]), Integer.parseInt(splits[2]));
                    int root  = findParent(index);
                    String rootString = board[root];
                    board[root] = "";
                    board[index] = rootString; //선택한 셀에만 값을 넣어준다.
                    List<Integer> delete = new ArrayList<>();
                    for (int i = 0; i <= 2500; i++) {   // 그외의 셀의 값을 지워준다. = 부모를 본인으로 변경
                        if (findParent(i) == root) delete.add(i);
                    }
                    for  (int temp : delete){
                        parent[temp] = temp;
                    }
                }
                case "PRINT" -> {
                    int index = findIndex(Integer.parseInt(splits[1]), Integer.parseInt(splits[2]));
                    int root  = findParent(index);  //index 좌표의 표가 가르키는 진짜 값을 찾기
                    if(board[root].equals("")) answer.add("EMPTY");
                    else answer.add(board[root]);
                }
            }
        }
        return answer.toArray(new String[0]);
    }

    /** 값 초기화 parent 는 자신의 index, board 는 "" */
    public void init(){
        for (int i = 0; i <= 2500; i++) {
            parent[i] = i;
            board[i] = "";
        }
    }
    /** board 의 좌표값 구하기 */
    public int findIndex(int dy, int dx){
        return (50 * (dy - 1)) + (dx - 1);
    }
    /** 부모 index 반환 */
    public int findParent(int index){
        if(parent[index] == index) return index;
        else return findParent(parent[index]);
    }
    /** union 연산
     *  index2의 부모가 index1의 부모가 되도록 변경
     * */
    public void union(int index1, int index2){
        index1 = findParent(index1);
        index2 = findParent(index2);
        parent[index2] = index1;
    }
    /** 값 before 를 값 after 로 모두 변환 */
    public void changeValue(String before, String after){
        for (int i = 0; i < 2500; i++) {
            if(board[i]!=null && board[i].equals(before)) board[i] = after;
        }
    }
}
