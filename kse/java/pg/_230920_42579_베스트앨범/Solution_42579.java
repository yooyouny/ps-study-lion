package java.pg._230920_42579_베스트앨범;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/42579
//신규_프로그래머스_lv3_42579_베스트앨범
public class Solution_42579 {
    //장르별 개수
    class Info{
        String name;
        int allCount;
        List<Genre> playlist = new LinkedList<>();

        public Info(String key, Integer value) {
            this.name = key;
            this.allCount = value;
        }

        public int getAllCount() {
            return allCount;
        }
    }
    //각 곡의 번호와 재생 수
    class Genre{
        int number;
        int plays;

        public Genre(int number, int plays) {
            this.number = number;
            this.plays = plays;
        }

        public int getNumber() {
            return number;
        }

        public int getPlays() {
            return plays;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();   //정답 List
        List<Info> infoList = new LinkedList<>();   //정보 List

        //각 장르별 총 재생수를 계산한다.
        Map<String, Integer> playsByGenre = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            playsByGenre.put(genres[i], playsByGenre.getOrDefault(genres[i], 0)+plays[i]);
        }
        //각 장르의 정보를 info class 에 넣어준다.
        for (Map.Entry<String, Integer> entry : playsByGenre.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            infoList.add(new Info(key, value));
        }
        //infoList 를 내림차순으로 정렬해준다
        infoList.sort(Comparator.comparing(Info::getAllCount).reversed());
        //총 재생수의 순서대로 반복문을 돌리며
        for (Info info : infoList){
            // genres 에서 같은 장르를 찾아서 info 의 playlist 에 넣어준다.
            for (int i = 0; i < genres.length; i++) {
                if(info.name.equals(genres[i])) info.playlist.add(new Genre(i, plays[i]));
            }
            // playlist 를 play 내림차순 -> number 오름차순으로 정렬해준다.
            info.playlist.sort(
                    Comparator.comparing(Genre::getPlays, Comparator.reverseOrder())
                            .thenComparing(Genre::getNumber)
            );
            // answer에 최대 2개까지 넣어준다.
            int count = 0;
            for (Genre genre : info.playlist){
                if(count >= 2) break;
                answer.add(genre.number);
                count++;
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
