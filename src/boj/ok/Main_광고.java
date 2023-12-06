package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_광고 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = null;
		while(!(input = br.readLine()).equals(".")) {
			int len = input.length();
			int[] fail = new int[len];
			for (int i = 1, j = 0; i < len; i++) {
				while(j > 0 && input.charAt(i) != input.charAt(j)) {
					j = fail[j - 1];
				}
				if(input.charAt(i) == input.charAt(j)) {
					fail[i] = ++j;
				}
			}
			int aLen = len - fail[len - 1];
			if(len % aLen == 0) sb.append(len / aLen);
			else sb.append(1);
			sb.append("\n");
		}
		br.close();
		System.out.println(sb);
	}
}
