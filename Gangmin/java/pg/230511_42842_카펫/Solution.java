import java.util.*;
class Solution {
    //노란 공간의 가로 세로 길이를 저장하는 클래스
  static class Divisor{
        int row;
        int col;
        Divisor(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
//약수 후보군을 저장하는 컬렉션
    static Set<Divisor> set = new HashSet<>();
//yellow의 약수를 찾는다.
    static void findCand(int yellow){
        for(int i = 1; i <= yellow / i; i++){
            if(yellow % i == 0){
                set.add(new Divisor(yellow / i, i));
            }
        }
    }
    
    static boolean isValid(Divisor d, int brown){
        int row = d.row;
        int col = d.col;
        //brown의 값을 만족하는 약수집합인지 확인
        if(row * 2 + col * 2 == brown - 4){
            return true;
        }
        return false;
    }
    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        //약수와 그 짝을 찾아 set에 넣는 함수
        findCand(yellow);
        Iterator it = set.iterator();
        while(it.hasNext()){
            Divisor d = (Divisor)it.next();
            //후보군이 적절한지 확인하는 함수
            if(isValid(d, brown)){
                //모서리를 포함하지 않고 계산했기 때문에 2를 더해준다..
                answer[0] = d.row + 2;
                answer[1] = d.col + 2;
            }
        }
        return answer;
    }
}
