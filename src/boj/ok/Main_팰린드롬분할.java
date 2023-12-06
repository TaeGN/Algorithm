package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_팰린드롬분할 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] word = br.readLine().toCharArray();
		int N = word.length;
		int[] dp = new int[N + 1];
		Arrays.fill(dp, 1, N + 1, Integer.MAX_VALUE);
		
		for (int i = 1; i <= N; i++) {
			a:for (int j = 1; j <= i; j++) {
				if(dp[i] <= dp[i - j] + 1) continue;
				for (int start = i - j, end = i - 1; start <= end; start++, end--) {
					if(word[start] != word[end]) continue a;
				}
				dp[i] = Math.min(dp[i], dp[i - j] + 1);
			}
			System.out.println(Arrays.toString(dp));
		}
		
		System.out.println(dp[N]);
	}
}
