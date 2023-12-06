package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_수열과쿼리16 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Tree tree = new Tree(N);
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			tree.add(Integer.parseInt(input[i]));
		}
		tree.init();
		
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			if(input[0].equals("1")) {
				tree.update(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
			} else {
				sb.append(tree.get(Integer.parseInt(input[1]), Integer.parseInt(input[2]))).append("\n");
			}
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static class Node {
		int idx, val;
		public Node(int idx) {
			this.idx = idx;
		}
		public Node set(int val) {
			this.val = val;
			return this;
		}
	}
	
	static class Tree {
		Node[] tree, data;
		int size, dataIdx;
		public Tree(int size) {
			int h = (int) Math.ceil(Math.log(size) / Math.log(2));
			int treeSize = (int) Math.pow(2, h + 1);
			this.tree = new Node[treeSize];
			this.data = new Node[size + 1];
			for (int i = 1; i <= size; i++) {
				this.data[i] = new Node(i);
			}
			this.size = size;
		}
		public Node getMin(Node a, Node b) {
			if(a == null) return b;
			if(b == null) return a;
			
			if(a.val > b.val) return b;
			else if(a.val < b.val) return a;
			else if(a.idx > b.idx) return b;
			else return a;
		}
		public void init() {
			this.init(1, 1, this.size);
		}
		public void add(int val) {
			this.data[++this.dataIdx].set(val);
		}
		public int get(int left, int right) {
			return get(1, left, right, 1, this.size).idx;
		}
		public void update(int idx, int val) {
			this.update(1, 1, this.size, idx, val);
		}
		public Node init(int node, int start, int end) {
			if(start == end) return this.tree[node] = this.data[start];
			return this.tree[node] = getMin(this.init(getLeftNode(node), start, getMid(start, end)), 
					this.init(getRightNode(node), getMid(start, end) + 1, end));
		}
		public Node get(int node, int left, int right, int start, int end) {
			if(end < left || right < start) return null;
			if(left <= start && end <= right) return this.tree[node];
			return getMin(this.get(getLeftNode(node), left, right, start, getMid(start, end)) , 
					this.get(getRightNode(node), left, right, getMid(start, end) + 1, end));
		}
		public Node update(int node, int start, int end, int idx, int val) {
			if(idx < start || end < idx) return this.tree[node];
			if(start == end) {
				if(start == idx) return this.tree[node].set(val);
				else return this.tree[node];
			}
			return this.tree[node] = getMin(this.update(getLeftNode(node), start, getMid(start, end), idx, val), 
					this.update(getRightNode(node), getMid(start, end) + 1, end, idx, val));
		}
		private int getLeftNode(int node) {
			return node * 2;
		}
		private int getRightNode(int node) {
			return node * 2 + 1;
		}
		private int getMid(int start, int end) {
			return (start + end) / 2;
		}
	}
}
	
