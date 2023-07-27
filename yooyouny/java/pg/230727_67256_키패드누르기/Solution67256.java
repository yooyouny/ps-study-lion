import java.util.*;
class Solution67256 {
    class Point{// 좌표를 저장할 객체
        int x;
        int y;
        Point (int x, int y){
            this.x = x;
            this.y = y;
        }
        int getX(){
            return x;
        }
        int getY(){
            return y;
        }
    }
    public void initLocation(Point[] location){// 키패드 위치를 좌표로 변환 *이 (0,0)
        location[0] = new Point(1, 0);
        location[1] = new Point(0, 3);
        location[2] = new Point(1, 3);
        location[3] = new Point(2, 3);
        location[4] = new Point(0, 2);
        location[5] = new Point(1, 2);
        location[6] = new Point(2, 2);
        location[7] = new Point(0, 1);
        location[8] = new Point(1, 1);
        location[9] = new Point(2, 1);
    }
    public static double getDistance(Point xy, Point now){// 맨하탄 방식으로 두 좌표 사이의 거리를 구함
        return Math.abs(xy.getX() - now.getX()) + Math.abs(xy.getY() - now.getY());
    }
    public String solution(int[] numbers, String hand) {
        Point[] location = new Point[10];// 키패드 값을 인덱스와 매핑하여 저장
        StringBuilder sb = new StringBuilder();// +연산이 잦으므로 StringBuilder 사용

        initLocation(location);
        Point left = new Point(0, 0);
        Point right = new Point(2, 0);

        for(int num : numbers){
            Point now = location[num];// 이동할 키패드 번호의 좌표
            if(num == 1 || num == 4 || num == 7){
                left = now;// 왼손의 위치를 해당 키패드 번호의 좌표로 변경
                sb.append("L");
            }else if(num == 3 || num == 6 || num == 9){
                right = now;// 오른손의 위치를 해당 키패드 번호의 좌표로 변경
                sb.append("R");
            }else{
                double rightDiff = getDistance(right, now);
                double leftDiff = getDistance(left, now);
                if(rightDiff == leftDiff){// 거리가 같은 경우 왼손잡이, 오른손잡이에 따라 이동
                    if(hand.equals("right")){
                        right = now;
                        sb.append("R");
                    }else{
                        left = now;
                        sb.append("L");
                    }
                }else if(rightDiff < leftDiff){// 오른손이 더 가까운 경우
                    right = now;
                    sb.append("R");
                }else{// 왼손이 더 가까운 경우
                    left = now;
                    sb.append("L");
                }
            }
        }
        return sb.toString();// 문자열 형태로 반환
    }
}