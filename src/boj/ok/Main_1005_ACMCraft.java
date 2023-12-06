package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_1005_ACMCraft {
	static List<Integer>[] build;
	static int[] time, D;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int K = Integer.parseInt(input[1]);
			build = new ArrayList[N + 1];
			time = new int[N + 1];
			input = br.readLine().split(" ");
			for (int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(input[i - 1]);
				build[i] = new ArrayList<>();
			}
			for (int i = 0; i < K; i++) {
				input = br.readLine().split(" ");
				int a = Integer.parseInt(input[0]);
				int b = Integer.parseInt(input[1]);
				build[b].add(a);
			}
			D = new int[N + 1];
			Arrays.fill(D, -1);
			int start = Integer.parseInt(br.readLine());
			go(start);
			sb.append(D[start]).append("\n");
		}
		br.close();
		System.out.println(sb);
	}
	
	private static void go(int curIdx) {
		int max = 0;
		for (int nextIdx : build[curIdx]) {
			if(D[nextIdx] == -1) go(nextIdx);
			max = Math.max(max, D[nextIdx]);
		}
		D[curIdx] = max + time[curIdx];
	}
}
