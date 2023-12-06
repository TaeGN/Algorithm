package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_앱 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		int[][] app = new int[N][2];
		for (int i = 0; i < 2; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				app[j][i] = Integer.parseInt(input[j]);
			}
		}
		
		int[] dp = new int[M + 1];
		for (int i = 0; i < dp.length; i++) {
			
		}
	}
}
