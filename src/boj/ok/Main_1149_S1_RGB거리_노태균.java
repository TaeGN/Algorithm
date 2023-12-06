package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1149_S1_RGB거리_노태균 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[3][N];
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < 3; j++) {
				dp[j][i] = Integer.parseInt(input[j]);
			}
		}
		
		for(int i = 0; i < N - 1; i++) {
			dp[0][i + 1] += Math.min(dp[1][i], dp[2][i]);
			dp[1][i + 1] += Math.min(dp[0][i], dp[2][i]);
			dp[2][i + 1] += Math.min(dp[0][i], dp[1][i]);
		}
		System.out.println(Math.min(dp[0][N - 1], Math.min(dp[1][N - 1], dp[2][N - 1])));
	}
}
