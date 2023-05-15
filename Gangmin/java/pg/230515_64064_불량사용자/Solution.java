import java.util.*;
import java.util.regex.*;
class Solution {
        //전체 경우를 담을 bannedIdSet
    static Set<Set<String>> bannedIdSet = new HashSet<>();

    //불량사용자 목록에서 불량 사용자인지 체크하는 함수
    private static boolean checkCand(String user_id, String banned_id){
        //패턴으로 찾는다.
        Pattern p;
        //정규식으로 치환
        String patternSample = banned_id.replace("*", ".");
        //패턴을 넣고 맞으면 true를 반환한다.
        p = Pattern.compile(patternSample);
        Matcher m = p.matcher(user_id);
        if(m.matches())
            return true;   
        //맞지 않으면 false
        return false;
    }

    private static void generateIdSet(String[] userId, String[] bannedId, Set<String> matchedIdSet, boolean[] checked, int idx){
        //찾고자하는 길이만큼 다 찾으면 bannedIdSet에 넣고 return
        if(idx == bannedId.length){
            //matchedIdSet이 계속 변경되고 있기 떄문에 새 인스턴스 생성해 넣어준다.
            bannedIdSet.add(new HashSet<>(matchedIdSet));
            return;
        }

        for (int i = 0; i < checked.length; i++) {
            //방문 했는지 확인, 형식에 맞는지 확인
           if(!checked[i] && checkCand(userId[i], bannedId[idx])){
            //dfs
                checked[i] = true;
                matchedIdSet.add(userId[i]);
                generateIdSet(userId, bannedId, matchedIdSet, checked, idx + 1);
                checked[i] = false;
                matchedIdSet.remove(userId[i]);
            }
        }
    } 



    
    public static int solution(String[] user_id, String[] banned_id) {
        generateIdSet(user_id, banned_id, new HashSet<>(), new boolean[user_id.length], 0);
        return bannedIdSet.size();
    }
}
