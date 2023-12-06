package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main_치즈 {
	
	static final int OUTSIDE = -1;
	static final int INSIDE = 0;
	static final int CHEESE = 1;
	static final int REMOVE_CHEESE = 2;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int[][] map;
	static int N, M;
	static Set<Integer> edgeCheese, removeCheese;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		map = new int[N][M];
		edgeCheese = new HashSet<>();
		removeCheese = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		print(map);
		dfs(0,0);
		
		int time = 0;
		while(!edgeCheese.isEmpty()) {
			// 시간 증가
			time++;
			// 치즈 없애기
			removeCheese();
		}
		
		System.out.println(time);
	}
	
	static void print(Set<Integer> set) {
		int[][] map = new int[N][M];
		for (int rc : set) {
			map[rc / M][rc % M] = 1;
		}
		print(map);
	}
	
	static void print(int[][] map) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
//				if(map[i][j] >= 0) {
//					sb.append(" ");
//				}
				sb.append(map[i][j] == -1 ? 0 : map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void removeCheese() {
		int r, c, cnt;
		for (int rc : edgeCheese) {
			r = rc / M;
			c = rc % M;
			cnt = 0;
			for (int i = 0; i < 4; i++) {
				if(map[r + dr[i]][c + dc[i]] == OUTSIDE) cnt++;
			}
			if(cnt >= 2) {
				map[r][c] = REMOVE_CHEESE;
				removeCheese.add(rc);
			}
		}
		
		for (int rc : removeCheese) {
			dfs(rc / M, rc % M);
		}
		
		edgeCheese.removeAll(removeCheese);
		removeCheese.clear();
	}
	
	static void dfs(int r, int c) {
		map[r][c] = OUTSIDE;
		
		int rr, cc;
		for (int i = 0; i < 4; i++) {
			rr = r + dr[i];
			cc = c + dc[i];
			if(!isValid(rr, cc)) continue;
			
			switch(map[rr][cc]) {
			case OUTSIDE:
				break;
			case INSIDE:
				dfs(rr, cc);
				break;
			case CHEESE:
				edgeCheese.add(rr * M + cc);
				break;
			}
		}
	}
	
	static boolean isValid(int r, int c) {
		return r < N && c < M && r >= 0 && c >= 0;
	}
}
