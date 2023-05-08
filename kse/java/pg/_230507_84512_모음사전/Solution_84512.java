package java.pg._230507_84512_모음사전;
//https://school.programmers.co.kr/learn/courses/30/lessons/84512
import java.util.ArrayList;
import java.util.List;

public class Solution_84512 {
    String[] spells = {"A","E","I","O","U"};
    public void creatDictionary(int cnt, String word, ArrayList<String> words){
        words.add(word);
        if(cnt == 0){
            return ;
        }else{
            for(int i = 0 ; i < 5; i++){    //spells를 넣어주며 재귀
                creatDictionary(cnt-1, word+spells[i], words);
            }
        }
    }
    public int solution(String word) {
        //단어들을 담아줄 list
        //List는 참조타입이기 때문에 메소드 내부에서 해당 List 객체를 조작하면 메소드 밖에서도 변경된 내용이 반영
        ArrayList<String> words = new ArrayList<>();
        creatDictionary(5, "", words);
        int answer = words.indexOf(word);
        return answer;
    }
}
