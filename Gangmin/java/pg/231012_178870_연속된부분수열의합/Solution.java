public class Pg178870_Review {
    public int[] solution(int[] sequence, int k) {
        int start = 0;
        int end = 0;
        int sum = 0;
        int range = 0;
        int answerLeft = 0;
        int answerRight = sequence.length - 1;
        //이미 정렬이 되어있기 떄문에 그냥 보면된다.
        for (start = 0; start < sequence.length; start++) {
            //k보다 작을때 까지만 반복
            while (end < sequence.length && sum < k){
                sum += sequence[end++];
            }
            //k와 같은지 확인
            if(sum == k){
                //sum은 start ~ end - 1까지 더한 값
                //따라서 end - 1
                range = end - 1 - start;
                if(answerRight - answerLeft > range){
                    answerRight = end - 1;
                    answerLeft = start;
                }
            }
            //왼쪽 범위 줄이기
            sum -= sequence[start];
        }
        return new int[]{answerLeft, answerRight};
    }
}

