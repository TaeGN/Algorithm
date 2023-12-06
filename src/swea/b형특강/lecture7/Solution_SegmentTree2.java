package swea.b형특강.lecture7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_SegmentTree2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int MAX_N = 100000;
		Tree tree = new Tree(MAX_N);
		int[] data = new int[MAX_N + 1];
		int TC = parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			sb.append("#").append(tc).append(" ");
			
			String[] input = br.readLine().split(" ");
			int N = parseInt(input[0]);
			int Q = parseInt(input[1]);
			
			input = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				data[i + 1] = i % 2 == 0 ? parseInt(input[i]) : -parseInt(input[i]);
			}
			
			for (int i = 1; i <= N; i++) {
				System.out.print(data[i] + " ");
			}
			System.out.println();
			tree.init(data);
			for (int i = 1; i <= N; i++) {
				System.out.print(tree.data[i] + " ");
			}
			System.out.println();
			for (int i = 1; i <= N; i++) {
				System.out.print(tree.sum(i, i) + " ");
			}
			System.out.println();
			System.out.println(tree.sum(1, 5));
			for (int i = 0; i < Q; i++) {
				input = br.readLine().split(" ");
				
				if(input[0].equals("0")) {
					tree.update(parseInt(input[1]) + 1,  i % 2 == 0 ? parseInt(input[2]) : -parseInt(input[2]));
				} else {
					sb.append(tree.sum(parseInt(input[1]) + 1, parseInt(input[2]))).append(" ");
				}
			}
			
			sb.append("\n");
		}
		br.close();
		System.out.println(sb);
	}
	
	static int parseInt(String str) {
		return Integer.parseInt(str);
	}
	
	static class Tree {
		long[] tree;
		int[] data;
		int dataSize;
		
		public Tree(int size) {
			int h = (int) Math.ceil(Math.log(size) / Math.log(2));
			int treeSize = (int) Math.pow(2, h + 1);
			this.tree = new long[treeSize];
		}
		
		public void init(int[] data) {
			this.data = data;
			this.dataSize = data.length;
			this.init(1, 1, this.dataSize - 1, data);
		}
		
		public long init(int node, int left, int right, int[] data) {
			if(left == right) return this.tree[node] = data[left];
			return this.init(getLeftNode(node), left, getMid(left, right), data)
					+ this.init(getRightNode(node), getMid(left, right) + 1, right, data);
		}
		
		public void update(int idx, int val) {
			this.data[idx] = val;
			this.update(1, 1, this.dataSize - 1, idx, val);
		}
		
		public long update(int node, int left, int right, int idx, int val) {
			if(idx < left || idx < node) return 0;
			if(idx == left && idx == right) return this.tree[node] = val;
			return this.update(getLeftNode(node), left, getMid(left, right), idx, val)
					+ this.update(getRightNode(node), getMid(left, right) + 1, right, idx, val);
		}
		
		public long sum(int start, int end) {
			long res = this.sum(1, 1, this.dataSize - 1, start, end);
			return start % 2 == 1 ? res : -res;
		}
		
		public long sum(int node, int left, int right, int start, int end) {
			if(right < start || end < left) return 0;
			if(start <= left && right <= end) return this.tree[node];
			return this.sum(getLeftNode(node), left, getMid(left, right), start, end)
					+ this.sum(getRightNode(node), getMid(left, right) + 1, right, start, end);
		}
		
		private int getLeftNode(int node) {
			return node * 2;
		}
		
		private int getRightNode(int node) {
			return node * 2 + 1;
		}
		
		private int getMid(int left, int right) {
			return (left + right) / 2;
		}
	}
}
