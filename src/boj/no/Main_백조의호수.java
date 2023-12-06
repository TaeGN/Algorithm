package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_백조의호수 {
	static int R, C;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static char[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		map = new char[R][C];
		String s;
		char ch;
		int[] pos = new int[2];
		int idx = 0;
		for (int i = 0; i < R; i++) {
			s = br.readLine();
			for (int j = 0; j < C; j++) {
				ch = s.charAt(j);
				if(ch == 'L') {
					pos[idx++] = i * C + j;
					map[i][j] = '.';
				} else {
					map[i][j] = ch;
				}
			}
		}
		int start = pos[0];
		int end = pos[1];
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(start, 0));
		map[start / C][start % C] = 'O';
		
		Point cur;
		int r, c, rr, cc, cCnt, nCnt, result = 0;
		bfs:while(!pq.isEmpty()) {
			cur = pq.poll();
			r = cur.rc / C;
			c = cur.rc % C;
			cCnt = cur.cnt;
			for (int i = 0; i < 4; i++) {
				rr = r + dr[i];
				cc = c + dc[i];
				if(boundaryCheck(rr, cc) || map[rr][cc] == 'O') continue;
				if(map[rr][cc] == '.') nCnt = cCnt;
				else nCnt = cCnt + 1;
				if(rr * C + cc == end) {
					result = nCnt;
					break bfs;
				}
				pq.offer(new Point(rr, cc, nCnt));
				map[rr][cc] = 'O';
			}
		}
		System.out.println((result + 1) / 2);
	}
	
	private static boolean boundaryCheck(int rr, int cc) {
		return rr >= R || rr < 0 || cc >= C || cc < 0;
	}

	static class Point implements Comparable<Point> {
		int rc, cnt;

		public Point(int rc, int cnt) {
			super();
			this.rc = rc;
			this.cnt = cnt;
		}

		public Point(int r, int c, int cnt) {
			super();
			this.rc = r * C + c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.cnt, o.cnt);
		}
	}
}
