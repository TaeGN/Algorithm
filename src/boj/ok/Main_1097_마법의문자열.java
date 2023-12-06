package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1097_마법의문자열 {
	static int N, R, magicCnt, numbers[];
	static String[] wordArr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		wordArr = new String[N];
		for (int i = 0; i < wordArr.length; i++) {
			wordArr[i] = br.readLine();
		}
		R = Integer.parseInt(br.readLine());
		numbers = new int[N];
		permutation(0, 0);
		System.out.println(magicCnt);
	}
	
	private static void permutation(int cnt, int flag) {
		if(cnt == N) {
			if(isMagicWord()) magicCnt++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if((flag & 1 << i) != 0) continue;
			numbers[cnt] = i;
			permutation(cnt + 1, flag | 1 << i);
		}
	}
	
	private static boolean isMagicWord() {
		for(int idx : numbers) {
			sb.append(wordArr[idx]);
		}
		String pattern = sb.toString();
		int pLen = pattern.length();
		sb.append(pattern.substring(0, pLen - 1));
		String text = sb.toString();
		int tLen = text.length();
		int[] fail = new int[pLen];
		
		for (int i = 1, j = 0; i < pLen; i++) {
			while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = fail[j - 1];
			}
			
			if(pattern.charAt(i) == pattern.charAt(j)) fail[i] = ++j;
		}
		
		int cnt = 0;
		for (int i = 0, j = 0; i < tLen; i++) {
			while(j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = fail[j - 1];
			}
			
			if(text.charAt(i) == pattern.charAt(j)) {
				if(++j == pLen) {
					if(++cnt > R) break;
					j = fail[j - 1];
				}
			}
		}
		sb.setLength(0);
		return cnt == R;
	}
}
