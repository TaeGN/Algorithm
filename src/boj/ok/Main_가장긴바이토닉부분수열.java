package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_가장긴바이토닉부분수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		int[] len = new int[N];
		LIS left = new LIS(N);
		LIS right = new LIS(N);
		for (int i = 0; i < N; i++) {
			left.add(arr[i]);
			right.add(arr[N - i - 1]);
			len[i] += left.size();
			len[N - i - 1] += right.size();
		}
		
		System.out.println(Arrays.toString(left.LIS));
		System.out.println(Arrays.toString(right.LIS));
		
		int res = 0;
		for (int i = 0; i < N; i++) {
			res = Math.max(res, len[i] - 1);
		}
		System.out.println(res);
	}
	
	static class LIS {
		int[] LIS;
		int idx;
		public LIS(int size) {
			LIS = new int[size + 1];
			LIS[0] = Integer.MIN_VALUE;
		}
		public void add(int val) {
			if(this.LIS[idx] < val) {
				this.LIS[++idx] = val;
			} else {
				int idx = this.binarySearch(val, 1, this.idx);
				this.LIS[idx] = val;
			}
		}
		public int size() {
			return this.idx;
		}
		private int binarySearch(int val, int start, int end) {
			if(start > end) return start;
			int mid = (start + end)/ 2;
			if(this.LIS[mid] == val) return mid;
			else if(this.LIS[mid] < val) return this.binarySearch(val, mid + 1, end);
			else return this.binarySearch(val, start, mid - 1);
		}
	}
}
