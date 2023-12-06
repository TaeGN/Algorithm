package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_14003_가장긴증가하는부분수열5 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		final int INF = Integer.MAX_VALUE;
		int[] arr = new int[N + 1];
		Arrays.fill(arr, INF);
		arr[0] = Integer.MIN_VALUE;
		int prev = 0; 
		int cur = 0, len = 1, idx = 0;
		for (int i = 0; i < N; i++) {
			cur = Integer.parseInt(input[i]);
			if(arr[len - 1] == cur) continue;
			if(arr[len - 1] < cur) {
				arr[len++] = cur;
//				System.out.println(len + " " + cur);
			}
			else {
				idx = Arrays.binarySearch(arr, cur);
				if(idx < 0) arr[-1-idx] = cur;
			}
		}
//		System.out.println(len - 1);
//		System.out.println(Arrays.toString(arr));
		br.close();
		sb.append(--len).append("\n");
		int num = 0;
		int[] res = new int[len];
		idx = len;
		cur = Integer.MAX_VALUE;
		prev = arr[len];
		for (int i = N - 1; i >= 0; i--) {
			num = Integer.parseInt(input[i]);
//			System.out.println(num + " " + prev + " " + cur);
			if(num >= prev && num < cur) {
//				System.out.println(num);
				res[--idx] = num;
				if(idx == -1) break;
				cur = num;
				prev = arr[--len];
			}
		}
//		System.out.println(Arrays.toString(arr));
		for (int i = 0, end = res.length; i < end; i++) {
			sb.append(res[i]).append(" ");
		}
		System.out.println(sb);
	}
}
