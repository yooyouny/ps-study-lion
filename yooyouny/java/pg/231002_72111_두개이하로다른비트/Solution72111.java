class Solution72111 {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for(int i=0; i<numbers.length; i++){
            long number = numbers[i];
            if(number % 2 == 0){// 짝수는 항상 마지막 비트가 0으로 끝나고 다음 숫자가 마지막 비트가 1로 되니
                answer[i] = number + 1;// 다음 숫자가 비트가 1개 다른 제일 작은 수가 됨
            }else{
                String binaryStr = Long.toString(number, 2);// 정수를 이진수로 변환
                int idx = binaryStr.lastIndexOf("0");// 0이 들어가 있는 이진수면 해당 위치부터 변환을 해야하므로 마지막 0이 등장하는 위치를 구함
                if(idx == -1){// 1로만 이루어진 이진수
                    // 1로만 있는 이진수의 다음 숫자는 자릿수가 하나 늘어나게 됨
                    binaryStr = "10" + binaryStr.substring(1, binaryStr.length());// 가장 앞에 있는 수를 0으로 바꿔야 가장 작은 수이므로
                }else{// 0이 있는 이진수
                    // 마지막 0이 있는 위치를 1로 바꾸고 그 다음 자리중에 가장 앞에 있는 수를 0으로 바꿔야 가장 작은 수이므로
                    binaryStr = binaryStr.substring(0, idx) + "10" + binaryStr.substring(idx+2, binaryStr.length());
                }
                answer[i] = Long.parseLong(binaryStr, 2);// 이진수를 정수로 변환
            }
        }
        return answer;
    }
}