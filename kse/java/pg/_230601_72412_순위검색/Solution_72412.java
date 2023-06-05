package java.pg._230601_72412_순위검색;

import java.util.*;
//신규_프로그래머스_lv2_72412_순위검색
//https://school.programmers.co.kr/learn/courses/30/lessons/72412
public class Solution_72412 {
    /**
     * 1. info의 모든 경우의 수와 score를 정리하여 Map 변수에 저장한다.
     * 2. 각 키에 속한 점수 리스트를 정렬한다.
     * 3. 쿼리에 맞는 키를 찾는다
     * 4. 키가 있으면, 이분탐색을 통해 몇 명이 정답자에 속하는지 계산하여 출력한다.
     * 5. 키가 없으면, answer[i] = 0;
     */
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        // info의 모든 경우의 수와 score를 담을 변수
        Map<String, List<Integer>> infoMap = new HashMap<>();

        // info의 모든 경우의 수와 score를 정리
        for (String temp : info)
            processInfo(infoMap, temp.split(" "), 0, "");

        //각 키에 속한 점수 리스트를 정렬
        for (Map.Entry<String, List<Integer>> entry : infoMap.entrySet())
            Collections.sort(entry.getValue());

        //쿼리에 맞는 키를 찾는다
        for (int i = 0; i < query.length; i++) {
            String[] qtemp = query[i].replaceAll(" and ", "").split(" ");
        //키가 있으면, 이분탐색을 통해 몇 명이 정답자에 속하는지 계산하여 출력
            if(infoMap.containsKey(qtemp[0]))
                answer[i] = search(infoMap, qtemp[0],Integer.parseInt(qtemp[1]));

        }
        return answer;
    }

    //몇 명이 정답자에 속하는지 계산
    //정렬된 리스트에서 기준 스코어보다 작은 경우 중 가장 적은 점수의 index를 구해 계산하는 방법
    public int search(Map<String, List<Integer>> infoMap, String query, int score){
        List<Integer> slist = infoMap.get(query);
        int start = 0;
        int end   = slist.size()-1;
        int mid;
        while(start <= end){
            mid = (start + end) / 2;
            if(score > slist.get(mid)) start = mid + 1;
            else end = mid - 1;
        }
        return slist.size() - start;
    }
    //info의 모든 경우의 수를 담아 hashMap에 담는 함수
    public void processInfo(Map<String, List<Integer>> infoMap, String[] iArr, int depth, String temp) {
        if (depth == 4) {
            //같은 키가 있으면 그 키의 리스트에 값을 추가해주고
            if (infoMap.containsKey(temp)) {
                infoMap.get(temp).add(Integer.parseInt(iArr[4]));
            }
            //없으면 새로 만들어 준다.
            else{
                ArrayList<Integer> al = new ArrayList<>();
                al.add(Integer.parseInt(iArr[4]));
                infoMap.put(temp, al);
            }
            return;
        }
        //정해진 값이 없는 경우 + 자신이 가진 값과 같은 경우를 계산해준다.
        processInfo(infoMap, iArr, depth+1, temp + "-");
        processInfo(infoMap, iArr, depth+1, temp + iArr[depth]);
    }
}
