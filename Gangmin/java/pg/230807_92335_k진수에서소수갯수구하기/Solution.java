import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        //진수 변환
        String string = Integer.toString(n, k);
        //0으로 스플릿
        String[] split = string.split("0");
        List<String> list = Arrays.asList(split);
        ArrayList<String> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //1이나 빈 배열은 제거
            if(!list.get(i).equals("1") && !list.get(i).equals(""))
                list2.add(list.get(i));
        }
        for (int i = 0; i < list2.size(); i++) {
            //소수인지 확인 진수 변환 과정에서 int범위를 뛰어 넘을 수 있기 때문에
            //long으로 잡아준다.
            if(isprime(Long.parseLong(list2.get(i)))){
                answer++;
            }
        }
        return answer;
    }
    
   public boolean isprime(long n){
        if(n <= 1) return false;
        else if(n == 2) return true;
        for(int i = 2; i <= Math.sqrt(n); i++)
            if(n % i == 0) 
                return false;
        return true;
    }


}
