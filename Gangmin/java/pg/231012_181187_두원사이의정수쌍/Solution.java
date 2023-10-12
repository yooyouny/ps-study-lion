class Solution {
    //TODO : r2원 안에 있는 모든 정수점에서 r1원안에 있는 모든 정수 점을 빼준다.
    public long solution(int r1, int r2) {
        long answer = 0;
        //TODO: x를 기준으로 r2와 r1의 y좌표에 있는 정수최대값의 차이를 빼면 사이 정수의 갯수가 나온다.
        for (int i = 1; i < r2; i++) {
            //TODO: x가 r1보다 작을때는 사이값을 구해줘야한다. r1이 겹치기 때문에
            if(i < r1){
                answer += getMaxY(r2, i, "r2") - getMaxY(r1, i, "r1");
                //TODO: 반대로 r1 반지름 보다 크면 r2의 y최대값만 구해줘도 된다.
            }else {
                answer += getMaxY(r2, i, "r2");
            }
        }
        //TODO: 1사분면에 대해서만 진행했기 때문에 모든 분면에 대해 연산해준다.
        answer *= 4;
        //TODO: x축과(x = 0) y축 (y = 0)위의 점은 제외 시켰기 때문에 포함 시켜주자
        answer += (r2 - r1 + 1) * 4;
        return answer;
    }

    /**
     * TODO: x를 기준으로 y좌표에 있는 정수최대값을 구한다.
     * @param r
     * @param x
     * @param rName
     * @return
     */
    private int getMaxY(long r, long x, String rName){
        double maxY = Math.sqrt(r * r - x * x);
        int maxYInt = (int)maxY;
        //r1원안에 있는 점중에 x,y축상에 있는 점이 아닌데 원에 걸쳐있는점은 r2 - r1 사이에 있는점으로 취급해서 여기서 빼주면 밖에서는 - 연산이니 때문에
        //포함된 것으로 처리된다.
        if(rName.equals("r1") && maxYInt - maxY == 0){
            return maxYInt - 1;
        }else {
            return maxYInt;
        }
    }
}
