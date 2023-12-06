package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_사전 {
	static StringBuilder sb = new StringBuilder();
	static int K;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		
		long cnt = 0;
		int i = 0;
		for (; i <= N; i++) {
			long num = combination(M + i - 1, i);
			if(cnt + num  >= K) break;
			cnt += num;
		}
		
		// K번째 문자열 존재 x
		if(i == N + 1) {
			sb.append(-1);
		} else {
			for (int j = 0; j < N - i; j++) {
				sb.append("a");
			}
			if(cnt == K) {
				for (int j = 0; j < M; j++) {
					sb.append("z");
				}
				for (int j = 0; j < i; j++) {
					sb.append("a");
				}
			} else {
				sb.append("z").append("a");
				getWord(cnt, i - 1, M - 1);
			}
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static void getWord(long cnt, int N, int M) {
		
		int i = 0;
		for (; i <= M; i++) {
			long num = combination(M - i + N, Math.min(N, M - i));
			if(cnt + num >= K) break;
			cnt += num;
		}
		
		for (int j = 0; j < i; j++) {
			sb.append("z");
		}
		sb.append("a");
	}
	
	// nCr
	static long combination(int n, int r) {
		long res = 1;
		for (int i = 0; i < r; i++) {
			res = res * (n - i) / (i + 1);
		}
		return res;
	}
}
