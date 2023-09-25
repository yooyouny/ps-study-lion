import java.util.*;
class Solution42885 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);// 최대한 많은 인원을 제한 무게 안에 포함시키기 위해 정렬
        int boat = 0;
        int weight = 0;// 현재 보트안에 있는 사람들의 무게 수
        for(int i = 0; i<people.length; i++){
            weight += people[i];// 보트 탑승
            if(weight == limit){// 무게제한에 해당 될 경우 보트 떠나보내고 무게 리셋
                boat++;
                weight = 0;
            }else if(weight > limit){// 무게제한 이상이면 보트 떠나보내고 마지막 사람만 포함
                boat++;
                weight = people[i];
            }
        }
        if(weight != 0) boat++;// 남아있는 사람이 있으면 보트 사용해야 함
        return boat;
    }
}