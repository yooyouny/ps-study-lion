package java.pg._231130_17684_압축;

import java.util.ArrayList;

//https://school.programmers.co.kr/learn/courses/30/lessons/17684?language=java
//신규_프로그래머스_lv2_17684_압축
public class Solution_17684 {
    int LENGTH; // msg 의 길이를 담을 변수
    public int[] solution(String msg) {
        ArrayList<Integer> answer = new ArrayList<>();
        ArrayList<String>  dictionary = initDictionary();

        LENGTH = msg.length();

        for (int i = 0; i < LENGTH; i++) {
            StringBuilder word = new StringBuilder(String.valueOf(msg.charAt(i)));
            String nextChar    = "";

            // 마지막 문자인 경우 계산 필요 x 1문자가 전부이기 때문에
            // answer 에 해당 문자의 index 추가 후 종료
            if(isLastChar(i)){
                answer.add(dictionary.indexOf(word.toString()));
            }
            // 그 외의 경우
            else{
                // 다음 문자를 구해준다.
                nextChar = String.valueOf(msg.charAt(i + 1));

                // dictionary 에 이미 word + nextChar 가 있는 경우 dictionary 에 포함되지 않을 때까지
                // 다음 문자를 확인해야 하기 때문에 while
                while (dictionary.contains(word + nextChar)){
                    word.append(nextChar);  // word 에 다음 문자를 추가 해준다.
                    i++;                    // i+1번째 문자가 word 에 포함되어 i+1부터 확인할 필요가 xx -> i + 1

                    // 마지막 문자라서 다음이 없는 경우 break;
                    // 다음 문자가 없다는 의미로 "" 으로 변경 해준다.
                    if (isLastChar(i)) {
                        nextChar = "";
                        break;
                    }

                    nextChar = String.valueOf(msg.charAt(i + 1));
                }

                // 새로운 단어 사전에 추가
                if(!dictionary.contains(word + nextChar)){
                    dictionary.add(word+nextChar);
                }
                // word 를 answer 에 추가
                answer.add(dictionary.indexOf(word.toString()));
            }
        }

        return answer.stream().mapToInt(i -> Integer.parseInt(String.valueOf(i))).toArray();
    }
    // 마지막 단어 인지 검사
    public boolean isLastChar(int index){
        return index == LENGTH - 1;
    }

    // A ~ Z 까지의 문자를 미리 추가
    public ArrayList<String> initDictionary(){
        ArrayList<String> dictionary = new ArrayList<>();
        dictionary.add(""); // index 번호를 맞춰주기 위해 의미 없는 ""를 추가

        char alpha = 'A';
        for (int i = 0; i < 26; i++) {
            dictionary.add(String.valueOf(alpha));
            alpha++;
        }
        return dictionary;
    }
}
