package java.pg._230523_72411_메뉴리뉴얼;

import javax.print.DocFlavor;
import java.util.*;
//신규_프로그래머스_lv2_72411_메뉴리뉴얼
//https://school.programmers.co.kr/learn/courses/30/lessons/72411
public class Solution_72411 {
    /**
     * 1. 이미 같은 코스가 들어있는 경우는 값을 +1
     * 2. 같은 코스가 없는 경우 코스를 추가
     * 3. 주문횟수가 2회 이상이 아닌 경우 map에서 삭제
     */
    public void makeList(HashMap<String,Integer> courseList, ArrayList<String> orderCourse){
        for(String temp : orderCourse){
            if(courseList.containsKey(temp)){
                courseList.put(temp, courseList.get(temp)+1);
            }else{
                courseList.put(temp,1);
            }
        }
        courseList.entrySet().removeIf(entry -> entry.getValue() == 1);
    }

    /**
     *  1. 2의 주문개수 제곱을 구한다.
     *  2. 0~ 구한 값까지의 이진수를 구하고
     *  3. 이를 split으로 배열형태로 저장한다.
     */
    public String[][] findBinaryArray(int orderLength){
        int count = (int)Math.pow(2,orderLength);
        String[][] binary = new String[count][orderLength];
        for (int number = 0; number < count; number++) {
            String temp = Integer.toBinaryString(number);
            temp = "0".repeat(orderLength-temp.length()) + temp;
            binary[number] = temp.split("");
        }
        return binary;
    }

    /**
     * 1. 단품메뉴를 사전에 정렬하여 xy,yx같은 경우가 다르게 취급되지 않도록 한다.
     * 2. 단품 개수만큼의 자릿수를 가진 이진수 조합을 구한다.
     *    ex)단품 코스가 4개인 경우 0~15까지의 조합을 구한다.
     * 2.
     */
    public void Combination(ArrayList<String> orderCourse, String[] orders){
        for (String order : orders) {
            String[] spells = order.split("");
            Arrays.sort(spells);

            String[][] binary = findBinaryArray(spells.length);

            for (String[] strings : binary) {
                StringBuilder temp = new StringBuilder();
                for (int j = 0; j < spells.length; j++) {
                    temp.append(strings[j].equals("1") ? spells[j] : "");
                }
                if (temp.length() > 1) orderCourse.add(temp.toString());
            }
        }

    }

    /**
     * 1. 현재 코스의 최댓값이 나와 같으면 temp list에 추가하고
     * 2. 새로운 최댓값이 나오면 기존에 쌓아둔 값을 clear로 제거한다.
     */
    private ArrayList<String> maxValueCourse(int count, HashMap<String, Integer> courseList) {
        ArrayList<String> temp = new ArrayList<>();
        int max = 0;
        for (Map.Entry<String, Integer> entry : courseList.entrySet()) {
            if(entry.getKey().length() == count){
                if(max < entry.getValue()){         //max값보다 entry의 값이 더 클 때
                    max = entry.getValue();
                    temp.clear();
                    temp.add(entry.getKey());
                }else if(max == entry.getValue()){  //max값과 entry의 값이 같을 때
                    temp.add(entry.getKey());
                }
            }
        }
        return temp;
    }

    /**
     * 1. 나올 수 있는 모든 코스 조합을 찾고
     * 2. 코스 조합을 토대로 만들 수 있는 코스와 주문된 횟수를 정리한다.
     * 3. 코스의 단품 개수와 내가 짠 코스의 단품 개수를 비교하면서
     * 4. 내가 구하는 단품 개수에서 최댓값을 가진 코스들을 구한다.
     * 5. 이를 모아서 사전식으로 정렬해준다.
     * @param orders 손님이 주문한 단품 주문
     * @param course 코스로 만들고 싶은 단품의 개수
     * @return 사전식으로 정렬되었으며, 손님이 가장 많이 주문한 단품을 코스화 한 배열
     */
    public String[] solution(String[] orders, int[] course) {
        String[] answer;
        HashMap<String,Integer> courseList = new HashMap<>();
        ArrayList<String> orderCourse = new ArrayList<>();

        // orders에서 나올 수 있는 모든 코스 조합을 찾아 orderCourse에 넣는다.
        Combination(orderCourse, orders);

        // orderCourse를 토대로 만들 수 있는 코스와 손님이 주문한 횟수를 정리한다.
        makeList(courseList, orderCourse);

        ArrayList<String> ans = new ArrayList<>();
        for(int count : course){
            ArrayList<String> temp = maxValueCourse(count, courseList); //주문 개수 최댓값에 해당하는 코스 구하기
            ans.addAll(temp);
        }

        answer = ans.toArray(new String[0]);    //정답 ArrayList를 배열로 변환
        Arrays.sort(answer);                    //사전식 정렬을 하기 위해 sort
        return answer;
    }


}
