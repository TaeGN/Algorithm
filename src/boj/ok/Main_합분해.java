package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_합분해 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		final int MOD = 1_000_000_000;
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		int[][] dp = new int[K + 1][N + 1];
		Arrays.fill(dp[1], 1);
		
		for (int i = 2; i <= K; i++) {
			for (int j = 0; j <= N; j++) {
				for (int k = j; k <= N; k++) {
					dp[i][k] = (dp[i][k] + dp[i - 1][k - j]) % MOD;
				}
			}
		}
		System.out.println(Arrays.toString(dp[K]));
		System.out.println(dp[K][N]);
	}
}
