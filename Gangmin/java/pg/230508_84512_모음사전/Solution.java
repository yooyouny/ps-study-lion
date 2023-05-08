class Solution {
    //조합을 만들 낱말 사전
    static char[] dict = {'A', 'E', 'I', 'O', 'U'};
    //몇번째 인지 기록
    static int order;
    public static int solution(String word) {
        int answer = 0;
        //string은 delete나 substring해줘야 되니까 char
        char[] cand = new char[5];
        //탐색할 재귀
        recur_proc(word, cand, 0);
        answer = order;
        return answer;
    }
    
    static boolean recur_proc(String word, char[] cand, int strLength){
        //char로 했으니까 비교는 string으로 바꿔서, trim()으로 주변 공백 지우기
        String str = new String(cand).trim();
        //같으면 true
        if(str.equals(word)){
            return true;
        }
        //6자리 되면 false
        if(strLength == 5){
            return false;
        }
        //탐색 반복
        for(int i = 0; i < 5; i++){
            //strLength index에 i를 대입
            cand[strLength] = dict[i];
            order++;
            //재귀 함수들어가서 다섯자리 인덱스를 다 채우거나 word를 찾을때 까지 반복
            if(recur_proc(word, cand, strLength + 1)){
                //찾으면 true 반환하면서 백트래킹으로 탈출
                return true;
            }
            //실패하면 strLength번쨰 글자를 0으로 초기화
            cand[strLength] = 0;
        }
        //반복문이 끝나면 return type이 false니까 false반환해서 계속 탐색
        return false;
    }
}
