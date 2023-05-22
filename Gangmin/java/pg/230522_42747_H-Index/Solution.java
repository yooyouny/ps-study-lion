import java.util.*;

/*
인용값을 정렬하면, 임의의 i번을 인용하는 인덱스의 뒤는 모두 i번 이상 인용하는 값을 가진다.
임의의 index에 h번 이상 인용된 값은 배열의 길이 - index편의 h번 이상 인용된 논문이 있음을 의미
그래서 우리는 citation[index]의 값 (h)가 배열의 길이 - index 값 보다 같거나 큰 순간의 뒤에 남은 인덱스의 수를 구하면 h의 최댓값이다.
*/

class Solution {
   static int ans;
    public static int getHindex(int[] citations){
        for(int i = 0; i < citations.length ; i++){
            //H-index를 찾으면 ans에 답을 넣는다.
            if(citations[i] >= citations.length - i){
                    ans = citations.length - i;
                    break;
            }
        }
        return ans;
    }

    public static int solution(int[] citations) {
        //정렬한 다음 index의 값과 남은 index의 길이가 같은 순간이 최대인 순간
        //정렬하지 않으면 의미가 없음
        Arrays.sort(citations);
        int answer = getHindex(citations);
        return answer;
    }
}
