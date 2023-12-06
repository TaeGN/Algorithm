package boj.ok;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_개미굴 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int MAX_K = 15;
		int N = Integer.parseInt(br.readLine());
		
		NodePool nodePool = new NodePool(N * MAX_K);
		Map<String, Node> treeMap = new HashMap<>();
		Node root = nodePool.get(null, null);
		
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			int K = Integer.parseInt(input[0]);
			
			Node parent = null;
			String val = input[1];
			if(treeMap.containsKey(val)) {
				parent = treeMap.get(val);
			} else {
				parent = nodePool.get(root, val);
				treeMap.put(val, parent);
				root.addChild(parent);
			}
			
			for (int j = 2; j <= K; j++) {
				val = input[j];
				Node child = null;
				for (Node node : parent.children) {
					if(node.val.equals(val)) {
						child = node;
						break;
					}
				}
				if(child == null) {
					child = nodePool.get(parent, val);
					parent.addChild(child);
				}
				parent = child;
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Collections.sort(root.children);
		for (Node parent : root.children) {
			dfs(parent, 0, bw);
		}
		
		br.close();
		bw.close();
	}
	
	static void dfs(Node parent, int depth, BufferedWriter bw) throws IOException {
		for (int i = 0; i < depth; i++) {
			bw.append("--");
		}
		bw.append(parent.val).append("\n");
		
		if(parent.children.isEmpty()) return;
		Collections.sort(parent.children);
		for (Node child : parent.children) {
			dfs(child, depth + 1, bw);
		}
	}
	
	static class Node implements Comparable<Node> {
		Node parent;
		String val;
		List<Node> children;
		public Node() {
			this.children = new ArrayList<>();
		}
		public void addChild(Node child) {
			this.children.add(child);
		}
		public void set(Node parent, String val) {
			this.parent = parent;
			this.val = val;
		}
		@Override
		public int compareTo(Node o) {
			return this.val.compareTo(o.val);
		}
	}
	
	static class NodePool {
		Node[] pool;
		int idx;
		public NodePool(int size) {
			this.pool = new Node[size];
			for (int i = 0; i < size; i++) {	
				this.pool[i] = new Node();
			}
			this.idx = -1;
		}
		public Node get(Node parent, String val) {
			Node node = this.pool[++this.idx];
			node.set(parent, val);
			return node;
		}
	}
}
	
