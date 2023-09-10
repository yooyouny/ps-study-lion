import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static class Position{
        int r;
        int c;

        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static String[][] values = new String[51][51];
    static Position[][] merge = new Position[51][51];
    Queue<String> result = new LinkedList<>();
    public String[] solution(String[] commands) {
        for (int i = 1; i <=50 ; i++) {
            for (int j = 1; j <= 50; j++) {
                merge[i][j] = new Position(i, j);
            }
        }
        for (String command : commands) {
            StringTokenizer commandTokens = new StringTokenizer(command);
            String commandType = commandTokens.nextToken();
            if(commandType.equals("UPDATE")){
                if(commandTokens.countTokens() == 3){
                    int r = Integer.parseInt(commandTokens.nextToken());
                    int c = Integer.parseInt(commandTokens.nextToken());
                    String value = commandTokens.nextToken();
                    processUpdateCommand(r,c,value);
                }else {
                    String target = commandTokens.nextToken();
                    String toChange = commandTokens.nextToken();
                    processUpdateCommand(target, toChange);
                }
            } else if (commandType.equals("MERGE")) {
                int r1 = Integer.parseInt(commandTokens.nextToken());
                int c1 = Integer.parseInt(commandTokens.nextToken());
                int r2 = Integer.parseInt(commandTokens.nextToken());
                int c2 = Integer.parseInt(commandTokens.nextToken());
                processMergeCommand(r1,c1,r2,c2);
            } else if (commandType.equals("UNMERGE")) {
                int r = Integer.parseInt(commandTokens.nextToken());
                int c = Integer.parseInt(commandTokens.nextToken());
                processUnmergeCommand(r, c);
            } else if (commandType.equals("PRINT")) {
                int r = Integer.parseInt(commandTokens.nextToken());
                int c = Integer.parseInt(commandTokens.nextToken()); 
                processPrintCommand(r, c);
            }
        }
        String[] answer = new String[result.size()];
        int idx = 0;
        while (! result.isEmpty()){
            answer[idx++] = result.poll();
        }
        return answer;
    }
    private void processUpdateCommand(int r, int c, String value){
        values[merge[r][c].r][merge[r][c].c] = value;
    }

    private void processUpdateCommand(String prev, String change){
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                if(Objects.equals(values[i][j], prev)) values[i][j] = change;
            }
        }
    }

    private void processMergeCommand(int r1, int c1, int r2, int c2){
        int valueRow1 = merge[r1][c1].r;
        int valueCol1 = merge[r1][c1].c;
        int valueRow2 = merge[r2][c2].r;
        int valueCol2 = merge[r2][c2].c;
        for (int i = 1; i <= 50 ; i++) {
            for (int j = 1; j <= 50 ; j++) {
                if(merge[i][j].r == valueRow2 && merge[i][j].c == valueCol2)
                    merge[i][j] = new Position(valueRow1, valueCol1);
            }
        }
        if(valueRow1 != valueRow2 || valueCol1 != valueCol2){
            if(!Objects.equals(values[valueRow1][valueCol1], null) && Objects.equals(values[valueRow2][valueCol2], null)){
                values[valueRow2][valueCol2] = values[valueRow1][valueCol1];
            } else if (Objects.equals(values[valueRow1][valueCol1], null) && !Objects.equals(values[valueRow2][valueCol2], null)) {
                values[valueRow1][valueCol1] = values[valueRow2][valueCol2]; 
            } else if (!Objects.equals(values[valueRow1][valueCol1], null) && !Objects.equals(values[valueRow2][valueCol2], null)) {
                values[valueRow2][valueCol2] = values[valueRow1][valueCol1]; 
            }
        }
    }

    private void processUnmergeCommand(int r, int c){
        int valueRow = merge[r][c].r;
        int valueCol = merge[r][c].c;
        String keepOriginal = values[valueRow][valueCol];
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                if(merge[i][j].r == valueRow && merge[i][j].c == valueCol){
                    merge[i][j] = new Position(i, j);
                    values[i][j] = null;
                }
            }
        }
        values[r][c] = keepOriginal;
    }
    private void processPrintCommand(int r, int c){
        int valueRow = merge[r][c].r;
        int valueCol = merge[r][c].c;
        String res = values[valueRow][valueCol]; 
        if(res == null) result.add("EMPTY");
        else result.add(res);
    }
}

