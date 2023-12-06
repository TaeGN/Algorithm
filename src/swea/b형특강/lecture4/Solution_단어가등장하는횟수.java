package swea.b형특강.lecture4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_단어가등장하는횟수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		int[] fail = new int[100000];
		
		for (int tc = 1; tc <= T; tc++) {
			//init
			Arrays.fill(fail, 0);
			
			char[] input = br.readLine().toCharArray();
			char[] word = br.readLine().toCharArray();
			int inputLen = input.length;
			int wordLen = word.length;
			
			for (int i = 1, j = 0; i < wordLen; i++) {
				while(j > 0 && word[i] != word[j]) {
					j = fail[j - 1];
				}
				if(word[i] == word[j]) fail[j] = ++j;
			}
			
			int cnt = 0;
			for (int i = 0, j = 0; i < inputLen; i++) {
				while(j > 0 && input[i] != word[j]) {
					j = fail[j - 1];
				}
				if(input[i] == word[j]) {
					if(++j == wordLen) {
						j = fail[j - 1];
						cnt++;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}	
}
