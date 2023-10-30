public class Solution {
    public long solution(int n, long l, long r) {
        return count(n, r) - count(n, l - 1);
    }

    private long count(int level, long end){
        if(level == 0) return 1;
        long prevBitStringNumber = level - 1;
        //전체를 5등분으로 나누는 수
        long divisor = (long)Math.pow(5.0, prevBitStringNumber);
        //이전 비트열 1의 갯수
        long numberOfOne = (long)Math.pow(4.0, prevBitStringNumber);
        //k번쨰가 속한 구역
        long zone = Math.abs(end / divisor);
        //나누어 떨어지는 경우, 나머지 구간을 구할때 end가 0이 되기 때문에, 나머지 구간을 따로 빼준다.
        if(end % divisor == 0L) zone -= 1;

        //0만 있는 구역
        if(zone == 2) return numberOfOne * zone;
        //가운데 0구간 이후 구간인 경우 -> 구간별로 세고 남은 나머지를 세준다.
        //level이 늘어날 수록 11011의 반복도 늘어남, 그래서 나머지 구간을 다시 5등분해서 1의 갯수를 구해야함
        if(zone > 2) return numberOfOne * (zone - 1) + count(level - 1, end - (divisor * zone));
        //가운데 0구간 이전인 경우
        return numberOfOne * zone + count(level - 1, end - (divisor * zone));
    }
}
