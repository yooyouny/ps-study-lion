package java.pg._230905_150367_표현가능한이진트리;

import java.util.ArrayList;
import java.util.List;

//https://school.programmers.co.kr/learn/courses/30/lessons/150367?language=java
//신규_프로그래머스_lv3_150367_표현가능한이진트리
public class Solution_150367 {
    public int[] solution(long[] numbers) {
        List<Integer> result = new ArrayList<>();
        for (long number : numbers){
            String binaryNumber = Long.toBinaryString(number);      // 2진수 만들기
            int[] treeData = findTreeData(binaryNumber.length());   // 2진수를 트리에 저장했을 때의 데이터 구하기
            char[] binary = findBinary(binaryNumber, treeData[0]);  // 완전이진트리 배열 구하기
            int root = binary.length/2 + binary.length%2 -1;

            searchBinary(binary, treeData[1], root, 1);

            boolean containsChar = new String(binary).chars()       // 남아있는 1이 있는지 세준다.
                    .mapToObj(c -> (char) c)
                    .anyMatch(c -> c == '1');

            result.add( containsChar ? 0 : 1);  // 남은 1이 있으면 표현 불가능
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public void searchBinary(char[] binary, int maxLevel, int node, int level){
        if(maxLevel < level) return;                    // 마지막 라인까지 확인했다면 return
        if(node < 0 || node >= binary.length) return;   // index 범위가 초과되었다면 return
        if(binary[node] == '0') return;                 // 해당 값이 0이면 더 이상 이동 불가 return

        binary[node] = '-'; // 확인했다는 의미로 값을 변경해준다.

        int move = (int) Math.pow(2, maxLevel-level-1); // 자식 노드로 이동할 때의 이동량 계산
        searchBinary(binary, maxLevel, node - move, level+1);
        searchBinary(binary, maxLevel, node + move, level+1);
    }

    /**
     * 이진수가 완전 이진트리인 경우를 구하기 위해
     * 트리의 길이 - 이진수의 길이만큼 0 을 추가해준다.
     */
    public char[] findBinary(String binary, int treeLength){
        int lengthDiff = treeLength - binary.length();
        binary = "0".repeat(lengthDiff)+ binary;
        return binary.toCharArray();
    }

    /**
     * 이진수가 들어갈 포화 이진트리의 길이와 트리 레벨을 구한다.
     */
    public int[] findTreeData(long binaryLength){
        int length = 0;
        int level = 0;
        while (binaryLength > length){
            length += Math.pow(2, level);
            level++;
        }
        return new int[]{length, level};
    }
}
