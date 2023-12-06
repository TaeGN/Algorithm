package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main_1600_G3_말이되고픈원숭이_노태균 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		int K = Integer.parseInt(br.readLine());
		input = br.readLine().split(" ");
		int W = Integer.parseInt(input[0]);
		int H = Integer.parseInt(input[1]);
		boolean[][][] map = new boolean[H + 4][W + 4][K + 1];
		
		for (int i = 2; i < H + 2; i++) {
			int j = 2;
			for (String s : br.readLine().split(" ")) {
				if (s.equals("0")) Arrays.fill(map[i][j], true);
				j++;
			}
		}
		br.close();
		System.out.println(bfs(K, W, H, map));
	}

	private static int bfs(int K, int W, int H, boolean[][][] map) {
		if (W == 1 && H == 1) return 0;
		if (!map[H + 1][W + 1][0]) return -1;
		
		int[] dhr = { 1, 2, 2, 1, -2, -1, -1, -2 };
		int[] dhc = { 2, 1, -1, -2, 1, 2, -2, -1 };
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(2, 2, K));
		int cnt = 0;
		Point cur;
		int r, c, k;
		while (!queue.isEmpty()) {
			cnt++;
			for (int i = 0, size = queue.size(); i < size; i++) {
				cur = queue.poll();
				k = cur.k;
				if (k > 0) {
					for (int j = 0; j < 8; j++) {
						r = cur.r + dhr[j];
						c = cur.c + dhc[j];
						if (r == H + 1 && c == W + 1) return cnt;
						if (map[r][c][k - 1]) {
							map[r][c][k - 1] = false;
							queue.offer(new Point(r, c, k - 1));
						}
					}
				}

				for (int j = 0; j < 4; j++) {
					r = cur.r + dr[j];
					c = cur.c + dc[j];
					if (r == H + 1 && c == W + 1) return cnt;
					if (map[r][c][k]) {
						map[r][c][k] = false;
						queue.offer(new Point(r, c, k));
					}
				}
			}
		}

		return -1;
	}

	static class Point {
		int r, c, k;

		public Point(int r, int c, int k) {
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}
}
