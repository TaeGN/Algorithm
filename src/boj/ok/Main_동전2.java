package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_동전2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		final int MAX_CNT = 10000;
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		
		int[] dp = new int[K + 1];
		Arrays.fill(dp, MAX_CNT + 1);
		dp[0] = 0;
		
		for (int n = 0; n < N; n++) {
			int val = Integer.parseInt(br.readLine());
			if(val > K) continue;
			for (int k = val; k <= K; k++) {
				dp[k] = Math.min(dp[k], dp[k - val] + 1);
			}
		}
		
		System.out.println(Arrays.toString(dp));
		System.out.println(dp[K] > MAX_CNT ? -1 : dp[K]);
	}
}
