package java.pg._230509_42842_카펫;
//https://school.programmers.co.kr/learn/courses/30/lessons/42842
import java.util.ArrayList;
import java.util.Arrays;
class Solution_42842 {
    /**
     * input값의 약수를 구해주는 함수
     * @param numb : 약수를 구할 값
     * @return yellow의 내림차순 약수 배열
     */
    public int[] divisor(int numb){
        ArrayList<Integer> result = new ArrayList<>();      //총 개수를 알 수 없기 때문에 약수를 넣어줄 ArrayList 선언
        for(int i = numb; i >= 1; i--){                     //내림차순으로 구하기 위해 i를 numb으로 하여 --로 for문을 돌려준다.
            if(numb % i == 0)                               //numb % i == 0이면 약수이므로
                result.add(i);                              //result에 값을 넣어준다.
        }

        return result.stream().mapToInt(i -> i).toArray();  //result를 int[]로 바꿔 return 해준다.
    }

    /**
     * 문제 풀이
     * 1. 안쪽 노란색 사각형의 모든 가로 길이를 구해 주기 위해 divisor()를 이용하여 yellow의 약수를 구한다.
     *    이 때 for문을 큰값에서 작은값으로 계산하게 하여 가로길이가 큰 순서부터 배열에 들어가게 해준다.
     *
     * 2. yellow의 약수로 for-each문을 돌려준다.
     *
     * 3. yellow의 가로길이와 세로길이의 합에 2배를 해주고 거기에 모서리 타일 개수 4를 더한 값이
     *    brown의 개수와 같으면 answer에 가로세로 길이를 넣어주고 break한다.
     *
     * @param brown  : 바깥쪽 갈색 타일의 개수
     * @param yellow : 안쪽 노란색 타일의 개수
     * @return 바깥쪽 갈색 타일의 가로, 세로 길이 배열
     */
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];                      //가로 세로를 넣어주면 되게 때문에 크기 2로 초기화 해준다.
        int[] yellowRow = divisor(yellow);              //yellowRow 배열에 yellow의 약수를 넣어준다.

        for(int yrow : yellowRow){                      //yellowRow의 값으로 for-each를 돌려준다.

            if(brown == 2*((yellow/yrow) + yrow) +4){   //brown 타일의 개수가 yellow의 바깥 테두리의 개수와 같은지 비교한다.
                answer[0] = yrow+2;
                answer[1] = (yellow/yrow)+2;
                break;
            }

        }
        return answer;
    }
}