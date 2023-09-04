import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    Map<String, Integer> termMap = new HashMap();
    List<Integer> expiryList = new ArrayList<>();
    public int[] solution(String today, String[] terms, String[] privacies) throws ParseException {
        int[] answer = {};
        SimpleDateFormat todayFormat = new SimpleDateFormat("yyyy.MM.dd");
        //Date로 변환
        Date todayDate = todayFormat.parse(today);
        //약관 Map형식으로 넣기
        for (String term : terms) {
            String[] termRecord = term.split(" ");
            termMap.put(termRecord[0], Integer.parseInt(termRecord[1]));
        }
        //유효성 검사 시작
        for (int i = 0; i < privacies.length; i++) {
            //등록 일자와 약관
            String[] privacyDateAndTerm = privacies[i].split(" ");
            String term = privacyDateAndTerm[1];
            String[] date = privacyDateAndTerm[0].split("\\.");
            //유효기간
            Integer termMonth = termMap.get(term);
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            //내년인 경우
            if(month + termMonth > 12){
                year++;
                month += termMonth - 12;
            }else {
                month += termMonth;
            }
            //0일 인 경우
            if(day - 1 == 0) {
                month--;
                if(month == 0){
                    year--;
                    month = 12;
                }
                day = 28;
            }else {
                day--;
            }
            //Date 확인
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1); // 월은 0부터 시작하므로 -1 해줍니다.
            calendar.set(Calendar.DAY_OF_MONTH, day);
            Date expiry = calendar.getTime();
            //만료되었으면 넣기
            if(todayDate.after(expiry)){
                expiryList.add(i + 1);
            }
        }
        return expiryList.stream().mapToInt(Integer::intValue).toArray();
    }
}
