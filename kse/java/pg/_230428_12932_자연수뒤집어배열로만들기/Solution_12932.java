package java.pg._230428_12932_자연수뒤집어배열로만들기;

//https://school.programmers.co.kr/learn/courses/30/lessons/12932
class Solution_12932 {
    public int[] solution(long n) {
        //입력값 n가 몇자리 수인지 구하기
        int length   = (int) (Math.log10(n)+1);
        //answer length 길이로 초기화 해주기
        int[] answer = new int[length];


        for(int i = 0 ; i < length ; i++ ){
            answer[i] = (int)(n%10);        //10으로 나눈 나머지값을 answer에 넣어주기
            n /=10;                         //1턴마다 n/10으로 값 바꿔주기
        }
        return answer;
    }
}