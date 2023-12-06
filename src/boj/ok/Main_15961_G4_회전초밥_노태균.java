package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_15961_G4_회전초밥_노태균 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int d = Integer.parseInt(input[1]);
		int k = Integer.parseInt(input[2]);
		int c = Integer.parseInt(input[3]);
		
		int[] sushiCnt = new int[d + 1];
		int[] sushi = new int[k];
		int[] prevSushi = null;
		int num = 0, cnt = 0, maxCnt = 0;
		for (int i = 0; i < N + k - 1; i++) {
			if(i == k) prevSushi = sushi.clone();
			if(--sushiCnt[sushi[i % k]] == 0) cnt--;
			if(i < N) sushi[i % k] = num = Integer.parseInt(br.readLine());
			else num = prevSushi[i - N];
			if(num != c && sushiCnt[num]++ == 0) maxCnt = Math.max(maxCnt, ++cnt);
		}
		
		System.out.println(maxCnt + 1);
	}
}
