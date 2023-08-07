import java.util.*;

class Solution {
   Map<String, HashSet<String>> reporterMapReported = new HashMap<>();
    Map<String, Integer> reportedMapCount = new HashMap<>();
    Set<String> removeOverlap;

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        //report에 저장된 중복 값을 제거하기 위해 Set
        //중복 요청이 모두 제거됨
        removeOverlap = new HashSet(Arrays.asList(report));
        for (String reportMapping : removeOverlap) {
            String[] tmp = reportMapping.split(" ");
            //아직 tmp[0]의 reporter가 신고하지 않아 set이 할당이 된적이 없다면 Set을 생성
            reporterMapReported.putIfAbsent(tmp[0], new HashSet<String>(){{add(tmp[1]);}});
            //이미 신고해서 가지고 있는 경우 -> set이어서 중복 대입해도 두번 값이 들어가지 않는다.
            reporterMapReported.get(tmp[0]).add(tmp[1]);
            //신고 당한 사람들의 신고 접수 횟수를 기록
            reportedMapCount.put(tmp[1], reportedMapCount.getOrDefault(tmp[1], 0) + 1);
        }
        //이부분이 헷갈려서 시간내에 못풀었습니다.
        for (String reported : reportedMapCount.keySet()) {
            //신고 횟수 비교
            Integer reportedCount = reportedMapCount.get(reported);
            if(reportedCount >= k){
                for (int i = 0; i < id_list.length; i++) {
                    //각각의 신고자들의 신고에 대해 확인해서 제외된 신고자를 신고 했으면 
                    //해당 신고자의 알람횟수를 증가시킨다.
                    if(reporterMapReported.containsKey(id_list[i]) && reporterMapReported.get(id_list[i]).contains(reported)){
                        answer[i]++;
                    }
                }
            }
        }
        return answer;
    }
}
