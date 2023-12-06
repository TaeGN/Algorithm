package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_수열과쿼리18 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = readInt(br.readLine());
		Tree tree = new Tree(N);
		
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			tree.add(readInt(input[i]));
		}
		tree.init();
		
		int M = readInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			if(input[0].equals("1")) {
				sb.append(tree.getResult(readInt(input[1]), readInt(input[2]), readInt(input[3]))).append("\n");
			} else {
				tree.update(readInt(input[1]), readInt(input[2]));
			}
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static int readInt(String s) {
		return Integer.parseInt(s);
	}
	
	static class Tree {
		int[] tree, data;
		int dataSize;
		public Tree(int size) {
			int h = (int) Math.ceil(Math.log(size) / Math.log(2));
			int treeSize = (int) Math.pow(2, h + 1);
			this.tree = new int[treeSize];
			this.data = new int[size + 1];
		}
		public void add(int val) {
			this.data[++this.dataSize] = val;
		}
		public void init() {
			this.init(1, 1, this.dataSize);
		}
		public int init(int node, int start, int end) {
			if(start == end) return this.tree[node] = this.data[start];
			return this.tree[node] = getMin(this.init(getLeftNode(node), start, getMid(start, end)),
					this.init(getRightNode(node), getMid(start, end) + 1, end));
		}
		public void update(int idx, int val) {
			this.update(1, 1, this.dataSize, idx, val);
		}
		public int update(int node, int start, int end, int idx, int val) {
			if(idx < start || end < idx) return this.tree[node];
			if(idx == start && idx == end) return this.tree[node] = val;
			return getMin(this.update(getLeftNode(node), start, getMid(start, end), idx, val),
					this.update(getRightNode(node), getMid(start, end) + 1, end, idx, val));
		}
		public int getResult(int left, int right, int k) {
			return this.getResult(1, 1, this.dataSize, left, right, k);
		}
		public int getResult(int node, int start, int end, int left, int right, int k) {
			if(end < left || right < start) return 0;
			if(left <= start && end <= right) {
				if(this.tree[node] > k) return end - start + 1;
				else if(start == end) return 0;
			}
			return getResult(getLeftNode(node), start, getMid(start, end), left, right, k) +
					getResult(getRightNode(node), getMid(start, end) + 1, end, left, right, k);
		}
		private int getMin(int a, int b) {
			return Math.min(a, b);
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
