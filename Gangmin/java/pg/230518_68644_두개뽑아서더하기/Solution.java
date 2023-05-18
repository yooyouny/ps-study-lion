import java.util.*;
class Solution {
    //더해서 중복된 값이 들어가면 안된다.
 static Set<Integer> set = new HashSet<>();
    public static int[] solution(int[] numbers) {
        //i와 j가 같은 경우는 제외하고 더해서 넣어준다.
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if(i == j)
                    continue;
                set.add(numbers[i] + numbers[j]);
            }
        }
        //set의 크기 만큼 배열 크기 생성
        int[] answer = new int[set.size()];
        int i = 0;
        //구해진 값대입
        Iterator it = set.iterator();
        while(it.hasNext()){
            answer[i] = (Integer)it.next();
            i++;
        }
        //정렬
        Arrays.sort(answer);
        return answer;
    }
}
