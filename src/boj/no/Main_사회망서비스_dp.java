package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main_사회망서비스_dp {
	
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		final int ROOT = 1;
		int[][] dp = new int[2][N + 1];
		boolean[] visited = new boolean[N + 1];
		Arrays.fill(dp[0], INF);
		Arrays.fill(dp[1], INF);
		
		ArrayList<Integer>[] tree = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			String[] input = br.readLine().split(" ");
			Integer from = Integer.parseInt(input[0]);
			Integer to = Integer.parseInt(input[1]);
			tree[from].add(to);
			tree[to].add(from);
		}
		dp[1][ROOT] = 0;
		dfs(ROOT, true, dp, tree, visited);
		dfs(ROOT, false, dp, tree, visited);
		
		System.out.println(Arrays.toString(dp[0]));
		System.out.println(Arrays.toString(dp[1]));
		System.out.println(Math.min(dp[0][ROOT], dp[1][ROOT]));
	}
	
	static int dfs(int idx, boolean isEarly, int[][] dp, ArrayList<Integer>[] tree, boolean[] visited) {
		dp[isEarly ? 0 : 1][idx] = (isEarly ? 1 : 0);
		visited[idx] = true;
		
		System.out.println(idx);
		for (Integer next : tree[idx]) {
			if(visited[next]) continue;
			if(isEarly) {
				dp[0][idx] += Math.min(dfs(next, isEarly, dp, tree, visited), dfs(next, !isEarly, dp, tree, visited));
			} else {
				dp[1][idx] += dfs(next, isEarly, dp, tree, visited);
			}
		}
		
		visited[idx] = false;
		return dp[isEarly ? 0 : 1][idx];
	}
}
	
