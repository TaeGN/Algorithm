package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_웜홀 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		final int MAX_N = 500;
		final int INF = Integer.MAX_VALUE;
		int[][] dp = new int[MAX_N + 1][MAX_N + 1];
		
		for (int tc = 0; tc < TC; tc++) {
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int M = Integer.parseInt(input[1]);
			int W = Integer.parseInt(input[2]);
			
			// init
			for (int i = 1; i <= N; i++) {
				Arrays.fill(dp[i], 1, N + 1, INF);
			}
			print(dp, N);
			
			// 도로 & 웜홀
			for (int i = 0; i < M + W; i++) {
				input = br.readLine().split(" ");
				int S = Integer.parseInt(input[0]);
				int E = Integer.parseInt(input[1]);
				int T = Integer.parseInt(input[2]);
				if(i < M) {
					if(dp[S][E] > T) {
						dp[S][E] = dp[E][S] = T;
					}
				} else {
					T = -T;
					if(dp[S][E] > T) {
						dp[S][E] = T;
					}
				}
			}
			
			print(dp, N);
			
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					if(dp[i][k] == INF) continue;
					for (int j = 1; j <= N; j++) {
						if(dp[k][j] == INF) continue;
						if(dp[i][j] > dp[i][k] + dp[k][j]) {
							dp[i][j] = dp[i][k] + dp[k][j];
						}
					}
				}
			}
			print(dp, N);
			
			boolean isOk = false;
			for (int i = 1; i <= N; i++) {
				if(dp[i][i] < 0) {
					isOk = true;
					break;
				}
			}
			
			if(isOk) sb.append("YES");
			else sb.append("NO");
			sb.append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static void print(int[][] arr, int N) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(arr[i][j] == Integer.MAX_VALUE ? 0 : arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
