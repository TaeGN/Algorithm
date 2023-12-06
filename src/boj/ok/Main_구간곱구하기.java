package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_구간곱구하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int K = Integer.parseInt(input[2]);
		
		// N개의 데이타 입력
		int[] data = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			data[i] = Integer.parseInt(br.readLine());
		}
		
		// N개의 데이타 tree에 add
		SegmentTree tree = new SegmentTree(N);
		tree.add(data);
		
		for (int i = 0; i < M + K; i++) {
			input = br.readLine().split(" ");
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			
			// 1이면 update, 2이면 print
			if(input[0].equals("1")) {
				tree.update(b, c);
			} else {
				sb.append(tree.getResult(b, c)).append("\n");
			}
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static class SegmentTree {
		private long[] tree;
		private int dataSize;
		private final int MOD;
		
		public SegmentTree(int size) {
			this.MOD = 1000000007;
			this.dataSize = size;
			int h = (int) Math.ceil(Math.log(size) / Math.log(2));
			int treeSize = (int) Math.pow(2, h + 1);
			this.tree = new long[treeSize];
		}
		
		public long add(int[] data, int node, int start, int end) {
			if(start == end) return this.tree[node] = data[start];
			return this.tree[node] = 
					(add(data, node * 2, start, (start + end) / 2)
					* add(data, node * 2 + 1, (start + end) / 2 + 1, end)) % this.MOD;
		}
		
		public void add(int[] data) {
			this.add(data, 1, 1, this.dataSize);
		}
		
		public long update(int node, int start, int end, int idx, int val) {
			if(idx < start || end < idx) return this.tree[node];
			if(start == end) return this.tree[node] = val;
			return this.tree[node] = 
				(update(node * 2, start, (start + end) / 2, idx, val)
				* update(node * 2 + 1, (start + end) / 2 + 1, end, idx, val)) % this.MOD;
		}
		
		public void update(int idx, int val) {
			this.update(1, 1, this.dataSize, idx, val);
		}
		
		public long getResult(int node, int start, int end, int left, int right) {
			if(end < left || right < start) return 1;
			if(left <= start && end <= right) return this.tree[node];
			return (getResult(node * 2, start, (start + end) / 2, left, right)
					* getResult(node * 2 + 1, (start + end) / 2 + 1, end, left, right)) % this.MOD;
		}
		
		public long getResult(int left, int right) {
			return getResult(1, 1, this.dataSize, left, right);
		}
	}
}
