import java.util.*;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        //단어의 길이별로 Trie를 저장할 배열
        Trie[] tries = new Trie[100001];
        //모든 단어를 확인하며, 단어의 길이 별로 트라이 자료구조를 넣어준다.
        for (String word : words) {  
            int len = word.length();
            //해당 길이가 null이면 생성해줌
            if (tries[len] == null) tries[len] = new Trie();
            //null이 아니라면 앞쪽부터 순서대로 넣은 트라이와, 뒷쪽부터 순서대로 넣은 트라이를 해당
            //단어의 길이를 인덱스로 가지는 트라이에서 전개한다.
            tries[len].insert(word);
        }

        //쿼리마다 확인가능한 가사의 갯수를 정답으로 적어줘야하기 때문에 query의 길이로 배열 생성
        int[] answer = new int[queries.length];
        
        //길이를 인덱스로 가지는 트라이 배열 확인하면서, 없으면 애초에 해당 길이의 문자열 자체가
        //words 중에 없었다는거니까 0반환하고 패스
        for (int i = 0; i < queries.length; i++) {
            int len = queries[i].length();
            if (tries[len] == null) answer[i] = 0;
            //근데 있다면?
            //query의 ?부분을 제외한 부분을 만족하는 문자를 찾는다.
            //자식노드까지 도달한 문자열들의 갯수인 node.count를 반환해서 갯수를 저장해준다.
            else answer[i] = tries[len].getCount(queries[i]);
        }
        return answer;
    }
}

class Trie {
    Node front;
    Node back;

    Trie() {
        this.front = new Node();
        this.back = new Node();
    }

    public void insert(String word) {
        //순서대로 트라이를 전개
        insertFront(word);
        //역순으로 트라이 전개
        insertBack(word);
    }

    private void insertFront(String word) {
        //순서대로 전개하는 경우 공통으로 사용하는 Node
        Node node = front;
        for (int i = 0; i < word.length(); i++) {
            //해당 길이에서 시작하는 트라이의 정순인 첫번째 노드의 count필드를 증가시켜준다.
            //이게 문자열의 root노드 -> 자식노드로 타고 내려가면서 count를 1로 갱신한다.
            //나중에 중복된 자식노드를 타고 내려가는 문자열이 나오면 count가 증가한다.
            node.count++;
            //children Map에서 word의 i번째 인덱스의 문자를 Key로 가지는 value가 없다면?
            //새로운 노드를 만들어 트라이를 전개한다.
            node = node.children.computeIfAbsent(word.charAt(i), c -> new Node());
            //만약 그 문자를 key로 가지는 node가 있다면, 해당 node로 이동한다.        
        }
        //이런 방식으로 계속해서 트라이를 문자가 끝날때 까지 전개시킨다.
    }

    //정순으로 전개하는 트라이를 역순으로 뒤집어 전개
    private void insertBack(String word) {
        Node node = back;
        for (int i = word.length() - 1; i >= 0; i--) {
            node.count++;
            node = node.children.computeIfAbsent(word.charAt(i), c -> new Node());
        }
    }

    //해당 자식노드까지 만족시킨 즉 패턴을 만족시킨 문자열의 갯수를 반환
    public int getCount(String query) {
        //만약에 쿼리의 첫번째가 ?로시작하면, 뒤에서 부터 일치하는지 확인해야한다.
        if (query.charAt(0) == '?') return getCountFromBack(query);
        //반대는 앞에서부터 일치하는지
        else return getCountFromFront(query);
    }

    //역순의 반대
    private int getCountFromFront(String query) {
        Node node = front;
        for (int i = 0; i < query.length(); i++) {
            if (query.charAt(i) == '?') break;
            if (!node.children.containsKey(query.charAt(i))) return 0;
            node = node.children.get(query.charAt(i));
        }
        return node.count;
    }

    private int getCountFromBack(String query) {
        //역순으로 구성되는 트라이의 루트 노드
        Node node = back;
        for (int i = query.length() - 1; i >= 0; i--) {
            //?를 만날때 까지만 문자와 같은지 확인해준다., 길이는 트라이 배열에서 정해졌기 때문에 
            //비교할 필요 없다.
            if (query.charAt(i) == '?') break;
            //역순으로 비교 시작 만약에 key를 가지고 있지 않으면 그냥 해당 쿼리를 만족하는 문자열이 없는거기 때문에 0반환
            if (!node.children.containsKey(query.charAt(i))) return 0;
            //i번째 문자를 Key로 가지는 값이 있다면 Value인 노드로 트라이를 타고 내려간다.
            node = node.children.get(query.charAt(i));
            //?를 만나는 순간 탈출
        }
        //만약 만족못했다면 0, 만족했다면 word를 insert할때 각각의 문자열에대해 트라이를 
        //전개하는 과정에서 count를 증가시켜줬음 해당 자식노드까지 온 문자열들은 길이비교는 이미 
        //해줬기 떄문에 패턴을 만족하는 문자열의 갯수를 count가 가지고 있다.
        return node.count;
    }
}

class Node {
    Map<Character, Node> children;
    int count;

    Node(){
        this.children = new HashMap<>();
        this.count = 0;
    }
}
