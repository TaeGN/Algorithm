package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_동전 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		int[] dp = new int[K + 1];
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			if(n > K) continue;
			dp[n]++;
			for (int j = 1; j <= K - n; j++) {
				dp[j + n] += dp[j];
			}
		}
		System.out.println(dp[K]);
	}
}
