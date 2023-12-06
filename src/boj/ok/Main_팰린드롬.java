package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_팰린드롬 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" "); 
		boolean[][] D = new boolean[N + 1][N + 1];
		int[] numArr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			numArr[i] = Integer.parseInt(input[i - 1]);
		}
		
		for (int i = 1; i <= N; i++) {
			// 홀수개의 팰린드롬
			for (int start = i, end = i; start >= 1 && end <= N; start--, end++) {
				if(numArr[start] != numArr[end]) break;
				D[start][end] = true;
			}
			
			// 짝수개의 팰린드롬
			for (int start = i, end = i + 1; start >= 1 && end <= N; start--, end++) {
				if(numArr[start] != numArr[end]) break;
				D[start][end] = true;
			}
		}
		print(D);
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			sb.append(D[start][end] ? 1 : 0).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static public void print(boolean[][] D) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < D.length; i++) {
			for (int j = 1; j < D.length; j++) {
				sb.append(D[i][j] ? 1 : 0).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
