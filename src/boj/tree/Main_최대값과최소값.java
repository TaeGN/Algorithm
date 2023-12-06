package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_최대값과최소값 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Stree maxTree = new Stree(arr, "max");
		Stree minTree = new Stree(arr, "min");
		print("init 실행 후", maxTree);
		print("init 실행 후", minTree);
		
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			sb.append(minTree.getResult(input)).append(" ")
				.append(maxTree.getResult(input)).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static void print(Stree sTree) {
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
	
	static void print(String msg, Stree sTree) {
		System.out.println("\n" + msg + " : ");
		print(sTree);
	}
	
	static class Stree {
		private long[] tree;
		private int size, h;
		private boolean isMax;
		
		public Stree(int[] arr, String type) {
			this.size = arr.length - 1;
			int h = (int) Math.ceil(Math.log(this.size) / Math.log(2));
			this.h = h;
			int treeSize = (int) Math.pow(2, h + 1);
			this.tree = new long[treeSize];
			this.isMax = type.equals("max");
			init(arr, 1, 1, this.size);
		}
		
		private long init(int[] arr, int node, int start, int end) {
			if(start == end) return this.tree[node] = arr[start];
			else {
				long leftSide = init(arr, node * 2, start, (start + end) / 2);
				long rightSide = init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
				return this.tree[node] = getValue(leftSide, rightSide);
			}
		}
		
		private long getValue(long leftSide, long rightSide) {
			if(this.isMax) return Math.max(leftSide, rightSide);
			else return Math.min(leftSide, rightSide);
		}
		
		public long getResult(String[] input) {
			return getResult(1, 1, this.size, Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		}

		private long getResult(int node, int start, int end, int left, int right) {
			if(end < left || right < start) return this.isMax ? Long.MIN_VALUE : Long.MAX_VALUE;
			
			if(left <= start && end <= right) return this.tree[node];
			
			long leftSide = getResult(node * 2, start, (start + end) / 2, left, right);
			long rightSide = getResult(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
			return getValue(leftSide, rightSide);
		}
		
	}
	
}
