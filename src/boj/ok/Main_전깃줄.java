package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_전깃줄 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Line[] line = new Line[N];
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			line[i] = new Line(start, end);
		}
		Arrays.sort(line);
		
		int[] LIS = new int[N + 1];
		int size = 0;
		LIS[0] = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			int val = line[i].end;
			if(val > LIS[size]) {
				LIS[++size] = val;
			} else {
				int idx = binarySearch(val, LIS, 1, size);
				LIS[idx] = val;
			}
		}
		
		System.out.println(N - size);
	}
	
	static int binarySearch(int val, int[] LIS, int start, int end) {
		if(start > end) return start;
		int mid = (start + end) / 2;
		if(LIS[mid] == val) return mid;
		else if(LIS[mid] < val) return binarySearch(val, LIS, mid + 1, end);
		return binarySearch(val, LIS, start, mid - 1);
	}

	static class Line implements Comparable<Line> {
		int start, end;
		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Line o) {
			return Integer.compare(this.start, o.start);
		}
	}
}
