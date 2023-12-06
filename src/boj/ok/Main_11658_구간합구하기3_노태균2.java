package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_11658_구간합구하기3_노태균2 {
	static int N, M, K;
	static long tree[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		tree = new long[N][N + 1];
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 1; j <= N; j++) {
				update(tree[i], j, Integer.parseInt(input[j - 1]));
			}
		}
		
		int r1,c1,r2,c2;
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			r1 = Integer.parseInt(input[1]);
			c1 = Integer.parseInt(input[2]);
			if(input[0].equals("0")) update(tree[r1], c1, Integer.parseInt(input[3]));
			else {
				r2 = Integer.parseInt(input[3]);
				c2 = Integer.parseInt(input[4]);
				sb.append(sectionSum(r1, c1, r2, c2)).append("\n");
			}
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static void update(long[] cur, int c, int num) {
		while(c <= N) {
			cur[c] += num;
			c += (c & -c);
		}
	}
	
	static long sum(int r, int c) {
		long[] cur = tree[r];
		long res = 0;
		while(c > 0) {
			res += cur[c];
			c -= (c & -c);
		}
		return res;
	}
	
	static long sectionSum(int r1, int c1, int r2, int c2) {
		long res = 0;
		for (int i = r1; i <= r2; i++) {
			res += sum(i, c2) - sum(i, c1);
		}
		return res;
	}
	
}
