class Solution12943 {
    public int solution(long num) {// num의 최대값이 7,999,999 이므로 *3 연산 중 21억을 넘을 수 있으므로 long으로 바꿈
        int answer = 0;
        while(true){
            if(num == 1) return answer;// input이 1인 경우 작업을 하지 않아도 되므로 바로 리턴
            if(answer == 500) return -1;// 작업횟수가 500번이 되어도 1이 되지 않으면 -1 리턴

            if(num % 2 == 0){// input이 짝수면
                num /= 2;// 2로 나누고
            }else{// input이 홀수면
                num = num * 3 + 1;// 3을 곱하고 1을 더함
            }
            answer++;// 작업횟수를 증가시켜줌
        }
    }
}
