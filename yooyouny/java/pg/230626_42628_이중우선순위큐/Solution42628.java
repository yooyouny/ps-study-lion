import java.util.*;
class Solution42628 {
    public int[] solution(String[] operations) {
        int[] answer = {};
        PriorityQueue<Integer> ascending = new PriorityQueue<>();// 오름차순으로 정렬될 큐
        PriorityQueue<Integer> descending = new PriorityQueue<>((a, b) -> b - a);// 내림차순으로 정렬될 큐
        int size = 0; // 큐가 비워질 상황을 체크하기 위한 변수 할당

        for(int i=0; i<operations.length; i++){
            if(operations[i].charAt(0) == 'I'){// 입력일 경우 큐 2개에 모두 삽입
                int num = Integer.parseInt(operations[i].split("I ")[1]);
                ascending.offer(num);
                descending.offer(num);
                size++;// 입력 연산이면 증가
            }else{
                if(!descending.isEmpty() && operations[i].equals("D 1")){// 최댓값 삭제 일 경우 내림차순 큐에서만 삭제
                    descending.poll();
                    size--;// 삭제 연산이면 감소
                }
                else if(!ascending.isEmpty() && operations[i].equals("D -1")){// 최솟값 삭제 일 경우 오름차순 큐에서만 삭제
                    ascending.poll();
                    size--;// 삭제 연산이면 감소

                }
                if(size == 0){// 입력된 모든 숫자가 삭제되었을 때 or 하나의 큐가 모두 비어있는 경우에는 클리어
                    /**
                     *  1 2 3 4
                     *  4 3 2 1
                     *  3 4
                     *  2 1
                     */
                    descending.clear();
                    ascending.clear();
                }
            }
        }
        if(descending.isEmpty() && ascending.isEmpty())// 비어있을 경우는 [0, 0]으로 리턴
            return new int[]{0, 0};

        return new int[]{descending.peek(), ascending.peek()};// 최댓값, 최솟값 peek해서 리턴
    }
}
