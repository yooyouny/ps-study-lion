import java.util.Arrays;
class Solution {
    //TODO: 2진 트리 형태로 표현할 boolean 배열
    boolean[] tree;
    int result;
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        //TODO: numbers를 순회하면서 숫자가 이진형태로 변환 가능한지 확인
        for (int i = 0; i < numbers.length; i++) {
            //TODO: result를 1로 초기화, 이진탐색에서 실패하면 0으로 갱신
            result = 1;
            long cand = numbers[i];
            //TODO: cand의 2진수 형태의 길이와 만족하는 포화 트리의 길이 구하기 -> 2 ^ n - 1 포화노드의 크기는 항상 이 형태를 가진다.
            //해당 수의 2진 변환시 길이
            int len = (int)Math.floor(Math.log(cand) / Math.log(2)) + 1;
            //len이 가져야할 포화 트리의 길이
            int treeLen = 0;
            //지수
            int exponent = 1;
            //treelen의 길이가 len보다 크거나 같아져야 len을 포함하는 tree를 만들 수 있다.
            while (true){
                treeLen = (int)Math.pow(2, exponent++) - 1;
                if(treeLen >= len) break;
            }
            tree = new boolean[treeLen];
            //TODO: cand의 2진 형태를 tree에 반영해준다.
            int cursor = treeLen - 1;
            while (true){
                //TODO: 2진수로 변환
                long div = cand / 2;
                long mod = cand % 2;
                cand = div;
                tree[cursor--] = (mod == 1);
                if(div == 1){
                    tree[cursor] = true;
                    break;
                } else if (div == 0) break;
            }
            recursion(0, treeLen - 1, false);
            answer[i] = result;
        }
        return answer;
    }

    //TODO: 2진트리를 탐색해서 자식이 1인데, 부모가 0인 경우를 찾는다.
    private void recursion(int start, int end, boolean parent){
        int mid = (start + end)/2;
        boolean current = tree[mid];
        //TODO: 자식이 1인데 부모가 0인경우
        if(parent && current){
            result = 0;
            return;
        }
        //TODO: 마지막 노드가 아닐 경우, 부분 탐색
        if(start != end){
            recursion(start, mid - 1, !current);
            recursion(mid + 1, end, !current);
        }
    }


}
