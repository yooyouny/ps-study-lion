class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        //number의 길이 - k 개 만큼 고르기
        for (int i = 0; i < number.length() - k; i++) {
            int max = 0;
            //첫번째 가장 큰수로 고른 다음 그 뒤부터 봐야 되니 index부터 시작
            for (int j = index; j <= k + i; j++) {
                //k개 만큼 탐색중에 제일 최댓값을 골라야 그 뒤에 숫자를 골라서 length를 완성 시킬 수 있음
                int cand = number.charAt(j) - '0';
                if(max < cand){
                    max = cand;
                    //최댓값을 고를떄마다 그 뒤에 숫자를 골라야 순서가 보장된다.
                    index = j + 1;
                }
            }
            sb.append(max);
        }
        return sb.toString();
    }
}
