package swea.b형특강.lecture2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_파핑파핑지뢰찾기 {
	
	static int[] dr = {0,0,1,-1,1,1,-1,-1};
	static int[] dc = {1,-1,0,0,1,-1,1,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		final int LAND_MINE = Integer.MIN_VALUE;
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N + 2][N + 2];
			
			// map 가장자리 표시
			Arrays.fill(map[0], LAND_MINE);
			Arrays.fill(map[N + 1], LAND_MINE);
			for (int i = 1; i <= N; i++) {
				map[i][0] = map[i][N + 1] = LAND_MINE;
			}
			
			// 지뢰 주변 cnt++
			for (int i = 1; i <= N; i++) {
				char[] input = br.readLine().toCharArray();
				for (int j = 1; j <= N; j++) {
					if(input[j - 1] == '*') {
						map[i][j] = LAND_MINE;
						for (int d = 0; d < 8; d++) {
							map[i + dr[d]][j + dc[d]]++;
						}
					}
				}
			}
			
			int res = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(map[i][j] != 0) continue;
					findMine(i, j, map);
					res++;
				}
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(map[i][j] > 0) res++;
				}
			}
			
			sb.append(res).append("\n");
		}
		br.close();
		System.out.println(sb);
	}

	private static void findMine(int r, int c, int[][] map) {
		// 방문체크
		map[r][c] = -1;
		
		int rr, cc;
		for (int d = 0; d < 8; d++) {
			rr = r + dr[d];
			cc = c + dc[d];
			if(map[rr][cc] > 0) map[rr][cc] = -1;
			else if(map[rr][cc] == 0) findMine(rr, cc, map);
		}
	}
}
