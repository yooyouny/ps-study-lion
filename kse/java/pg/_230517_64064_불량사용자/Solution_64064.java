package java.pg._230517_64064_불량사용자;


import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/64064
public class Solution_64064 {
    /**
     * 1. findBan() 메서드로 불량 사용자를 찾는다.
     * 2. 불량 사용자 리스트 : boolean배열을 String으로 바꿔 set에 저장한다.
     * 3. result의 사이즈를 answer에 넣어 반환한다.
     * @param user_id   당첨 사용자
     * @param banned_id 제재 사용자
     * @return          당첨 사용자 중 제재 사용자가 속한 경우의 수
     */
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        HashSet<String> result = new HashSet<>();
        findBan(result, user_id, new boolean[user_id.length], banned_id, banned_id.length-1);
        answer = result.size();
        return answer;
    }

    /**
     * 1. depth에 해당하는 bannedId를 검사한다.
     * 2. 당첨 사용자가 제재 사용자와 닉네임 길이가 같으면
     * 3. 한글자씩 검사하여 제재 사용자일 경우를 살핀다.
     * 4. 조건 : 제재 사용자의 자리가 *이 아닌데, 둘의 문자가 다른 경우
     * 5. 에 해당하는 사용자를 제외하는 방식으로 진행한다.
     *
     * @param result    만들어진 결과값을 담을 Set<String>
     * @param userId    당첨된 사용자의 배열
     * @param booleans  당첨된 사용자 중 제재대상일 수 있는 사람을 체크한 배열
     * @param bannedId  제재대상의 닉네임
     * @param depth     현재 확인할 제재대상의 배열 index
     */
    private void findBan(HashSet<String> result, String[] userId, boolean[] booleans, String[] bannedId, int depth) {
        if (depth == -1){
            String temp = "";
            for(boolean isused : booleans){
                temp += isused ? "1" : "0";
            }
            result.add(temp);
            return ;
        }
        String[] user;
        String[] ban = bannedId[depth].split("");
        boolean check;
        for (int i = 0; i < userId.length; i++) {
            if(userId[i].length() == ban.length && !booleans[i]){
                user = userId[i].split("");
                check = true;
                for(int j = 0 ;j < user.length; j++){
                    if(!ban[j].equals("*") && !ban[j].equals(user[j])){
                        check = false;
                        break;
                    }
                }
                if(check){
                    booleans[i] = true;
                    findBan(result, userId, booleans, bannedId, depth-1);
                    booleans[i] = false;
                }
            }
        }
    }


}
