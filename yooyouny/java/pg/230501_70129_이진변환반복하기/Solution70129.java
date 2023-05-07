class Solution70129 {
    public int[] solution(String s) {
        int[] answer = {0, 0};// 결과배열 0으로 초기화
        while(true){// s가 1이 될 때까지 계속해서 이진변환을 시도하므로 while문
            if(s.equals("1"))// s가 1이면
                break;// 반복문 탈출
            int len = 0;// 0제거 후 s의 길이를 2진법으로 바꿔야하므로 s의 길이를 저장할 변수
            for(int i=0; i<s.length(); i++){// i로 s문자열의 인덱스 접근
                if(s.charAt(i) == '0')
                    answer[1]++;// 0의 개수를 저장
                else
                    len++;// 0이 아닌 경우의 개수와 0을 제거 한 s의 길이와 동일함
            }
            s = Integer.toBinaryString(len);// 2진법으로 표현한 문자열로 바꾸고
            answer[0]++;// 이진변환의 횟수 증가
        }
        return answer;
    }
}
