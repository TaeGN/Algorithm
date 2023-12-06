package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main_1194_G1_달이차오른다가자_노태균 {
	static int N, M;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		char[][] map = new char[N][];
		boolean[][][] visited = new boolean[N][M][];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		Point cur = null;
		char word;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				word = map[i][j];
				if(word != '#') visited[i][j] = new boolean[1 << 6];
				if(word == '0') cur = new Point(i, j, 0);
			}
		}
		int[] dr = {0,1,0,-1};
		int[] dc = {1,0,-1,0};
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(cur);
		int r, c, k, rr, cc, kk, cnt = 0;
		boolean ok = false;
		a:while(!queue.isEmpty()) {
			cnt++;
			for(int i = 0, size = queue.size(); i < size; i++) {
				cur = queue.poll();
				r = cur.r;
				c = cur.c;
				k = cur.k;
				for(int j = 0; j < 4; j++) {
					rr = r + dr[j];
					cc = c + dc[j];
					kk = k;
					if(isBoundary(rr, cc) || map[rr][cc] == '#') continue;
					word = map[rr][cc];
					if(word == '1') {
						ok = true;
						break a;
					}
					if(Character.isUpperCase(word) && (k & 1 << (word - 'A')) == 0) continue; 
					else if(Character.isLowerCase(word)) kk = k | 1 << (word - 'a');
					
					if(!visited[rr][cc][kk]) {
						visited[rr][cc][kk] = true;
						queue.offer(new Point(rr, cc, kk));
					}
				}
			}
		}
		if(ok) System.out.println(cnt);
		else System.out.println(-1);
	}
	
	private static boolean isBoundary(int rr, int cc) {
		return rr >= N || rr < 0 || cc >= M || cc < 0;
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
