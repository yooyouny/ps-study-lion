class Solution {
    static boolean isExists[];
    public int solution(int[] numbers) {
        int answer = 0;
        //있는지 없는지 확인하는 배열
        isExists = new boolean[10];
        for(int i = 0; i < numbers.length; i++){
            //있으면 true
            isExists[numbers[i]] = true;
        }
        
        for(int i = 0; i < isExists.length; i++){
            //없는 것들의 합 반환
            if(!isExists[i])
                answer += i;
        }
        return answer;
    }
}
