import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private static final String ENTER_FIXTURE = "님이 들어왔습니다.";
    private static final String EXIT_FIXTURE = "님이 나갔습니다.";

    private static final String ENTER_CONDITION = "Enter";
    private static final String EXIT_CONDITION = "Leave";

    Map<String, List<String>> idRecordMap = new HashMap<>();
    List<String[]> totalHistory = new ArrayList<>();
    List<String> tmpAnswer = new ArrayList<>();
    public String[] solution(String[] record) {
        String[] answer = new String[record.length];
        int idx = 0;
        for (String command : record) {
            String[] split = command.split(" ");
            if(split.length > 2){
                List<String> history = idRecordMap.getOrDefault(split[1], new ArrayList<>());
                history.add(split[2]);
                idRecordMap.put(split[1], history);
            }
            totalHistory.add(new String[]{split[1], split[0]});
        }
        for (String[] strings : totalHistory) {
            String lastNick = lastNickName(idRecordMap.get(strings[0]));
            if(strings[1].equals(ENTER_CONDITION)){
                tmpAnswer.add(lastNick + ENTER_FIXTURE);
            } else if (strings[1].equals(EXIT_CONDITION)) {
                tmpAnswer.add(lastNick + EXIT_FIXTURE);
            }
        }
        return tmpAnswer.toArray(new String[tmpAnswer.size()]);
    }


    private String lastNickName(List<String> history){
        return history.get(history.size() - 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] answer = solution.solution(
            new String[] {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
                "Change uid4567 Ryan"});
        System.out.println(Arrays.toString(answer));
    }
}
