package java.pg._230606_43236_징검다리;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//https://school.programmers.co.kr/learn/courses/30/lessons/43236
//신규_프로그래머스_lv4_43236_징검다리
public class Solution_43236 {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        //1. rocks 배열을 거리순으로 정렬해준다.
        Arrays.sort(rocks);

        //2. rocks의 거리 차를 배열로 만들어준다.
        long[] diff = new long[rocks.length + 1];

        diff[0] = rocks[0];
        diff[rocks.length] = distance - rocks[rocks.length-1];

        for(int i = 1; i < rocks.length; i++)
            diff[i] = rocks[i] - rocks[i-1];

        //3. 최소의 최댓값을 구해준다.
        long start = 0;         //시작 지점
        long end   = distance;  //도착 지점
        long mid   = 0;         //중간 지점 -> 거리의 최솟값의 최댓값
        long sum   = 0;         //돌 사이의 거리의 합
        long count = 0;         //없앤 돌의 개수
        while(start <= end){
            //3-1. 값 초기화
            mid   = (start + end)/2;
            count = 0;
            sum   = 0;
            //3-2. mid값을 기준으로 돌을 제거해준다.
            for (int i = 0; i < diff.length; i++) {
                sum += diff[i];
                if(sum < mid) count++; else sum = 0;
            }
            //3-3. 최솟값을 구했을 때의 돌의 개수를 비교해 start, end 값을 바꿔준다.
            if(count > n){          //n개 보다 많은 돌을 부쉈다면 최소길이는 sum 보다 작다.
                end = mid - 1;
            }else if(count <= n){   //n개 보다 적거나 같은 돌을 부쉈다면 최소길이는 sum 보다 클 것
                start = mid + 1;
            }
        }

        return (int)start - 1;
    }
    //x개의 바위를 제거했을 때 내가 지정한 길이 mid보다 크면 그 길이는 최솟값이 아니게 된다.
    //distance 25일 때 처음 선택하는 mid 최소 길이는 12
    //앞부분이 12를 넘었다면 최소길이는 뒷 부분의 길이(sum)가 된다.
    //2+9+3  3+4+4  => 14 11
    //내가 n개보다 많은 돌을 부쉈다면 최소길이는 sum보다 작고,
    //내가 n개보다 적거나 같은 돌을 부쉈다면 최소길이는 sum보다 클 것이다.
    //mid 12일 때 부순 돌은 총 4개 end = mid - 1 = 11
    //mid 5일 때  2+9 3+3 4+4 부순돌은 총 3개 end    = mid - 1 = 4
    //mid 2일 때  2 9 3 3 4 4 부순돌은 총 0개 start  = mid + 1 = 3
    //mid 3일 때  2+9 3 3 4 4 부순돌은 총 1개 start  = mid + 1 = 4
    //mid 4일 때  2+9 3+3 4 4 부순돌은 총 2개 answer = 4
}
