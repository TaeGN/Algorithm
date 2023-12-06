package swea.b형특강.lecture2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_중위순회 {
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			char[] words = new char[N + 1];
			
			for (int i = 0; i < N; i++) {
				String[] input = br.readLine().split(" ");
				int idx = Integer.parseInt(input[0]);
				char c = input[1].charAt(0);
				words[idx] = c;
			}
			inOrder(1, words, N);
			sb.append("\n");
		}
		br.close();
		System.out.println(sb);
	}
	
	
	private static void inOrder(int idx, char[] words, int N) {
		if(idx > N) return;
		
		inOrder(idx * 2, words, N);
		sb.append(words[idx]);
		inOrder(idx * 2 + 1, words, N);
	}
}
