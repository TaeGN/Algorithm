package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_배럭 {
	static int N, B, U;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		B = Integer.parseInt(input[1]);
		U = Integer.parseInt(input[2]);
		
		
		System.out.println(getResult());

	}
	private static int getResult() {
		if(N >= B) return 1;
		
		if(N > U) return (B - N - 1) / (N - U) + 2;
		
		if(N < U) {
			int cnt = 0;
			int u = 0, n = N, b = B;
			while(n > 0) {
				cnt++;
				if((b -= n) <= 0) return cnt + getCnt(b, n, u);
				n -= u;
				u += U;
			}
		}
		if(2 * N == B + U) return 2;
		if(2 * N >= B + U / 2) return 3;
		
		return -1;
	}
	private static int getCnt(int b, int n, int u) {
		int cnt = 0;
		if((u -= (n - b)) < 0) return 0;
		while(n > 0) {
			n -= u;
			u += U;
			cnt++;
			if((u -= n) < 0) return cnt;
		}
		return -1;
	}
}
