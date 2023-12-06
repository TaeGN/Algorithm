package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_6613_버그잡는꿍 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		String s, input[];
		int N, fail[] = new int[1000];
		char[] code, bug;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			for(s = br.readLine(); s != null; s = br.readLine()) {
				input = s.split(" ");
				N = Integer.parseInt(input[0]);
				bug = input[1].toCharArray();
				int pLen = bug.length;
				
				for (int i = 1, j = 0; i < pLen; i++) {
					while(j > 0 && bug[i] != bug[j]) {
						j = fail[j - 1];
					}
					
					if(bug[i] == bug[j]) fail[i] = ++j;
				}
				
				int idx, cLen;
				for (int k = 0; k < N; k++) {
					idx = 0;
					code = br.readLine().toCharArray();
					cLen = code.length;
					for (int i = 0, j = 0; i < cLen; i++) {
						while(j > 0 && code[i] != bug[j]) {
							j = fail[j - 1];
						}
						
						if(code[i] == bug[j]) {
							if(++j == pLen) {
								for (int l = idx; l <= i - pLen; l++) {
									sb.append(code[l]);
								}
								idx = i + 1;
								j = 0;
							}
						}
					}
					if(idx < cLen) {
						for (int l = idx; l < cLen; l++) {
							sb.append(code[l]);
						}
					}
					sb.append("\n");
				}
			}
			br.close();
		} finally {
			System.out.println(sb);
		}
		
	}
}
