import java.util.*;
class Solution60060 {
    /*
    트라이 자료구조란 문자열을 저장하고 효율적으로 검색할 수 있는 트리 형태의 자료구조
    트리의 노드에 문자 하나씩 저장하면서 현재 문자+길이 조합의 갯수 저장 + 다음 문자 위치 저장을 같이 해야해서 이해하는데 어려웠음
    * */
    class Trie{
        Map<Integer, Integer> lenCntMap = new HashMap<>();// 문자열의 길이를 key, 횟수가 value로 저장
        Trie[] child = new Trie[26];// 다음  노드에 저장하는게 트라이 구조. 알파벳은 26가지

        void insert(String str){
            Trie cur = this;// 현재 메소드를 호출한 인스턴스를 가리키는 객체 (prefix or suffix 객체)
            int len = str.length();
            // 1. 인서트할 문자열의 길이와 개수 저장
            lenCntMap.put(len, lenCntMap.getOrDefault(len, 0) + 1);
            // 2. 인서트할 문자열의 문자를 하나의 트리로 연결
            for(char ch : str.toCharArray()){
                int idx = ch - 'a';// 해당 문자를 26개 인덱스에 맞도록 변경
                if(cur.child[idx] == null){
                    cur.child[idx] = new Trie();
                }
                cur = cur.child[idx]; // 다음 글자 노드를 가리키도록 자식 객체로 변경
                cur.lenCntMap.put(len, cur.lenCntMap.getOrDefault(len, 0) + 1);// 자식의 맵에
            }
        }
        int search(String str, int offset){// str에서 ?가 나오기 전까지 탐색하는 재귀함수
            if(str.charAt(offset) == '?')// 와일드문자가 나오면 더이상 탐색을 할 필요가 없고 해당 노드의 map에 있는 value값이 해당 문자열의 개수이므로 리턴
                return lenCntMap.getOrDefault(str.length(), 0);// 해당 문자열로 저장된 트라이가 없는 경우 0으로 리턴
            int idx = str.charAt(offset) - 'a';// 해당 문자의
            return child[idx] == null ? 0 : child[idx].search(str, offset+1);//offset을 증가시켜가면서 child에 있는 문자를 탐색
        }
    }
    public int[] solution(String[] words, String[] queries) {
        Trie prefix = new Trie();
        Trie suffix = new Trie();// 쿼리가 접미사인 경우 트라이 구조로 찾을 수 없기 때문에 reverse한 구조를 추가로 만듦

        for(String word : words){
            prefix.insert(word);
            suffix.insert(new StringBuilder(word).reverse().toString());
        }

        return Arrays.stream(queries)
                .mapToInt(query -> query.charAt(0) == '?' ?// 첫번째 문자가 와일드 카드면 접미사
                        suffix.search(new StringBuilder(query).reverse().toString(), 0) :
                        prefix.search(query, 0))// 와일드카드가 아니면 접두사
                .toArray();

    }
}