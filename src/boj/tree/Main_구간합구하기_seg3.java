package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_구간합구하기_seg3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int K = Integer.parseInt(input[2]);
		
		STree sTree = new STree(N);
		
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		print("init 실행 전", sTree);
		sTree.init(arr);
		print("init 실행 후", sTree);
		
		print("update 실행 전", sTree);
		for (int i = 0; i < M + K; i++) {
			input = br.readLine().split(" ");
			if(input[0].equals("1")) {
				int idx = Integer.parseInt(input[1]);
				long val = Integer.parseInt(input[2]);
				long diff = val - arr[idx];
				sTree.update(idx, diff);
			}
		}
		print("update 실행 후", sTree);
		
		
	}
	
	static void print(STree sTree) {
		long[] tree = sTree.tree;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= sTree.h; i++) {
			int start = (int) Math.pow(2, i);
			int end = (int) Math.pow(2, i + 1) - 1;
			for (int j = start; j <= end; j++) {
				sb.append(tree[j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void print(String msg, STree sTree) {
		System.out.println("\n" + msg + " : ");
		print(sTree);
	}
	
	static class STree {
		private long[] tree;
		private int h, treeSize;
		
		public STree(int size) {			
			this.h = (int) Math.ceil(Math.log(size) / Math.log(2));
			this.treeSize = (int) Math.pow(2, this.h + 1);
			this.tree = new long[this.treeSize];
		}


		public void init(int[] arr) {
//			this.init(arr, 1, 1, arr.length);
			this.init(arr, 1, 1, (int) Math.pow(2, this.h));
		}

		private long init(int[] arr, int node, int start, int end) {
			if(start == end) return this.tree[node] = arr[start];
			else return this.tree[node] = init(arr, node * 2, start, (start + end) / 2)
						+ init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
		}
		
		private void update(int node, int start, int end, int idx, long diff) {
			if(idx < start || idx > end) return;
			
			this.tree[node] += diff;
			
			if(start != end) {
				update(node, start, (start + end) / 2, idx, diff);
				update(node, (start + end) / 2 + 1, end, idx, diff);
			}
		}
		
		public void update(int idx, long diff) {
			update(1,1,(int) Math.pow(2, this.h), idx, diff);
		}
	}
}
