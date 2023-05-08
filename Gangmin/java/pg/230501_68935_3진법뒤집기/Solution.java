class Solution {
   public static int solution(int n) {
       //10진 정수를 3진법으로 거꾸로 담을 문자열
        String tri = "";
        while(n > 0){
            //나머지로 아랫자리 부터 채워넣기
            tri += n % 3 + "";
            n /= 3;
        }
       //3진법으로 분자열 해석후 10진 정수로 대입
        int answer = Integer.valueOf(tri, 3);

        return answer;
    }
}
