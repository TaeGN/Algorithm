package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1305_광고 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		String text = br.readLine();
		text += text.substring(0, L - 1);
		int[] fail = new int[L];
		int max = 0;
		for(int tc = 0; tc < L; tc++) {
			for (int i = 1, j = 0; i < L; i++) {
				while(j > 0 && text.charAt(i + tc) != text.charAt(j + tc)) {
					j = fail[j - 1];
				}
				if(text.charAt(i + tc) == text.charAt(j + tc)) {
					fail[i] = ++j;
					max = Math.max(fail[i], max);
				}
			}
			
			Arrays.fill(fail, 0);
		}
		System.out.println(L - max);
	}
}
