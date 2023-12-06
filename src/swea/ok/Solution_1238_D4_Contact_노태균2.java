package swea.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Solution_1238_D4_Contact_노태균2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] input;
		int T = 10;
		int N, M, from, to, cur, max = 0, curMax;
		for(int tc = 1; tc <= T; tc++) {
			input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			input = br.readLine().split(" ");
			for(int i = 0; i < N / 2; i++) {
				from = Integer.parseInt(input[i * 2]);
				to = Integer.parseInt(input[i * 2 + 1]);
			}
			
			init();
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static int[] set = new int[101];
	static int[] rank = new int[101];
	
	static void init() {
		Arrays.fill(set, 0);
		Arrays.fill(rank, 0);
	}
	
	static int findSet(int a) {
		if(set[a] == 0) return a;
		return findSet(a); 
	}
	
}
