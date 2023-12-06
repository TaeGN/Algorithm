package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_평범한배낭 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		int[] dp = new int[K + 1];
		Thing[] things = new Thing[N];
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			int W = Integer.parseInt(input[0]);
			int V = Integer.parseInt(input[1]);
			things[i] = new Thing(W, V);
		}
		
		for (int n = 0; n < N; n++) {
			for (int k = K; k >= 1; k--) {
				if(k >= things[n].W) {
					dp[k] = Math.max(dp[k], dp[k - things[n].W] + things[n].V);
				}
			}
		}
		int res = 0;
		for (int k = 1; k <= K; k++) {
			res = Math.max(res, dp[k]);
		}
		System.out.println(res);
	}
	
	static class Thing {
		private int W, V;
		public Thing(int W, int V) {
			this.W = W;
			this.V = V;
		}
	}
}
