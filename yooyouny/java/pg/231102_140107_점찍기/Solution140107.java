class Solution140107 {
    // d^2 >= x^2 + y^2 원의방정식 활용
    // y = Math.sqrt(d * d - x * x) 양의 방정식
    public long solution(long k, long d) {// int * int = long이 되려면 int도 long으로 바꿔야
        long answer = 0;
        for(long x=0; x<=d; x+=k){// k간격 만큼 증가할때 해당하는 y의 수 구하기
            long y = (long) Math.sqrt(d * d - x * x);
            answer += y/k + 1;// k간격에 해당하는 x좌표 격자점의 개수 소수점 자리포함 하기 위해 + 1
        }
        return answer;
    }
}