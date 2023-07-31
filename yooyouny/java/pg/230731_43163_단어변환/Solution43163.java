import java.util.*;
class Solution43163 {
    static int answer;
    public int solution(String begin, String target, String[] words) {
        //words에 target이 없는 경우 바로 리턴
        if(Arrays.stream(words).noneMatch(word -> word.equals(target))) return 0;

        boolean[] visited = new boolean[words.length];// 단어 선택유무를 저장할 boolean 배열
        dfs(begin, target, words, visited, 0);

        return answer;
    }
    private void dfs(String begin, String target, String[] words, boolean[] visited, int cnt){
        if(begin.equals(target)){// 찾은 word가 target일 경우 cnt 저장 후 리턴
            answer = cnt;
            return;
        }

        for(int i=0; i<words.length; i++){
            if(visited[i]) continue;// 이전에 선택한 word가 아니면서
            if(!isConvertable(begin, words[i])) continue;// 다른 문자가 1개만 있는 경우

            visited[i] = true;// 해당 단어 선택 표시
            dfs(words[i], target, words, visited, cnt + 1);// 해당 word를 begin으로 설정 후 cnt 증가
            visited[i] = false;// 한가지 케이스가 끝났을 경우 선택한 단어 체크 해제
        }
    }
    private boolean isConvertable(String from, String to){// 두 문자열 중 다른 문자가 1개 있는 경우 true리턴
        int cnt = 0;
        char[] fromArr = from.toCharArray();
        char[] toArr = to.toCharArray();
        for(int i=0; i<fromArr.length; i++){
            if(fromArr[i] != toArr[i]) cnt++;
        }
        return cnt == 1;
    }
}