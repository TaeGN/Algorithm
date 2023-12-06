/package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_2568_전깃줄2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Deque<Point> deque = new ArrayDeque<>();
		int N = Integer.parseInt(br.readLine());
		Point[] arr = new Point[N + 2];
		arr[0] = new Point(0, -1);
		arr[N + 1] = new Point(N + 1, -1);
		deque.push(arr[0]);
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(br.readLine());
			arr[i] = new Point(i, num);
			while(deque.peek().h >= num) deque.pop();
			arr[i].w += (i - deque.peek().idx - 1);
			deque.push(arr[i]);
		}
		
		deque.clear();
		deque.push(arr[N + 1]);
		for (int i = N; i >= 1; i--) {
			Point cur = arr[i];
			while(deque.peek().h >= cur.h) deque.pop();
			cur.w += (deque.peek().idx - i - 1);
			deque.push(arr[i]);
		}
		
		long max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, arr[i].getArea());
		}
		sb.append(max).append("\n");
		br.close();
		System.out.println(sb);
	}
	
	static class Point {
		int idx, h, w;

		public Point(int idx, int h) {
			super();
			this.idx = idx;
			this.h = h;
			this.w = 1;
		}
		
		public long getArea() {
			return (long) h * w;
		}
	}
}