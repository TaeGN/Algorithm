package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_16236_G3_아기상어_노태균 {
	static PriorityQueue<Point> queue = new PriorityQueue<>();
	static int curR, curC, N, cnt, totalTime, curSize = 2, eat = 2;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];
		map = new int[N][N];
		int j, num;
		for(int i = 0; i < N; i++) {
			j = -1;
			for(String s : br.readLine().split(" ")) {
				num = Integer.parseInt(s);
				map[i][++j] = num;
				if(num == 9) {
					curR = i;
					curC = j;
					map[i][j] = 0;
					continue;
				}
				if(num == 0) continue;
				cnt++;
			}
		}
		
		br.close();
		while(cnt > 0) if(!bfs()) break;
		System.out.println(totalTime);
	}
	
	private static boolean bfs() {
		queue.clear();
		init(visited);
		queue.offer(new Point(curR, curC, 0));
		visited[curR][curC] = true;
		int r, c, rr, cc, time = 0;
		Point point;
		while(!queue.isEmpty()) {
			time++;
			for(int j = 0, size = queue.size(); j < size; j++) {
				point = queue.poll();
				r = point.r;
				c = point.c;
				if(map[r][c] != 0 && map[r][c] < curSize) {
					map[r][c] = 0;
					cnt--;
					if(--eat == 0) {
						curSize++;
						eat = curSize;
					}
					curR = r;
					curC = c;
					totalTime += point.time;
					return true;
				}
				
				for(int i = 0; i < 4; i++) {
					rr = r + dr[i];
					cc = c + dc[i];
					if(rr >= N || cc >= N || rr < 0 || cc < 0 || visited[rr][cc] || map[rr][cc] > curSize) continue;
					queue.offer(new Point(rr, cc, time));
					visited[rr][cc] = true;
				}
			}
		}
		return false;
	}
	
	private static void init(boolean[][] visited) {
		for(boolean[] a : visited) {
			Arrays.fill(a, false);
		}
	}
	
	static class Point implements Comparable<Point> {
		int r;
		int c;
		int time;
		
		public Point(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}

		@Override
		public int compareTo(Point o) {
			if(this.time != o.time) return Integer.compare(this.time, o.time);
			if(this.r != o.r) return Integer.compare(this.r, o.r);
			return Integer.compare(this.c, o.c);
		}

	}

}
