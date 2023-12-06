package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;

public class Main_�����ձ��ϱ�_seg {
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
		print("sTree init ��",sTree);
		sTree.init();
		print("sTree init ��",sTree);
		print("sTree update ��",sTree);
		for (int i = 0; i < M + K; i++) {
			input = br.readLine().split(" ");
			if(input[0].equals("1")) sTree.update(input);
		}
		print("sTree update ��",sTree);
		
		
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


		public void init() {
			this.init(1, 1, (int) Math.pow(2, this.h));
		}
		
		private long init(int node, int start, int end) {
			if(start == end) return this.tree[node];
			else return this.tree[node] = init(node * 2, start, (start + end) / 2)
						+ init(node * 2 + 1, (start + end) / 2 + 1, end);
		}
		
		private void update(int node, int start, int end, int idx, long val) {
			if(idx < start || idx > end) return;
			else {
				if(start == idx && end == idx) {
					this.tree[node] = val;
				} else {
					
				}
			}
		}
		
		public void update(String[] input) {
			update(1,1,(int) Math.pow(2, this.h),Integer.parseInt(input[1]), Integer.parseInt(input[2]));
		}
	}
}
