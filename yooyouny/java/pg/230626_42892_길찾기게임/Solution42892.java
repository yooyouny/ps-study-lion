import java.util.*;
class Solution42892 {
	static class Node{
		public int x;
		public int y;
		public int value;
		public Node left;// 이진트리라 left, rigit 노드 저장
		public Node right;

		public Node(int x, int y, int value){
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
	private static void preOrder(Node root, List<Integer> visitList){
		if(root == null)// 끝까지 간 경우 재귀를 빠져나옴
			return;
		//전위순위는 root -> left -> right 순
		visitList.add(root.value);
		preOrder(root.left, visitList);
		preOrder(root.right, visitList);
	}
	private static void inOrder(Node root, List<Integer> visitList){
		if(root == null)
			return;
		//전위순위는 left -> root -> right 순
		inOrder(root.left, visitList);
		visitList.add(root.value);
		inOrder(root.right, visitList);
	}
	private static void postOrder(Node root, List<Integer> visitList){
		if(root == null)// 끝까지 간 경우 재귀를 빠져나옴
			return;
		//후위순위는 left -> right -> root 순
		postOrder(root.left, visitList);
		postOrder(root.right, visitList);
		visitList.add(root.value);
	}

	private static void insert(Node root, Node insertNode){
		if(root.x > insertNode.x){// 현재 노드보다 작으면 왼쪽 서브트리
			if(root.left == null){// 리프노드를 찾으면 추가
				root.left = insertNode;
			}else{// insetNode는 순서대로 들어오기 때문에 이미 채워진 위치면 해당 노드 레벨 아래로 내려가야함
				insert(root.left, insertNode);
			}
		}else{// 현재 노드보다 크면 오른쪽 서브트리
			if(root.right == null){// 리프노드를 찾으면 추가
				root.right = insertNode;
			}else{
				insert(root.right, insertNode);
			}
		}
	}
	public int[][] solution(int[][] nodeinfo) {
		Node[] tree = new Node[nodeinfo.length];

		for(int i=0; i<nodeinfo.length; i++){// input 형태를 노드로 만들어주고
			tree[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i+1);
		}

		Arrays.sort(tree, (a, b) -> b.y - a.y);// y값에 따라 내림차순으로 정렬

		Node root = tree[0];// root노드 설정
		for(int i=1; i<tree.length; i++){// root노드에 노드들을 연결시켜 트리구조를 만들어주는 과정
			insert(root, tree[i]);
		}

		List<Integer> preOrder = new ArrayList<>();
		preOrder(root, preOrder);

		List<Integer> postOrder = new ArrayList<>();
		postOrder(root, postOrder);

		return new int[][]{
			preOrder.stream().mapToInt(Integer::intValue).toArray(),
			postOrder.stream().mapToInt(Integer::intValue).toArray()
		};
	}
}
