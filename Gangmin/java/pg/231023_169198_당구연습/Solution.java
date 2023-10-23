class Solution {
  static class Pool{
        int startX;
        int startY;
        int width;
        int height;
        int targetX;
        int targetY;

        public int getMinimumDistance(){
            //TODO: target의 점대칭과 start사이의 길이 구하기
            //target의 위 대칭 점과의 길이
            int up = pow(startX - targetX) + pow((height - startY) + (height - targetY));
            //target의 아래 대칭 점과의 길이
            int down = pow(startX - targetX) + pow(startY + targetY);
            //target의 왼쪽 대칭 점과의 길이
            int left = pow(startX + targetX) + pow(startY - targetY);
            //target의 오른쪽 대칭 점과의 길이
            int right = pow((width - startX) + (width - targetX)) + pow(startY - targetY);
            //X좌표가 같은 경우 (target이 위에 있을때)
            if(startX == targetX && targetY > startY){
                return min(down, left, right);
                //(target이 아래에 있을때)
            } else if (startX == targetX && targetY < startY) {
                return min(up, left, right);
            //Y좌표가 같은 경우 (target이 왼쪽에 있을때)
            } else if (startY == targetY && startX > targetX) {
                return min(up, down, right);
                //(target이 오른쪽에 있을때)
            } else if (startY == targetY && startX < targetX) {
                return min(up, down, left);
            }else
                //둘다 다른 경우
                return min(up, down, left, right);
        }

        public int pow(int value){
            return (int)Math.pow(value, 2);
        }

        public int min(int...values){
            int min = Integer.MAX_VALUE;
            for (int value : values) {
                min = Math.min(min, value);
            }
            return min;
        }

        public Pool(int startX, int startY) {
            this.startX = startX;
            this.startY = startY;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setTargetX(int targetX) {
            this.targetX = targetX;
        }

        public void setTargetY(int targetY) {
            this.targetY = targetY;
        }
    }
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        Pool pool = new Pool(startX, startY);
        pool.setHeight(n);
        pool.setWidth(m);
        int index = 0;
        for (int[] ball : balls) {
            int targetX = ball[0];
            int targetY = ball[1];
            pool.setTargetX(targetX);
            pool.setTargetY(targetY);
            answer[index++] = pool.getMinimumDistance();
        }
        return answer;
    }
}
