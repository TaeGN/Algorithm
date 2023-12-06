package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_트리의지름 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		LinePool linePool = new LinePool(V * 2);
		ArrayList<Line>[] tree = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < V; i++) {
			String[] input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			for (int j = 1; !input[j].equals("-1") ;) {
				int to = Integer.parseInt(input[j++]);
				int distance = Integer.parseInt(input[j++]);
				tree[from].add(linePool.get(to, distance));
			}
		}
		
		int[] dp = new int[V + 1];
		boolean[] visited = new boolean[V + 1];
		dfs(1, tree, dp, visited);
		
		int idx = 0;
		int res = 0;
		for (int i = 1; i <= V; i++) {
			if(res < dp[i]) {
				res = dp[i];
				idx = i;
			}
		}
		
		System.out.println(Arrays.toString(dp));
		System.out.println(idx);
		Arrays.fill(dp, 0);
		Arrays.fill(visited, false);
		dfs(idx, tree, dp, visited);
		
		res = 0;
		for (int i = 1; i <= V; i++) {
			res = Math.max(res, dp[i]);
		}
		
		System.out.println(Arrays.toString(dp));
		System.out.println(res);
	}
	
	private static void dfs(int from,  ArrayList<Line>[] tree, int[] dp, boolean[] visited) {
		visited[from] = true;
		
		for (Line line : tree[from]) {
			if(visited[line.to]) continue;
			dp[line.to] = dp[from] + line.distance;
			dfs(line.to, tree, dp, visited);
		}
		
		visited[from] = false;
	}

	static class Line {
		int to, distance;
		public Line() {}
		public void set(int to, int distance) {
			this.to = to;
			this.distance = distance;
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
		public Line get(int to, int distance) {
			Line line = this.pool[++idx];
			line.set(to, distance);
			return line;
		}
	}
}
