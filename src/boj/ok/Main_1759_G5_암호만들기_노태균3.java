package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main_1759_G5_암호만들기_노태균3 {
	static StringBuilder sb = new StringBuilder();
	static Set<Character> vowels = new HashSet<>();
	static int L, C;
	static char[] password, words;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		L = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		words = new char[C];
		password = new char[L];
		char c;
		int i = 0;
		for(String s : br.readLine().split(" ")) {
			c = s.charAt(0);
			switch(c) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				vowels.add(c);
				break;
			}
			words[i++] = c;
		}
		br.close();
		Arrays.sort(words);
		getPassword(0, 0, 0, 0);
		System.out.println(sb);
	}
	
	private static void getPassword(int cnt, int idx, int a, int b) {
		if(cnt == L) {
			if(a >= 1 && b >= 2) sb.append(String.valueOf(password)).append("\n");
			return;
		}
		
		if(idx == C) return;
		char c = password[cnt] = words[idx];
		if(vowels.contains(c)) getPassword(cnt + 1, idx + 1, a + 1, b);
		else getPassword(cnt + 1, idx + 1, a, b + 1);
		getPassword(cnt, idx + 1, a, b);
	}
}
