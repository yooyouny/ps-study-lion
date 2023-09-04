import java.util.*;
import java.util.stream.Collectors;
class Solution150370 {
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 약관 정보를 map으로 저장
        Map<String, Integer> termMap = new HashMap<>();
        for(String term : terms){
            String[] split = term.split(" ");
            termMap.put(split[0], Integer.parseInt(split[1]) * 28);// key가 약관종류, 유효기관
        }

        List<Integer> answer = new ArrayList<>();
        int todayInt = convertDay(today);

        for(int i=0; i<privacies.length; i++){
            String[] split = privacies[i].split(" ");
            int signDay = convertDay(split[0]);// 개인정보 수집일자
            int termMonth = termMap.get(split[1]);// 약관만료
            if(signDay + termMonth <= todayInt)// 수집일자 + 약관만료일 <= 오늘일자인 대상이 파기일자
                answer.add(i+1);
        }

        return answer.stream()// list to int array
            .mapToInt(Integer::intValue)
            .toArray();
    }

    private int convertDay(String date){
        String[] split = date.split("\\.");
        int day = (28 * 12 * Integer.parseInt(split[0]))
            + (28 * Integer.parseInt(split[1]))
            + Integer.parseInt(split[2]);
        return day;
    }
}