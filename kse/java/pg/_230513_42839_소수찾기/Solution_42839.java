package java.pg._230513_42839_소수찾기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

//https://school.programmers.co.kr/learn/courses/30/lessons/42839
public class Solution_42839 {
    /**
     * 1. splits의 문자중 사용되지 않은 문자를 temp에 넣어 재귀를 시켜준다.
     * 2. 한 번 돌릴 때마다 result에 numb를 저쟁해주고,
     * 3. depth가 0이되어 더이상 값을 넣을 수 없을 때 result에 numb를 저장해 준다.
     * @param splits    숫자를 잘라낸 문자 배열
     * @param used      splits의 값이 사용된 문자인지 확인하기 위한 배열
     * @param depth     몇번 째 계산인지 확인하는 변수
     * @param numb      구하고 있는 문자열을 저장하는 변수
     * @param result    구한 문자열 numb를 저장해줄 HastSet
     */
    public void combination(String[] splits, boolean[] used, int depth, String numb, HashSet<Integer> result){
        if(depth == 0){
            result.add(Integer.parseInt(numb));
            return;
        }
        String temp;
        for(int i = 0 ; i < splits.length ; i++){
            if(!used[i]){
                used[i] = true;
                temp = numb+splits[i];
                result.add(Integer.parseInt(temp));
                combination(splits, used, depth-1, temp, result);
                used[i] = false;
            }

        }
    }

    /**
     * 소수 만들기
     * 1. numb가 1 이하인 경우 소수 x
     * 2. 2~n/2까지의 숫자 중 나눴을 때 나머지가 0인 수가 있는 경우 소수 x
     * 3. 위의 두가지 경우를 제외한 수를 소수로 판단하여 return true 해준다.
     * @param numb
     * @return
     */
    public boolean isPrime(int numb){
        boolean check = true;
        if(numb<=1) check = false;
        for(int i = 2 ; i <= numb/2 ; i++){
            if(numb%i==0){
                return false;
            }
        }
        return check;
    }

    /**
     * 1. number로 구할 수 있는 숫자 조합을 구한다
     * 2. 숫자조합을 반복문으로 돌려 소수인 경우 answer를 1씩 더해준다.
     * @param numbers
     * @return
     */
    public int solution(String numbers) {
        int answer = 0;

        HashSet<Integer> result = new HashSet<>();
        combination(numbers.split(""), new boolean[numbers.length()], numbers.length(), "", result);
        for (int element : result) {
            if(isPrime(element)){
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution_42839 solution42839 = new Solution_42839();
        int num = solution42839.solution("17");
    }
}
