package swea.b형특강.lecture3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_knapsack {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		final int MAX_K = 1000;
		int[] D = new int[MAX_K + 1];
		
		for (int tc = 1; tc <= T; tc++) {
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int K = Integer.parseInt(input[1]);
			
			for (int n = 1; n <= N; n++) {
				input = br.readLine().split(" ");
				int V = Integer.parseInt(input[0]);
				int C = Integer.parseInt(input[1]);
				for (int k = K; k >= 1; k--) {
					if(V <= k) D[k] = Math.max(D[k], D[k - V] + C);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(D[K]).append("\n");
			Arrays.fill(D, 1, K + 1, 0);
		}
		br.close();
		System.out.println(sb);
	}
}
