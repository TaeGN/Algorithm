package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_6613_버그잡는꿍3 {
	static int N, fail[] = new int[1000];
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		String s, input[];
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			for(s = br.readLine(); s != null; s = br.readLine()) {
				input = s.split(" ");
				N = Integer.parseInt(input[0]);
				String bug = input[1];
				int pLen = bug.length();
				
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
			br.close();
		} catch (Exception e) {
			System.out.println(sb);
		}
		
	}
}
