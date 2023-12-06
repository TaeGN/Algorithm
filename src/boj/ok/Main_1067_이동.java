package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1067_이동 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] X = new int[N];
		long[] S = new long[N];
		int i = 0;
		for(String s : br.readLine().split(" ")) {
			X[i++] = Integer.parseInt(s);
		}
		
		int num;
		i = 0;
		for(String s : br.readLine().split(" ")) {
			num = Integer.parseInt(s);
			for(int j = 0; j < N; j++) {
				S[j] += X[(j + i) % N] * num;
			}
			i++;
		}
		br.close();
		
		Arrays.sort(S);
		System.out.println(S[S.length - 1]);
	}
	
}
