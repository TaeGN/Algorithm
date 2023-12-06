package boj.P4_2424_부산의해적;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main1 {
	static int N, M, end;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static final char ISLAND = 'I', PIRATE = 'V', WATER = '.', VISITED = '0';
	static char[][] map;
	static boolean[][][] visited;
	static Queue<Integer> pirate = new ArrayDeque<>();
	static Queue<Integer> sua = new ArrayDeque<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		map = new char[N][M];
		visited = new boolean[N][M][4];
		for (int i = 0; i < N; i++) {
			int j = 0;
			for(char c : br.readLine().toCharArray()) {
				switch(c) {
				case 'Y':
					sua.offer(i * M + j);
					map[i][j] = VISITED;
					break;
				case 'V':
					pirate.offer(i * M + j);
					map[i][j] = ISLAND;
					break;
				case 'T':
					end = i * M + j;
					map[i][j] = WATER;
					break;
				default:
					map[i][j] = c;
					break;
				}
				j++;
			}
		}
		int cnt = 0;
		boolean ok = false;
		movePirate();
		while(!sua.isEmpty()) {
			if(moveSua()) break;
			movePirate();
			print(++cnt);
			if(map[end / M][end % M] == PIRATE) break;
		}
		System.out.println(ok ? "YES" : "NO");
		
	}
	
	private static boolean moveSua() {
		int pos, r, c, rr, cc;
		for (int i = 0, size = sua.size(); i < size; i++) {
			pos = sua.poll();
			r = pos / M;
			c = pos % M;
//			if(map[r][c] == PIRATE) continue;
			for (int j = 0; j < 4; j++) {
				rr = r + dr[j];
				cc = c + dc[j];
				if(boundaryCheck(rr, cc) || map[rr][cc] != WATER) continue;
				if((rr * M + cc) == end) return true;
				sua.offer(rr * M + cc);
				map[rr][cc] = VISITED;
			}
		}
		
		return false;
	}

	private static void movePirate() {
		int pos, r, c, rr, cc;
		if(pirate.isEmpty()) return;
		for (int i = 0, size = pirate.size(); i < size; i++) {
			pos = pirate.poll();
			r = pos / M;
			c = pos % M;
			map[r][c] = ISLAND;
			for (int j = 0; j < 4; j++) {
				rr = r + dr[j];
				cc = c + dc[j];
				if(boundaryCheck(rr, cc) || map[rr][cc] == ISLAND) continue;
				pirate.offer(rr * M + cc);
				map[rr][cc] = ISLAND;
				while(!boundaryCheck(rr += dr[j], cc += dc[j])) {
					if(visited[rr][cc][j] || map[rr][cc] == ISLAND) break;
					visited[rr][cc][j] = true;
					map[rr][cc] = PIRATE;
				}
			}
		}
	}

	static boolean boundaryCheck(int r, int c) {
		return r >= N || r < 0 || c >= M || c < 0;
	}
	
	static void print(int cnt) {
		System.out.println(cnt);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
