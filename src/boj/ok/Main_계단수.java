package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_계단수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int MOD = 1_000_000_000;
		
		int N = Integer.parseInt(br.readLine());
		int[][][][] D = new int[N + 1][10][10][10];
		for (int i = 0; i < 10; i++) {
			D[1][i][i][i] = 1;
		}
		
		/**
		 *  i : 수의 길이
		 *  j : 마지막 숫자
		 *  k : 이미 등장한 숫자의 최소값
		 *  l : 이미 등장한 숫자의 최대값
		 */
//		print(D, 1);
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				// j - 1 숫자에서 +1 하는 경우
				if(j > 0) {
					for (int l = j - 1; l <= 9; l++) {
						for (int k = 0; k <= l; k++) {
							D[i][j][k][l == j - 1 ? j : l] = (D[i][j][k][l == j - 1 ? j : l] + D[i - 1][j - 1][k][l]) % MOD;
						}
					}
				}
				if(j < 9) {
					// j + 1 숫자에서 -1 하는 경우
					for (int k = j + 1; k >= 0; k--) {
						for (int l = k; l <= 9; l++) {
							D[i][j][k == j + 1 ? j : k][l] = (D[i][j][k == j + 1 ? j : k][l] + D[i - 1][j + 1][k][l]) % MOD;
						}
					}
				}
			}
//			print(D, i);
		}
		print(D, N);
		int res = 0;
		for (int i = 1; i <= 9; i++) {
			res = (res + D[N][i][0][9]) % MOD;
		}
		System.out.println(res);
	}
	
	static void print(int[][][][] D, int N) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n---- N : ").append(N).append(" start ----\n");
		for (int i = 0; i < 10; i++) {
			sb.append("---- 마지막 숫자 : ").append(i).append(" start ----\n");
			for (int j = 0; j < 10; j++) {
//				sb.append("idx : ").append(j).append(" - ");
				for (int k = 0; k < 10; k++) {
					sb.append(D[N][i][j][k]).append(" ");
				}
				sb.append("\n");
			}
			sb.append("---- i : ").append(i).append(" end ----\n");
		}
		sb.append("\n---- N : ").append(N).append(" end ----\n\n");
		System.out.println(sb);
	}
}
