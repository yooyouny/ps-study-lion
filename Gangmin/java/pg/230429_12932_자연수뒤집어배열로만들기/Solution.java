class Solution {
    public int[] solution(long n) {
        //숫자를 문자열로 변환한다.
        String s = n + "";
        //각 자릿수를 숫자배열로 나누어 넣는다.
        String[] digits = s.split("");
        //정답배열을 숫자 자릿수만큼 생성
        int[] answer = new int[digits.length];
        //거꾸로 넣어야하니 뒷인덱스부터 넣기 위한 인덱스
        int aIndex = digits.length - 1;
        for(int i = 0; i < digits.length; i++){
            //digits배열의 앞 인덱스 부터 읽어 answer배열의 뒷인덱스에 넣는다.
            answer[aIndex] = Integer.parseInt(digits[i]);
            aIndex--;
        }
        return answer;
    }
}
