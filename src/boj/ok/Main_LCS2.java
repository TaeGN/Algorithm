package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// dp LCS
public class Main_LCS2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] word1 = br.readLine().toCharArray();
		char[] word2 = br.readLine().toCharArray();
		int len1 = word1.length;
		int len2 = word2.length;
		
		int[][] LCS = new int[len1 + 1][len2 + 1];
		for (int i = 1; i <= len1; i++) {
			char c = word1[i - 1];
			for (int j = 1; j <= len2; j++) {
				if(c == word2[j - 1]) LCS[i][j] = LCS[i - 1][j - 1] + 1;
				else LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
			}
		}
		print(LCS);
		
		sb.append(LCS[len1][len2]).append("\n");
		
		Deque<Character> stack = new ArrayDeque<>();
		for (int idx1 = len1, idx2 = len2; idx1 >= 1 && idx2 >= 1;) {
			if(word1[idx1 - 1] == word2[idx2 - 1]) {
				stack.push(word1[idx1 - 1]);
				idx1--;
				idx2--;
			} else {
				if(LCS[idx1][idx2] == LCS[idx1 - 1][idx2]) idx1--;
				else idx2--;
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		br.close();
		System.out.println(sb);
	}
	
	public static void print(int[][] LCS) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < LCS.length; i++) {
			for (int j = 1; j < LCS[0].length; j++) {
				sb.append(LCS[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
