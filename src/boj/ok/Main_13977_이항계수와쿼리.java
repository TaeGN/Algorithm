package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_13977_이항계수와쿼리 {
	static final long MOD = 1_000_000_007;
	static final int MAX_N = 4_000_000;
	static long[] fac;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		fac = new long[MAX_N + 1];
		fac[0] = 1;
		for (int i = 1; i <= MAX_N; i++) {
			fac[i] = (fac[i - 1] * i) % MOD;
		}
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int K = Integer.parseInt(input[1]);
			sb.append(combination(N, K)).append("\n");
		}
		br.close();
		System.out.println(sb);
	}
	
	// nCk
	static long combination(int n, int k) {
		return (((fac[n] * power(fac[n - k], MOD - 2)) % MOD)
				* power(fac[k], MOD - 2)) % MOD;
	}
	
	// x^y
	private static long power(long x, long y) {
		long res = 1;
		x %= MOD;
		while(y > 0) {
			if(y % 2 == 1) res = (res * x) % MOD;
			y /= 2;
			x = (x * x) % MOD;
		}
		return res;
	}
}
