class Solution178870 {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];// 구간의 시작인덱스, 마지막 인덱스 반환
        int n = sequence.length;
        int len = n;// 최대 구간의 길이는 n
        int left = 0;// left, right 인덱스로 선형으로 정렬된 수의 합 구하기
        int sum = 0;

        for(int right=0; right<n; right++) {
            sum += sequence[right];// right을 증가시켜가며 구간 합 구하기

            while(sum > k) {// 범위를 초과할 경우 left를 증가시켜가며 구간 합 구하기
                sum -= sequence[left++];
            }

            if(sum == k) {// 타겟인 k와 같은 값이면 최소 길이를 업데이트 하면서 인덱스 값 저장
                if(len > right-left) {
                    len = right-left;
                    answer[0] = left;
                    answer[1] = right;
                }
            }
        }

        return answer;
    }
}