package java.pg._231214_17677_뉴스클러스터링;

import java.util.ArrayList;

//https://school.programmers.co.kr/learn/courses/30/lessons/17677
//신규_프로그래머스_lv2_17677_뉴스클러스터링
public class Solution_17677 {
    public int solution(String str1, String str2) {

        // 두 글자씩 끊어서 다중집합 구하기
        ArrayList<String> wordList1 = createWord(str1);
        ArrayList<String> wordList2 = createWord(str2);

        // 둘 중 하나라도 값이 없을 경우 자카드 유사도 = 1로 판단한다.
        if(wordList1.size() == 0 && wordList2.size() == 0) return 65536;

        double unionLength = 0;
        double interLength = 0;

        // 교집합 개수 구하기
        for (String temp : wordList1){
            if(wordList2.contains(temp)){   // list1, 2 모두 포함된 경우 교집합 개수 + 1
                interLength++;
                wordList2.remove(temp);     // 비교가 끝난 원소는 제거한다.
            }

        }
        // 합집합 개수 구하기
        // 겹치는 원소는 wordlist2에서 제거되었기 때문에
        // 두집합의 원소의 개수의 합이 합집합의 총 개수가 된다.
        unionLength += wordList1.size();
        unionLength += wordList2.size();

        return (int) (( interLength / unionLength ) * 65536);
    }

    // str 의 다중집합을 구하는 메서드
    private ArrayList<String> createWord(String str) {
        char[] charStr = str.toUpperCase().toCharArray();

        ArrayList<String> wordList = new ArrayList<>();

        for (int i = 0; i < charStr.length - 1; i++) {
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < 2; j++) {
                char character = charStr[i + j];
                if(isAlpha(character)) temp.append(character);
            }
            // 알파벳인 경우에만 temp에 저장했기 때문에
            // temp의 길이가 2인 경우 올바른 집합의 원소라고 판단하여 저장한다.
            if(temp.length() == 2) wordList.add(String.valueOf(temp));
        }

        return wordList;
    }

    // 알파벳인지 확인하는 메서드
    private boolean isAlpha(char character) {
        return character >= 65 && character <= 90;
    }

}
