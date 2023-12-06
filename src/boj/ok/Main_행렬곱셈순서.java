package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// dp
public class Main_행렬곱셈순서 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] matrix = new int[N + 2][2];
		
		for (int i = 1; i <= N; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			matrix[i][0] = a;
			matrix[i][1] = b;
		}
		br.close();
		
		int[][] dp = new int[N + 2][N + 2];
		for (int i = 1; i <= N - 1; i++) {
			for (int j = 1; i + j <= N; j++) {	// j = row, i + j = col
				int min = Integer.MAX_VALUE;
				for (int k = j; k < i + j; k++) {
					min = Math.min(min, dp[j][k] + dp[k + 1][i + j] + matrix[j][0] * matrix[k][1] * matrix[i + j][1]);
				}
				dp[j][i + j] = min;
			}
		}
		
		print(dp);
		
		System.out.println(dp[1][N]);
	}
	
	public static void print(int[][] LCS) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < LCS.length; i++) {
			for (int j = 1; j < LCS[0].length; j++) {
				sb.append(LCS[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
