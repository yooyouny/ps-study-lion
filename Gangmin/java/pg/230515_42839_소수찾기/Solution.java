import java.util.*;
class Solution {
      /*
     * 완전탐색 문제 - 중복을 허용하지 않는 순열
     * 접근 방법 : String을 자른뒤 한개의 문자를 기준으로 dfs
     * 필요한 기능
     * 1. String을 자르는 기능
     * 2. 문자열을 이어붙여 탐색하는 재귀함수 - 탈출조건: 해당 자릿수일때
     * 3. 확인한 소수를 저장할 Set
     * 4. 소수임을 확인하는 함수
     */

    static boolean[] isVisited;
    //방문 확인할 배열
    static HashSet set = new HashSet();
    //소수저장
    
    //소수인지 확인
    public static boolean isPrime(long cand){
        if(cand == 1 || cand == 0)
            return false;
        for (int i = 2; i <= cand / i; i++) {
            if(cand % i == 0){
                return false;
            }
        }
        return true;
    }
    public static void recurProc(String cand, String[] choices, int depth){
        if(cand.length() == depth){
            set.add(Long.parseLong(cand));
            return;
        }
        //dfs
        for (int i = 0; i < choices.length; i++) {
            if(!isVisited[i]){
                cand += choices[i];
                isVisited[i] = true;
                recurProc(cand, choices, depth);
                isVisited[i] = false;
                //뒤에 잘라서 초기화
                cand = cand.substring(0, cand.length() - 1);
            }
        }
    }


    public static int solution(String numbers) {
        int answer = 0;
        String[] choices = numbers.split("");
        isVisited = new boolean[choices.length];
        //각 자릿수에 가능한 경우의 수 확인
        for(int depth = 0; depth < choices.length; depth++){
            recurProc("", choices, depth + 1);
        }
        Iterator it = set.iterator();
        while(it.hasNext()){
            //소수인지 확인
            if(isPrime((Long)it.next())){
                answer++;
            }
        }
        return answer;
    }
}
