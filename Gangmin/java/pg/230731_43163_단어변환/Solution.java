import java.util.*;
class Solution {
    static boolean visited[];
    static int distance[];
    Queue<Integer> queue = new LinkedList<>();

    public int solution(String begin, String target, String[] words){
        int answer = 0;
        visited = new boolean[words.length];
        distance = new int[words.length];
        List<String> list = Arrays.asList(words);
        //단어가 없으면 불가능하기 때문에 0반환
        if(!list.contains(target)){
            return 0;
        }
        for (int i = 0; i < words.length; i++) {
            //1개만 다른지 확인
            if(isSimilar(begin, words[i])){
                queue.offer(i);
                //이동한 거리 저장
                distance[i] = 1;
            }
        }
        while(!queue.isEmpty()){
            Integer nextIndex = queue.poll();
            String nextVisit = words[nextIndex];
            //중복방문 제거
            visited[nextIndex] = true;
            //문자열에 target에 도달하면 answer에 지금까지 온 거리를 저장
            if(nextVisit.equals(target)){
                answer = distance[nextIndex];
            }
            for (int i = 0; i < words.length; i++) {
                if(isSimilar(nextVisit, words[i]) && !visited[i]){
                    queue.offer(i);
                    visited[i] =true;
                    //방문했다면 이전 방문 거리에 +1하면 지금까지 방문한 노드
                    distance[i] = distance[nextIndex] + 1;
                }
            }
        }
        return answer;
    }


    private boolean isSimilar(String start, String to){
        int count = 0;
        for (int i = 0; i < start.length(); i++) {
            if(start.charAt(i) == to.charAt(i)){
                count ++;
            }
        }
        return count == start.length() - 1;
    }

}
