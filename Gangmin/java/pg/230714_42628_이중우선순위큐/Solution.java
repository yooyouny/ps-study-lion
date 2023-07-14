import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] operations) {
	    //답을 저장할 배열 -> 만약 비어있다면 이대로 return
        int[] answer = {0,0};
	//최댓값을 저장할 우선순위 큐를 선언한다.
        PriorityQueue<Integer> priorityQueueWithMax = new PriorityQueue<>(Comparator.reverseOrder());
        //최솟값을 저장할 우선순위 큐
	PriorityQueue<Integer> priorityQueueWithMin = new PriorityQueue<>();

	//각 연산을 순회하면서 I가 나오면 삽입
	//D가 나오면 삭제한다.
        for (String operation : operations) {
		//연산자와 값으로 split
            String[] splitOther = operation.split(" ");
//I인 경우 삽입
            if (splitOther[0].equals("I")) {
		    priorityQueueWithMax.add(Integer.parseInt(splitOther[1]));
                priorityQueueWithMin.add(Integer.parseInt(splitOther[1]));
            }
//D이고 1인 경우 max큐에서 최댓값을 확인한 후 삭제
            if (splitOther[0].equals("D")) {
                if (!priorityQueueWithMax.isEmpty()) {
                    if (splitOther[1].equals("1")) {
                        int max = priorityQueueWithMax.peek();
                        priorityQueueWithMax.remove(max);
                        priorityQueueWithMin.remove(max);

			//D이고 -1인경우 min큐에서 최댓값을 확인한 후 삭제
                    } else {
                        int min = priorityQueueWithMin.peek();
                        priorityQueueWithMax.remove(min);
                        priorityQueueWithMin.remove(min);
                    }
                }
            }

        }
	//남아 있는 숫자가 있다면 최대, 최소를 max,min큐에서 꺼내 배열에 담고 return한다.
        if (!priorityQueueWithMax.isEmpty()) {
            answer[0] = priorityQueueWithMax.peek();
            answer[1] = priorityQueueWithMin.peek();

        }
        return answer;
    }
}
