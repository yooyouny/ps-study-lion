import java.util.*;
import java.util.stream.Collectors;

class Solution92334 {
    /*
    * id별 신고한 유저들의 목록,
    * id별 신고당한 횟수를 저장할 map,
    * k번 이상 신고당해서 정지당한 유저들의 목록
    * */
    public int[] solution(String[] id_list, String[] report, int k) {

        Map<String, Set<String>> num = new HashMap<>();// key가 신고자로 신고한 유저목록을 저장하는 map 생성
        for(int i=0; i<id_list.length; i++){
            num.put(id_list[i], new HashSet<>());// 중복으로 신고가 가능하므로 set으로 저장
        }

        // id당 신고한 목록 set을 초기화하면서 신고받은 횟수를 저장
        Map<String, Integer> declare = new HashMap<>();// 유저별 신고받은 횟수 저장 할 map
        for(String r : report){
            String[] token = r.split(" ");
            String reporter = token[0];// 신고자
            String target = token[1];// 신고한 유저

            Set<String> set = num.get(reporter);
            if(set.contains(target))// 이미 신고한 전적이 있으면 넘어감
                continue;

            set.add(token[1]);// 신고자 set에 신고한 유저를 put
            declare.put(target, declare.getOrDefault(target, 0) + 1);// 신고받은 유저의 신고 횟수 증가
        }

        Set<String> banned = declare.keySet().stream() // 신고를 k번 이상 당한 정지당할 유저들의 목록
                            .filter(id -> declare.get(id) >= k)
                            .collect(Collectors.toSet());

        return Arrays.stream(id_list)
                        .mapToInt(id -> (int) num.get(id).stream()// 유저별로 있는 신고한 목록에서
                                        .filter(banned::contains) // banned 당한 유저가 있는 경우
                                        .count())// 처리 메일 보낼 횟수 저장
                        .toArray();// 배열로 변환
    }
}