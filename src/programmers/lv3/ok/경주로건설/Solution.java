package programmers.lv3.ok.경주로건설;

import java.util.Arrays;

public class Solution {
	
	int[] dr = {0,1,0,-1};
	int[] dc = {1,0,-1,0};
	int N, M;
	final int STRAIGHT = 100;
	final int CURVE = 500 + STRAIGHT;
	final int INF = Integer.MAX_VALUE >> 2;
	
    public int solution(int[][] board) {
        N = board.length;
        M = board[0].length;
        
        int[][][] map = new int[N][M][4];
        
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j] == 0) Arrays.fill(map[i][j], INF);
			}
		}
        Arrays.fill(map[0][0], 0);
        
        dfs(0, 0, map);
        
        for (int k = 0; k < 4; k++) {
        	for (int i = 0; i < N; i++) {
        		for (int j = 0; j < M; j++) {
        			System.out.print((map[i][j][k] == INF ? -1 : map[i][j][k]) + " ");
        		}
        		System.out.println();
        	}
        	System.out.println();
		}
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
			res = Math.min(map[N - 1][M - 1][i], res);
		}
        return res;
    }

	private void dfs(int r, int c, int[][][] map) {
		int rr, cc;
		for (int i = 0; i < 4; i++) {
			rr = r + dr[i];
			cc = c + dc[i];
			if(!isValid(rr, cc)) continue;
			int val = Math.min(map[r][c][(i + 1) % 4], map[r][c][(i + 3) % 4]) + CURVE;
			val = Math.min(map[r][c][i] + STRAIGHT, val);
			if(map[rr][cc][i] > val) {
				map[rr][cc][i] = val;
				dfs(rr, cc, map);
			}
		}
	}
	
	boolean isValid(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}
