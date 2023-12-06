package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_문자열제곱_KMP {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		String input = br.readLine();
		int[] fail = new int[L];
		// kmp
		for (int i = 1, j = 0; i < L; i++) {
			while(j > 0 && input.charAt(i) != input.charAt(j)) {
				j = fail[j - 1];
			}
			if(input.charAt(i) == input.charAt(j)) {
				fail[i] = ++j;
			}
		}
		br.close();
		System.out.println(L - fail[L - 1]);
	}
}
