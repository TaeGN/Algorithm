package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10971_S2_외판원순회2_노태균 {
	static int N;
	static int min = Integer.MAX_VALUE;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			int j = 0;
			for(String s : br.readLine().split(" ")) {
				map[i][j++] = Integer.parseInt(s);
			}
		}
		
		dfs(0, 1, 0, new boolean[N]);
		System.out.println(min);
	}
	private static void dfs(int cur, int cnt, int sum, boolean[] visited) {
		if(sum >= min) return;
		if(cnt == N) {
			if(map[cur][0] != 0) {
				min = Math.min(min, sum + map[cur][0]);
			}
			return;
		}
		
		visited[cur] = true;
		for(int i = 0; i < N; i++) {
			if(visited[i] || map[cur][i] == 0) continue;
			dfs(i, cnt + 1, sum + map[cur][i], visited);
		}
		visited[cur] = false;
	}
}
