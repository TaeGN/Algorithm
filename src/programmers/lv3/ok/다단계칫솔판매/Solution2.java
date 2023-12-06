package programmers.lv3.ok.다단계칫솔판매;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int N = enroll.length;
		int M = seller.length;
		final String ROOT = "-";
		
		// tree만들기
		Map<String, Node> nodeMap = new HashMap<>(N + 1);
		nodeMap.put(ROOT, new Node(null));
		for (int i = 0; i < N; i++) {
			Node parent = nodeMap.get(referral[i]);
			Node child = new Node(parent);
			parent.addChild(child);
			nodeMap.put(enroll[i], parent);
		}
		
		// seller정보 입력
		for (int i = 0; i < M; i++) {
			Node node = nodeMap.get(seller[i]);
			node.val = amount[i] * 100;
		}
		
		dfs(nodeMap.get(ROOT), nodeMap);
		
		int[] res = new int[N];
		for (int i = 0; i < N; i++) {
			res[i] = nodeMap.get(enroll[i]).val;
			System.out.println(res[i]);
		}
		return res;
	}
	
	private int dfs(Node parent, Map<String, Node> nodeMap) {
		int res = parent.val;
		for (Node child : parent.children) {
			res += dfs(child, nodeMap) / 10;
		}
		parent.val = parent.val * 9 / 10;
		return res;
	}

	class Node {
		Node parent;
		int val;
		List<Node> children;
		public Node(Node parent) {
			this.parent = parent;
			this.children = new ArrayList<>();
		}
		public void addChild(Node child) {
			this.children.add(child);
		}
	}
	
	
}
