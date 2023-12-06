package swea.b형특강.lecture5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_사탕가방 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int MAX_N = 100;
		long[] arr = new long[MAX_N];
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			long M = Long.parseLong(input[1]);
			
			input = br.readLine().split(" ");
			long max = 0;
			for (int i = 0; i < N; i++) {
				arr[i] = Long.parseLong(input[i]);
				max = Math.max(max, arr[i]);
			}
			
			long sum = 0;
			long start = 1;
			long end = max;
			long mid, res = 0;
			while(start <= end) {
				mid = start + (end - start) / 2;
				sum = 0;
				for (int i = 0; i < N; i++) {
					sum += arr[i] / mid;
				}
				if(sum >= M) {
					start = mid + 1;
					res = mid;
				} else {
					end = mid - 1;
				}
			}
			
			sb.append(res).append("\n");
		}
		
		br.close();
		System.out.print(sb);
	}	
}
