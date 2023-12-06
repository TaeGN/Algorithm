package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_내리막길 {
	static int M, N, map[][], dp[][];
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		M = Integer.parseInt(input[0]);
		N = Integer.parseInt(input[1]);
		
		map = new int[M + 2][N + 2];
		Arrays.fill(map[0], Integer.MAX_VALUE);
		Arrays.fill(map[M + 1], Integer.MAX_VALUE);
		for (int m = 1; m <= M; m++) {
			map[m][0] = map[m][N + 1] = Integer.MAX_VALUE;
			input = br.readLine().split(" ");
			for (int n = 1; n <= N; n++) {
				map[m][n] = Integer.parseInt(input[n - 1]);
			}
		}
		
		dp = new int[M + 1][N + 1];
		for (int i = 1; i <= M; i++) {
			Arrays.fill(dp[i], Integer.MIN_VALUE);
		}
		dp[M][N] = 1;
		
		dfs(1, 1);
		
		print(dp, M, N);
		System.out.println(dp[1][1]);
	}
	
	static int dfs(int m, int n) {
		if(dp[m][n] >= 0) {
			return dp[m][n];
		}
		
		dp[m][n] = 0;
		for (int i = 0; i < 4; i++) {
			if(map[m][n] > map[m + dr[i]][n + dc[i]]) {
				dp[m][n] += dfs(m + dr[i], n + dc[i]);
			}
		}
		return dp[m][n];
	}
	
	static void print(int[][] arr, int M, int N) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(arr[i][j] == Integer.MIN_VALUE ? 0 : arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
