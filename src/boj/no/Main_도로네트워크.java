package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Main_도로네트워크 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int ROOT = 1;
		int N = readInt(br.readLine());
		
		ArrayList<Edge>[] list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			String[] input = readLine(br);
			int A = readInt(input[0]);
			int B = readInt(input[1]);
			int C = readInt(input[2]);
			list[A].add(new Edge(B, C));
			list[B].add(new Edge(A, C));
		}
		
		final int MAX_I = (int) Math.ceil(Math.log(N) / Math.log(2));
		int[][] parent = new int[N + 1][MAX_I + 1];
		int[] depth = new int[N + 1];
		int[] min = new int[N + 1];
		int[] max = new int[N + 1];
		min[ROOT] = Integer.MAX_VALUE;
		
		bfs(ROOT, min, max, list, N, parent, depth);
		
		for (int j = 1; j <= MAX_I; j++) {
			for (int i = 1; i <= N; i++) {
				parent[i][j] = parent[parent[i][j - 1]][j - 1];
			}
		}
		
		System.out.println(Arrays.toString(min));
		System.out.println(Arrays.toString(max));
		
		StringBuilder sb = new StringBuilder();
		int K = readInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			String[] input = readLine(br);
			int D = readInt(input[0]);
			int E = readInt(input[1]);
			getMin(D, E, parent, depth, MAX_I);
			sb.append(Math.min(min[D], min[E])).append(" ").append(Math.max(max[D], max[E])).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static void getMin(int D, int E, int[][] parent, int[] depth, int MAX_I) {
		if(depth[D] < depth[E]) {
			int temp = D;
			D = E;
			E = temp;
		}
		
		int diff = depth[D] - depth[E];
		
		for (int i = MAX_I; i >= 0; i--) {
			if((diff & (1 << i)) != 0) D = parent[D][i];
		}
		
		if()
		for (int i = MAX_I; i >= 0; i--) {
			if((diff & (1 << i)) != 0) D = parent[D][i];
		}
	}

	static void bfs(int cur, int[] min, int[] max, ArrayList<Edge>[] list, int N, int[][] parent, int[] depth) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		queue.offer(cur);
		visited[cur] = true;
		
		while(!queue.isEmpty()) {
			cur = queue.poll();
			for (Edge next : list[cur]) {
				if(visited[next.idx]) continue;
				visited[next.idx] = true;
				min[next.idx] = Math.min(min[cur], next.weight);
				max[next.idx] = Math.max(max[cur], next.weight);
				parent[next.idx][0] = cur;
				depth[next.idx] = depth[cur] + 1;
				queue.offer(next.idx);
			}
		}
		
	}

	static int readInt(String s) {
		return Integer.parseInt(s);
	}
	
	static String[] readLine(BufferedReader br) throws IOException {
		return br.readLine().split(" ");
	}
	
	static class Edge {
		int idx, weight;

		public Edge(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}
	}
}
