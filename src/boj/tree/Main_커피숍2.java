package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_커피숍2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int Q = Integer.parseInt(input[1]);
		
		FenwickTree fenwickTree = new FenwickTree(N);
		
		// N개의 정수 입력
		input = br.readLine().split(" ");
		for (int i = 1; i <= N; i++) {
			fenwickTree.add(i, Long.parseLong(input[i - 1]));
		}
		
		// Q개의 쿼리 실행
		for (int i = 0; i < Q; i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			int a = Integer.parseInt(input[2]);
			long b = Long.parseLong(input[3]);
			
			// x~y의 구간 합
			if(x <= y)
				sb.append(fenwickTree.sum(x, y)).append("\n");
			else
				sb.append(fenwickTree.sum(y, x)).append("\n");
			
			// a자리에 (b - 원래값)을 더한다.
			fenwickTree.add(a, b - fenwickTree.get(a));
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static class FenwickTree {
		private long[] tree;
		
		public FenwickTree(int size) {
			this.tree = new long[size + 1];
		}
		
		public int size() {
			return this.tree.length;
		}
		
		
		public void add(int idx, long val) {
			while(idx < this.size()) {
				this.tree[idx] += val;
				idx += (idx & -idx);
			}
		}
		
		public long sum(int idx) {
			long res = 0;
			
			while(idx > 0) {
				res += this.tree[idx];
				idx -= (idx & -idx);
			}
			
			return res;
		}
		
		public long sum(int start, int end) {
			return sum(end) - sum(start - 1);
		}
		
		public long get(int idx) {
			return sum(idx) - sum(idx - 1);
		}
	}
}
