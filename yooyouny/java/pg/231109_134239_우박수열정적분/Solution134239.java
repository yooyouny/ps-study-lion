import java.util.*;

class Solution134239 {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        // 콜라츠 구하기
        List<Double> collatzList = new ArrayList<>(); // 구한 콜라츠들 저장
        double num = k;// 콜라츠 결과도 double로 저장해야해서 다른 변수에 저장
        collatzList.add(num);// 시작한 k부터 저장
        while (num > 1) {
            if (num % 2 == 0)
                num /= 2;
            else
                num = num * 3 + 1;
            collatzList.add(num);
        }

        int size = collatzList.size();
        // 넓이 구하기 (정적분) 1부터 size
        double[] area = new double[size];// 정적분 결과도 double로 저장
        for (int i = 1; i < size; i++) {// 0~1의 넓이를 1에 저장
            area[i] = (collatzList.get(i - 1) + collatzList.get(i)) / 2; // 윗변 + 아랫변 * 높이 / 2인 사다리꼴 공식 활용
        }

        // 특정 범위의 넓이를 구해야하므로 구간의 합을 저장
        double[] sum = new double[size];
        sum[1] = area[1];// 0~1 구간의 합은 1까지의 넓이
        for (int i = 2; i < size; i++) {// 0~2 구간의 합은 0~1 구간의 합에 2 넓이 저장
            sum[i] = sum[i-1] + area[i];
        }

        for (int i = 0; i < ranges.length; i++) {
            int a = ranges[i][0];
            int b = (size - 1) + ranges[i][1];// n-b인데 b는 마이너스 범위의 수가 들어옴. n은 콜라츠 변환 횟수이기 때문에 +1을 해줘야함

            if (b > a) {// b가 더 크면 = b 범위까지의 합 - a 범위까지의 합
                answer[i] = Double.parseDouble(String.format("%.1f", sum[b] - sum[a]));// 소수 첫번째 자리까지 형변환 후 double로 저장
            } else if (a > b) {// 문제에서 유효하지 않은 구간은 -1로 정의
                answer[i] = -1.0;
            }
        }

        return answer;
    }
}
