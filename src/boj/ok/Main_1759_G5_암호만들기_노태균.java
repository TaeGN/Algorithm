package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main_1759_G5_암호만들기_노태균 {
	static StringBuilder sb = new StringBuilder();
	static Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
	static int L, C;
	static char[] password, words;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		L = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		password = new char[L];
		words = new char[C];
		int i = 0;
		for(String s : br.readLine().split(" ")) {
			words[i++] = s.charAt(0);
		}
		br.close();
		Arrays.sort(words);
		getPassword(0, 0, 0, 0);
		System.out.println(sb);
	}
	
	// a = 모음 개수, b = 자음 개수
	private static void getPassword(int cnt, int idx, int a, int b) {
		if(cnt == L) {
			if(a >= 1 && b >= 2) sb.append(String.valueOf(password)).append("\n");
			return;
		}
		
		if(idx == C) return;
		if(vowels.contains(password[cnt] = words[idx])) getPassword(cnt + 1, idx + 1, a + 1, b);
		else getPassword(cnt + 1, idx + 1, a, b + 1);
		getPassword(cnt, idx + 1, a, b);
	}
}
