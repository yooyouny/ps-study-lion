import java.util.*;
class Solution {
    //객체를 담을 컬렉션
   static ArrayList <diskStickPair> al = new ArrayList<>();
    //원판과 막대기를 멤버로 가지는 객체
    static class diskStickPair{
        int disk;
        int stick;
        public diskStickPair(int disk, int stick){
            this.disk = disk;
            this.stick = stick;
        }
    }
    
    public static int[][] solution(int n) {
        //하노이탑 오른쪽, 중간, 왼쪽 스틱, n번째 원판, 원판갯수
        hanoiRecur(1, 2, 3, 1, n);
        //옮긴 횟수가 al에 쌓인만큼 배열 생성
        int[][] answer = new int[al.size()][2];
        //answer에 넣어주는 반복문
        for(int i = 0; i < al.size(); i++){
            diskStickPair dsp = al.get(i);
            answer[i][0] = dsp.disk;
            answer[i][1] = dsp.stick;
        }
        return answer;
    }
    //원판n개를 from에서 tmp를 사용해 to로 옮긴다.
    static void hanoiRecur(int from, int tmp, int to, int disk, int n){
        //마지막 남은 원판을 왼쪽 원판으로 옮긴다.
        if(disk == n){
            al.add(new diskStickPair(from, to));
            return;
        }
       //원판 n - 1개를 from에서 to를 사용해 tmp로 옮긴다.
        hanoiRecur(from, to, tmp, disk + 1, n);
        //n번쨰 원판을 from에서 to로 옮긴다.
        al.add(new diskStickPair(from, to));
        //원판 n - 1개를 tmp에서 from을 사용해 to로 옮긴다.
        hanoiRecur(tmp, from, to, disk + 1, n);
    
    }
}
