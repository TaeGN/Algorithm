package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_구간합구하기_seg2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int K = Integer.parseInt(input[2]);
		
		STree sTree = new STree(N);
		
		// init
		for (int i = 0, start = (int) Math.pow(2, sTree.h); i < N; i++) {
			System.out.println(start + i);
			sTree.tree[start + i] = Integer.parseInt(br.readLine());
//			System.out.println(Integer.parseInt(br.readLine()));
		}
		print(sTree);
		sTree.init(1, 1, N);
		
		print(sTree);
		
		
		
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
	
	static class STree {
		private long[] tree;
		private int h;
		
		public STree(int size) {
			this.tree = new long[getTreeSize(size)];
		}
		
		public long init(int node, int start, int end) {
			if(start == end) return this.tree[node];
			else return this.tree[node] = init(node * 2, start, (start + end) / 2)
						+ init(node * 2 + 1, (start + end) / 2 + 1, end);
			
		}
		
		private int getTreeSize(int size) {
			this.h = (int) Math.ceil(Math.log(size) / Math.log(2));
			return (int) Math.pow(2, this.h + 1);
		}
	}
}
