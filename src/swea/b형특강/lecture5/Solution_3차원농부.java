package swea.b형특강.lecture5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_3차원농부 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int MAX_N = 500000;
		int[] cow = new int[MAX_N];
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int M = Integer.parseInt(input[1]);
			
			input = br.readLine().split(" ");
			int c1 = Integer.parseInt(input[0]);
			int c2 = Integer.parseInt(input[1]);
			
			input = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				cow[i] = Integer.parseInt(input[i]);
			}
			Arrays.sort(cow, 0, N);
			
			int min = Integer.MAX_VALUE;
			int cnt = 0;
			input = br.readLine().split(" ");
			for (int i = 0; i < M; i++) {
				int num = Integer.parseInt(input[i]);
				
				int idx = Arrays.binarySearch(cow, 0, N, num);
				if(idx >= 0) {
					if(min > 0) {
						min = 0;
						cnt = 1;
					} else {
						cnt++;
					}
				} else {
					idx = -idx - 1;
					
					if(idx < N) {
						if(min > cow[idx] - num) {
							min = cow[idx] - num;
							cnt = 1;
						} else if(min == cow[idx] - num) {
							cnt++;
						}
					}
					if(idx > 0) {
						if(min > num - cow[idx - 1]) {
							min = num - cow[idx - 1];
							cnt = 1;
						} else if(min == num - cow[idx - 1]) {
							cnt++;
						}
					}
				}
			}
			
			sb.append(min + Math.abs(c1 - c2)).append(" ").append(cnt).append("\n");
		}
		
		br.close();
		System.out.print(sb);
	}	
	
}
