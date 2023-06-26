package java.pg._230625_42892_길찾기게임;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//https://school.programmers.co.kr/learn/courses/30/lessons/42892
//신규_프로그래머스_lv3_길찾기게임
class Node {
	int location; //위치, nodeinfo에서의 x 좌표 값
	int index;    //nodeinfo에서의 index 값
	Node left;
	Node right;
	Node(int location, int index){
		this.location = location;
		this.index = index;
		this.left  = null;
		this.right = null;
	}
}

public class Solution_42892 {
	/**
	 * 1. List<int[]> node 를 만들어 nodeinfo의 정보와 nodeinfo에서의 index를 함께 저장해준다.
	 * 2. node 를 y좌표값에 따라 정렬해준다.
	 *    (가장 앞에 오는 값이 root 노드가 된다.)
	 * 3. root 노드를 만들어 준다.
	 * 4. root 노드를 기준으로 한 이진트리에 값을 넣어준다. insertNode()
	 * 5. 전위, 후위 순회한 값을 answer 에 넣어준다.
	 * @param nodeinfo 노드 정보 [x좌표,y좌표]
	 * @return 전위 순회, 후위 순회 순서 배열
	 */
	public int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][];

		//List<int[]> node 를 만들어 nodeinfo의 정보와 nodeinfo에서의 index를 함께 저장해준다.
		List<int[]> node = new ArrayList();
		for (int i = 0; i < nodeinfo.length; i++){
			node.add(new int[] {nodeinfo[i][1], nodeinfo[i][0], i+1});
		}
		//node를 y 좌표값에 따라 정렬하고 reverse 해준다. (가장 앞에 오는 값이 root 노드가 된다. 노드들의 level에 순서를 맞춰 넣어줄 수 있다.)
		node.sort(Comparator.comparingInt(a -> a[0]));
		Collections.reverse(node);

		//root 노드를 만들어 준다.
		Node root = new Node(node.get(0)[1], node.get(0)[2]);

		for (int i = 1; i < node.size(); i++) {
			Node newNode = new Node(node.get(i)[1], node.get(i)[2]);
			insertNode(root, newNode);
		}

		//전위, 후위 순회한 값을 answer에 넣어준다.
		ArrayList<Integer>  preResult = new ArrayList<>();
		ArrayList<Integer> postResult = new ArrayList<>();
		preOrder(preResult, root);
		postOrder(postResult, root);
		answer[0] = preResult.stream().mapToInt(i -> i).toArray();
		answer[1] = postResult.stream().mapToInt(i -> i).toArray();
		return answer;
	}

	//후위 순회 탐색을 끝낸 후에 result에 넣어준다.
	private void postOrder(ArrayList<Integer> result, Node node) {
		if (node != null) {
			postOrder(result, node.left);
			postOrder(result, node.right);
			result.add(node.index);
		}
	}

	//전위 순회 result에 넣어주고 다음 노드를 탐색해준다.
	private void preOrder(ArrayList<Integer> result, Node node) {
		if (node != null) {
			result.add(node.index);
			preOrder(result, node.left);
			preOrder(result, node.right);
		}
	}

	/**
	 * 1. current != null 마지막 노드가 비어있지 않을 때까지 반복하며 위치에 맞는 말단 노드를 찾는다.
	 * 2. parent 기준으로 node.location 이 크고 작은지 판단하여 node를 넣어준다.
	 * @param root 루트 노드
	 * @param node 입력 노드
	 */
	public void insertNode(Node root, Node node){
		Node current = root;
		Node parent = null;

		//current != null 마지막 노드가 비어있지 않을 때까지 반복하며 위치에 맞는 말단 노드를 찾는다.
		while (current != null) {
			parent = current;
			if (node.location >= current.location) {
				current = current.right;
			} else {
				current = current.left;
			}
		}
		//parent 기준으로 node.location 이 크고 작은지 판단하여 node를 넣어준다.
		if (node.location >= parent.location) {
			parent.right = node;
		} else {
			parent.left = node;
		}
	}


}
