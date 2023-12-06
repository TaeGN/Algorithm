package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_파일합치기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		final int MAX_K = 500;
		final int INF = Integer.MAX_VALUE;
		int[][] dp = new int[MAX_K][MAX_K];
		int[][] min = new int[MAX_K][MAX_K];
		
		for (int tc = 0; tc < T; tc++) {
			
			int K = Integer.parseInt(br.readLine());
			
			// init
			for (int i = 0; i < K; i++) {
				Arrays.fill(dp[i], INF);
				Arrays.fill(min[i], INF);
			}
			
			String[] input = br.readLine().split(" ");
			for (int i = 0; i < K; i++) {
				dp[i][i] = Integer.parseInt(input[i]);
				min[i][i] = 0;
			}
			
			// 횟수
			for (int i = 1; i <= K - 1; i++) {
				// row
				for (int j = 0; j < K - i; j++) {
					for (int k = 1; k <= i; k++) {
						if(min[j][j + i] > min[j][j + i - k] + min[j + (i - k) + 1][j + i] + dp[j][j + i - k] + dp[j + (i - k) + 1][j + i]) {
							dp[j][j + i] = dp[j][j + i - k] + dp[j + (i - k) + 1][j + i];
							min[j][j + i] = min[j][j + i - k] + min[j + (i - k) + 1][j + i] + dp[j][j + i];
						}
					}
				}
			}
			print(min, K);
			print(dp, K);
			sb.append(min[0][K - 1]).append("\n");
		}
		br.close();
		System.out.println(sb);
	}
	
	static void print(int[][] arr, int K) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				sb.append(arr[i][j] == Integer.MAX_VALUE ? 0 : arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
