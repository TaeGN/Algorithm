package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main_16236_G3_아기상어_노태균2 {
	static Queue<Integer> queue = new ArrayDeque<>();
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
//		for(int[] a : map) {
//			for(int b : a) {
//				System.out.print(b);
//			}
//			System.out.println();
//		}
//		System.out.println(curR);
//		System.out.println(curC);
		br.close();
		while(cnt > 0) if(!bfs()) break;
		System.out.println(totalTime);
	}
	
	private static boolean bfs() {
		queue.clear();
		init(visited);
		queue.offer(curR);
		queue.offer(curC);
		visited[curR][curC] = true;
		int r, c, rr, cc, time = 0;
		while(!queue.isEmpty()) {
			time++;
			for(int j = 0, size = queue.size() / 2; j < size; j++) {
				r = queue.poll();
				c = queue.poll();
				for(int i = 0; i < 4; i++) {
					rr = r + dr[i];
					cc = c + dc[i];
					if(rr >= N || cc >= N || rr < 0 || cc < 0 || visited[rr][cc] || map[rr][cc] > curSize) continue;
					if(map[rr][cc] != 0 && map[rr][cc] < curSize) {
						map[rr][cc] = 0;
						cnt--;
						if(--eat == 0) {
							curSize++;
							eat = curSize;
						}
						curR = rr;
						curC = cc;
						totalTime += time;
						return true;
					}
					queue.offer(rr);
					queue.offer(cc);
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

}
