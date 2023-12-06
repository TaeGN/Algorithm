package swea.b형특강.lecture2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution_공통조상 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		final int MAX_V = 10000;
		Map<Integer, Node> nodeMap = new HashMap<>(MAX_V);
		Set<Integer> findSet = new HashSet<>(MAX_V);
		NodePool nodePool = new NodePool(MAX_V);
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// init
			nodePool.init();
			nodeMap.clear();
			findSet.clear();
			nodeMap.put(1, nodePool.get(1, null));
			
			String[] input = br.readLine().split(" ");
			int V = Integer.parseInt(input[0]);
			int E = Integer.parseInt(input[1]);
			int A = Integer.parseInt(input[2]);
			int B = Integer.parseInt(input[3]);
			
			input = br.readLine().split(" ");
			for (int i = 0; i < E; i++) {
				int parent = Integer.parseInt(input[i * 2]);
				int child = Integer.parseInt(input[i * 2 + 1]);
				
				Node parentNode = null;
				// parentNode가 존재하지 않으면 새로 만들기
				if(!nodeMap.containsKey(parent)) {
					parentNode = nodePool.get(parent);
					nodeMap.put(parent, parentNode);
				} else {	// parentNode가 존재하면 map에서 가져오기
					parentNode = nodeMap.get(parent);
				}
				
				Node childNode = null;
				// childNode가 존재하지 않으면 새로 만들기
				if(!nodeMap.containsKey(child)) {
					childNode = nodePool.get(child, parentNode);
					nodeMap.put(child, childNode);
				} else {	// childNode가 존재하면 map에서 가져오기
					childNode = nodeMap.get(child);
				}
				// 부모 자식 관계 등록
				childNode.setParent(parentNode);
				parentNode.setChild(childNode);
			}
			
			Node aNode = nodeMap.get(A);
			Node bNode = nodeMap.get(B);
			
			while(aNode != null) {
				findSet.add(aNode.val);
				aNode = aNode.parent;
			}
			
			while(!findSet.contains(bNode.val)) {
				bNode = bNode.parent;
			}
			
			sb.append(bNode.val).append(" ").append(subTreeSize(bNode)).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static int subTreeSize(Node node) {
		int res = 1;
		if(node.left != null) res += subTreeSize(node.left);
		if(node.right != null) res += subTreeSize(node.right);
		return res;
	}
	
	static class Node {
		int val;
		Node parent, left, right;
		public Node() {};
		public void setParent(Node parentNode) {
			this.parent = parentNode;
		}
		public void setChild(Node childNode) {
			if(this.left == null) this.left = childNode;
			else this.right = childNode;
		}
		public void set(int val, Node parent) {
			this.val = val;
			this.parent = parent;
		}
		public void init() {
			this.left = this.right = null;
		}
		public String toString() {return (parent == null ? 1 : parent.val) + " - " + this.val;}
	}
	
	static class NodePool {
		Node[] pool;
		int idx;
		public NodePool(int size) {
			this.pool = new Node[size];
			for (int i = 0; i < size; i++) {
				this.pool[i] = new Node();
			}
			this.init();
		}
		public void init() {
			this.idx = -1;
		}
		public Node get(int val, Node parent) {
			Node node = this.pool[++idx];
			node.set(val, parent);
			node.init();
			return node;
		}
		public Node get(int val) {
			return this.get(val, null);
		}
	}
}
