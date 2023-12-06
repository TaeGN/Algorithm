package swea.b형특강.lecture5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_영어공부 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int MAX_N = 200000;
		int[] arr = new int[MAX_N];
		int[] sum = new int[MAX_N];
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int P = Integer.parseInt(input[1]);
			
			input = br.readLine().split(" ");
			arr[0] = Integer.parseInt(input[0]);
			for (int i = 1; i < N; i++) {
				arr[i] = Integer.parseInt(input[i]);
				sum[i] = sum[i - 1] + (arr[i] - arr[i - 1] - 1);
			}
			
			int max = 0;
			for (int i = 0; i < N; i++) {
				max = Math.max(max, binarySearch(i, N - 1, P, arr, sum));
			}
			sb.append(max).append("\n");
		}
		
		br.close();
		System.out.print(sb);
	}	
	
	static int binarySearch(int idx, int end, int p, int[] arr, int[] sum) {
		int res = 0;
		int start = idx;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(sum[mid] - sum[idx] > p) {
				end = mid - 1;
			} else {
				start = mid + 1;
				res = mid;
			}
		}
		return arr[res] - arr[idx] + 1 + p - (sum[res] - sum[idx]);
	}
}
