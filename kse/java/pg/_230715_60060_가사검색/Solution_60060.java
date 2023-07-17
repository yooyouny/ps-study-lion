package java.pg._230715_60060_가사검색;

import java.util.HashMap;
import java.util.Map;

//https://school.programmers.co.kr/learn/courses/30/lessons/60060
//신규_프로그래머스_lv4_60060_가사검색
//https://codingnojam.tistory.com/40
public class Solution_60060 {
	class Node {
		// 이 Node 를 지나가는 모든 문자열의 수를 저장하는 Map
		// <문자열의 길이, 문자열의 수>
		Map<Integer, Integer> lenMap = new HashMap<>();
		// 자식 노드를 저장하는 Map
		// Map < 해당 문자(word.charAt(i)) , Node >
		Map<Character, Node> childNodes = new HashMap<>();
	}
	//Trie 문자열을 저장하고 효율적으로 탐색하기 위한 트리 형태의 자료구조
	class Trie{
		Node root = new Node();
		void insert(String word) {
			Node node = this.root;
			int len = word.length();
			// 시작 노드에도 지나는 문자열을 저장해준다. ( = queries 의 길이 ) 효율성 3번 : [????????]
			node.lenMap.put(len,node.lenMap.getOrDefault(len,0)+1);

			// computeIfAbsent : 특정 키(Key)들에 항목(Value)을 넣어주고 싶은 경우 사용하는 메서드
			// 키 word.charAt(i) 가 존재할 경우 그 값을, 존쟂하지 않는 경우 new Node()를 반환한다.
			for (int i = 0; i < len; i++) {
				node = node.childNodes.computeIfAbsent(word.charAt(i), c -> new Node());
				//지나는 문자열의 개수를 저장해준다.
				node.lenMap.put(len, node.lenMap.getOrDefault(len,0)+1);
			}
		}

		/** @param word 검사하는 단어
		 *  @return 해당 노드를 지나는 문자열의 개수
		 */
		int search(String word) {
			Node node = this.root;
			int len = word.length();
			for(int i=0; i<len; i++) {
				// front -> aaa??   back  -> ??aaa  : 물음표가 오면 뒷부분 계산할 필요 없으므로
				// return 사전에 넣어둔 이 노드를 지나는 문자열의 수
				if(word.charAt(i) == '?') return node.lenMap.getOrDefault(len,0);

				// node == null : word 를 다 검사하지못했는데 다음 노드가 없다? -> 맞는 문자열이 없다.
				node = node.childNodes.getOrDefault(word.charAt(i), null);
				if(node == null) return 0;
			}
			return node.lenMap.getOrDefault(word.length(),0);
		}
	}
	public int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];
		// front -> aaa??   back  -> ??aaa
		Trie front = new Trie();	//
		Trie back = new Trie();		//문자열을 거꾸로 저장하여 ?? 부분의 검사를 뒤로 미루도록 한다.
		for(String word : words){
			front.insert(word);
			back.insert(reverseWord(word));
		}
		for (int i = 0; i < queries.length; i++) {
			answer[i] = queries[i].charAt(0)=='?' ? back.search(reverseWord(queries[i])) : front.search(queries[i]);
		}
		return answer;
	}

	String reverseWord(String s) {
		return new StringBuilder(s).reverse().toString();
	}
}
