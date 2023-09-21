import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

    static class Music{
        String category;
        int totalPlayed;

        public String getCategory() {
            return category;
        }

        public int getTotalPlayed() {
            return totalPlayed;
        }

        public Music(String category, int totalPlayed) {
            this.category = category;
            this.totalPlayed = totalPlayed;
        }
    }
    Map<String, PriorityQueue<int[]>> musicMap = new HashMap<>();
    PriorityQueue<Music> popularityList = new PriorityQueue<>((music1, music2) -> music2.getTotalPlayed() - music1.getTotalPlayed());
    int n;
    public int[] solution(String[] genres, int[] plays) {
        n = genres.length;
        int index = 0;
        createMap(genres, plays);
        makePopularityList(genres);
        List<Integer> answerList = new ArrayList<>();
        while (!popularityList.isEmpty()){
            Music music = popularityList.poll();
            PriorityQueue<int[]> queue = musicMap.get(music.getCategory());
            for (int i = 0; i < 2; i++) {
                if(queue.isEmpty()) break;
                answerList.add(queue.poll()[0]);
            }
        }
        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }

    private void createMap(String[] genres, int[] plays){
        for (int i = 0; i < n; i++) {
            PriorityQueue<int[]> pq = musicMap.getOrDefault(genres[i], getPrioritiQueue());
            pq.offer(new int[]{i, plays[i]});
            musicMap.put(genres[i], pq);
        }
    }

    private void makePopularityList(String[] genres){
        Set<String> collect = Arrays.stream(genres).collect(Collectors.toSet());
        for (String genre : collect) {
            PriorityQueue<int[]> queue = musicMap.get(genre);
            int totalPlayed = queue.stream().mapToInt(arr -> arr[1]).sum();
            popularityList.offer(new Music(genre, totalPlayed));
        }
    }


    private static PriorityQueue<int[]> getPrioritiQueue() {
        return new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1])
                return o1[0] - o2[0];
            return o2[1] - o1[1];
        });
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] answer = solution.solution(new String[] {"a","b","c","d","a","d","d","d","a","a","c","c"},
            new int[] {100,300,400,150,100,300,200,600,700,110,900,9000});
        System.out.println(Arrays.toString(answer));
    }
}
