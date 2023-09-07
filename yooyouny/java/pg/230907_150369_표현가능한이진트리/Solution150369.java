class Solution150369 {
    static int result;
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i=0; i<numbers.length; i++){
            String s = Long.toBinaryString(numbers[i]);// 이진수로 변환

            //이진수로 변환한 길이가 완전이진트리 전체노드의 개수와 같아야함
            int treeLen = 0;
            int height = 1;
            while(treeLen < s.length()){
                treeLen = (int)Math.pow(2, height++) - 1; // 2^트리높이 - 1 = 전체노드의 개수
            }
            // 부족한 길이만큼 왼쪽을 더미노드로 채움
            boolean[] node = new boolean[treeLen];// true면 실제노드 false면 더미노드
            int idx = treeLen - s.length();
            for(int j=0; j<s.length(); j++){
                node[idx++] = s.charAt(j) == '1';
            }
            result = 1;// 완전이진트리로 만들 수 있으면 1
            isDummyRoot(node, 0, treeLen - 1, false);// 서브트리의 루트노드가 더미인데 자식이 실제노드인 케이스가 있는지 확인
            answer[i] = result;

        }
        return answer;
    }
    private static void isDummyRoot(boolean[] nodeInfo, int start, int end, boolean child){
        int mid = (start + end) / 2;
        boolean node = nodeInfo[mid];
        if(node && child){
            result = 0;
            return;
        }
        if(start != end){
            isDummyRoot(nodeInfo, start, mid-1, !node);
            isDummyRoot(nodeInfo, mid+1, end, !node);
        }
    }
}