package boj.P4_2424_부산의해적;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main_정답 {
	static int N, M;
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
		int end = 0;
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
		
		int pPos, sPos, r, c, rr, cc, rrr, ccc, cnt = 0;
		for (int i = 0; i < 4; i++) {
			pPos = pirate.peek();
			r = pPos / M;
			c = pPos % M;
			while(!boundaryCheck(r += dr[i], c += dc[i])) {
				if(visited[r][c][i] || map[r][c] == ISLAND) break;
				visited[r][c][i] = true;
				map[r][c] = PIRATE;
			}
		}
		
		boolean ok = false;
		bfs:while(!sua.isEmpty()) {
			for (int i = 0, size = pirate.size(); i < size; i++) {
				pPos = pirate.poll();
				r = pPos / M;
				c = pPos % M;
				map[r][c] = ISLAND;
				for (int j = 0; j < 4; j++) {
					rr = r + dr[j];
					cc = c + dc[j];
					if(boundaryCheck(rr, cc) || map[rr][cc] == ISLAND) continue;
					pirate.offer(rr * M + cc);
					map[rr][cc] = ISLAND;
					for (int k = 0; k < 4; k++) {
						rrr = rr;
						ccc = cc;
						while(!boundaryCheck(rrr += dr[k], ccc += dc[k])) {
							if(visited[rrr][ccc][k] || map[rrr][ccc] == ISLAND) break;
							visited[rrr][ccc][k] = true;
							map[rrr][ccc] = PIRATE;
						}
					}
				}
			}
			
			for (int i = 0, size = sua.size(); i < size; i++) {
				sPos = sua.poll();
				r = sPos / M;
				c = sPos % M;
				for (int j = 0; j < 4; j++) {
					rr = r + dr[j];
					cc = c + dc[j];
					if(boundaryCheck(rr, cc) || map[rr][cc] != WATER) continue;
					if((rr * M + cc) == end) {
						ok = true;
						break bfs;
					}
					sua.offer(rr * M + cc);
					map[rr][cc] = VISITED;
				}
			}
			
//			print(++cnt);
			if(map[end / M][end % M] == PIRATE) break;
		}
		System.out.println(ok ? "YES" : "NO");
		
	}

	static boolean boundaryCheck(int r, int c) {
		return r >= N || r < 0 || c >= M || c < 0;
	}
	
//	static void print(int cnt) {
//		System.out.println(cnt);
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
}
