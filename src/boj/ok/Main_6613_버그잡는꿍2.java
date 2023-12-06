package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_6613_버그잡는꿍2 {
	static int N, fail[];
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		String[] input;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while((input = br.readLine().split(" ")) != null) {
				N = Integer.parseInt(input[0]);
				String bug = input[1];
				int pLen = bug.length();
				fail = new int[pLen];
				
				for (int i = 1, j = 0; i < pLen; i++) {
					while(j > 0 && bug.charAt(i) != bug.charAt(j)) {
						j = fail[j - 1];
					}
					
					if(bug.charAt(i) == bug.charAt(j)) fail[i] = ++j;
				}
				
				int idx, cLen;
				for (int k = 0; k < N; k++) {
					idx = 0;
					String code = br.readLine();
					cLen = code.length();
					for (int i = 0, j = 0; i < cLen; i++) {
						while(j > 0 && code.charAt(i) != bug.charAt(j)) {
							j = fail[j - 1];
						}
						
						if(code.charAt(i) == bug.charAt(j)) {
							if(++j == pLen) {
								sb.append(code.substring(idx, i - pLen + 1));
								idx = i + 1;
								j = 0;
							}
						}
					}
					if(idx < cLen) sb.append(code.substring(idx, code.length()));
					sb.append("\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(sb);
		}
		
	}
}
