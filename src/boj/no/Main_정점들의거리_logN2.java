package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Main_정점들의거리_logN2 {
	
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		LinePool linePool = new LinePool(2 * (N - 1));
		
		ArrayList<Line>[] tree = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<Line>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			String[] input = br.readLine().split(" ");
			int A = Integer.parseInt(input[0]);
			int B = Integer.parseInt(input[1]);
			int distance = Integer.parseInt(input[2]);
			tree[A].add(linePool.get(B, distance));
			tree[B].add(linePool.get(A, distance));
		}
		
		
		int[] dp = new int[N + 1];
		final int MAX_I = (int) (Math.log(N) / Math.log(2));
		int[][] parent = new int[N + 1][MAX_I];
		int[] rank = new int[N + 1];
		parent[1][0] = -1;
		bfs(1, dp, tree, parent, rank);
		for (int i = 1; i <= N; i++) {
			System.out.println(Arrays.toString(parent[i]));
		}
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			int A = Integer.parseInt(input[0]);
			int B = Integer.parseInt(input[1]);
			
			int res = dp[A] + dp[B];
			int diff = rank[A] - rank[B];
			int end = (int) (Math.log(diff) / Math.log(2));
			
			// 공통조상 찾기
			if(diff > 0) {
				for (int j = 0; j < end; j++) {
					if((diff & (1 << j)) != 0) A = parent[A][j];
				}
			} else if(diff < 0) {
				diff = -diff;
				for (int j = 0; j < end; j++) {
					if((diff & (1 << j)) != 0) B = parent[B][j];
				}
			}
			
			int j = (int) (Math.log(rank[A]) / Math.log(2));
			for (; j >= 0; j--) {
				if(parent[A][j] != -1 && parent[A][j] != parent[B][j]) {
					A = parent[A][j];
					B = parent[B][j];
				}
			}
			
			sb.append(res - 2 * dp[parent[A][0]]).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static void bfs(int idx, int[] dp, ArrayList<Line>[] tree, int[][] parent, int[] rank) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(idx);
		
		while(!queue.isEmpty()) {
			idx = queue.poll();
			for (Line line : tree[idx]) {
				if(parent[line.idx][0] != 0)  {
					parent[idx][0] = line.idx;
					for (int i = 1, end = (int) (Math.log(rank[line.idx]) / Math.log(2)); i <= end; i++) {
						parent[idx][i] = parent[parent[idx][i - 1]][i - 1];
					}
					rank[idx] = rank[line.idx] + 1;
				} else {
					dp[line.idx] = dp[idx] + line.distance;
					queue.offer(line.idx);
				}
			}
		}
		
	}
	
	static class Line {
		int idx, distance;
		public Line() {}
		public void set(int idx, int distance) {
			this.idx = idx;
			this.distance = distance;
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Line [idx=").append(idx).append(", distance=").append(distance).append("]");
			return builder.toString();
		}
		
	}
	
	static class LinePool {
		Line[] pool;
		int idx;
		public LinePool(int size) {
			this.pool = new Line[size];
			for (int i = 0; i < size; i++) {
				this.pool[i] = new Line();
			}
			this.idx = -1;
		}
		public Line get(int idx, int distance) {
			Line line = this.pool[++this.idx];
			line.set(idx, distance);
			return line;
		}
	}
}
	
