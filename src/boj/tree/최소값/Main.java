package boj.tree.최소값;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import util.print.SegTreePrint;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		SegTree minTree = new SegTree(N);
		int[] data = new int[N + 1];
		print("minTree create", minTree);
		for (int i = 1; i <= N; i++) {
			data[i] = Integer.parseInt(br.readLine());
		}
		minTree.init(data);
		System.out.println(Arrays.toString(data));
		print("minTree init", minTree);
		
		for (int i = 0; i < M; i++) {
			sb.append(minTree.getResult(br.readLine().split(" "))).append("\n");
		}
		br.close();
		System.out.println(sb);
	}
	
	public static void print(String msg, SegTree minTree) {
		SegTreePrint minTreePrint = new SegTreePrint(msg, minTree.data);
		minTreePrint.print(minTree.tree, minTree.h);
	}


	static class SegTree {
		private int[] tree, data;
		private int dataSize, h;
		
		public SegTree(int dataSize) {
			this.dataSize = dataSize;
			
			this.h = (int) Math.ceil(Math.log(dataSize) / Math.log(2));
			int treeSize = (int) Math.pow(2, this.h + 1);
			this.tree = new int[treeSize];
		}

		public void init(int[] data) {
			this.data = data;
			init(1, 1, this.dataSize);
		}

		private int init(int node, int start, int end) {
			if(start == end) return this.tree[node] = this.data[start];
			else return this.tree[node] = Math.min(init(node * 2, start, (start + end) / 2), init(node * 2 + 1, (start + end) / 2 + 1, end));
		}
		
		
		public int getResult(String[] input) {
			return getResult(1, 1, this.dataSize, Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		}

		private int getResult(int node, int start, int end, int left, int right) {
			if(end < left || right < start) return Integer.MAX_VALUE; 
			if(left <= start && end <= right) return this.tree[node];
			return Math.min(getResult(node * 2, start, (start + end) / 2, left, right), getResult(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
		}

	}
}
