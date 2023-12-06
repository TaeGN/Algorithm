package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_뱀 {
	
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int N, M;
	static final int WALL = -2;
	static final int GROUND = -1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				if(str.charAt(j) == '1') map[i][j] = WALL;
				else map[i][j] = GROUND;
			}
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		List<Integer> moveCnt = new ArrayList<>();
		
		int idx = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					int cnt = bfs(i, j, map, queue, ++idx);
					moveCnt.add(cnt);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					sb.append(0);
				} else {
					sb.append(getMoveCnt(i, j, moveCnt, map));
				}
			}
			sb.append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static int getMoveCnt(int r, int c, List<Integer> moveCnt, int[][] map) {
		int cnt = 0;
		
		int rr, cc;
		for (int i = 0; i < 4; i++) {
			rr = r + dr[i];
			cc = c + dc[i];
			if(!isValid(rr, cc) || map[rr][cc] == WALL) continue;
			cnt += moveCnt.get(map[rr][cc]);
		}
		
		return cnt;
	}

	static int bfs(int r, int c, int[][] map, Queue<Integer> queue, int idx) {
		queue.clear();
		queue.offer(r * M + c);
		map[r][c] = idx;
		
		int rc, rr, cc, cnt = 0;
		while(!queue.isEmpty()) {
			rc = queue.poll();
			r = rc / M;
			c = rc % M;
			cnt++;
			
			for (int i = 0; i < 4; i++) {
				rr = r + dr[i];
				cc = c + dc[i];
				if(!isValid(rr, cc) || map[rr][cc] != GROUND) continue;
				map[rr][cc] = idx;
				queue.offer(rr * M + cc);
			}
		}
		
		return cnt;
	}

	static boolean isValid(int r, int c) {
		return r < N && c < M && r >= 0 && c >= 0;
	}
}
	
