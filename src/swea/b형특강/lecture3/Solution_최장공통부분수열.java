package swea.b형특강.lecture3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_최장공통부분수열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		final int MAX_WORD_LEN = 1000;
		int[][] D = new int[2][MAX_WORD_LEN + 1];
		
		for (int tc = 1; tc <= T; tc++) {
			String[] input = br.readLine().split(" ");
			char[] word1 = input[0].toCharArray();
			char[] word2 = input[1].toCharArray();
			int len1 = word1.length;
			int len2 = word2.length;
			
			for (int i = 1; i <= len1; i++) {
				for (int j = 1; j <= len2; j++) {
					if(word1[i - 1] == word2[j - 1]) D[i % 2][j] = D[(i + 1) % 2][j - 1] + 1;
					else D[i % 2][j] = Math.max(D[(i + 1) % 2][j], D[i % 2][j - 1]);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(D[len1 % 2][len2]).append("\n");
			Arrays.fill(D[0], 1, len2 + 1, 0);
		}
		br.close();
		System.out.println(sb);
	}
}
