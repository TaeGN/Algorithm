package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1109_섬 {
	static int[] dr = {-1, 1, 0, 0, 1, 1, -1, -1};
	static int[] dc = {0, 0, 1, -1, 1, -1, 1, -1};
	static int[][] map;
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			int j = 0;
			for(char c : br.readLine().toCharArray()) {
				if(c == '*') map[i][j] = -1;
				j++;
			}
		}
		
		islandSetting();
	}
	
	private static void islandSetting() {
		int num = 1;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == -1) setIsland(r, c, num++);
			}
		}
		
	}
	
	private static void setIsland(int r, int c, int num) {
		map[r][c] = num;
		int rr, cc;
		for(int i = 0; i < 8; i++) {
			rr = r + dr[i];
			cc = c + dc[i];
			if(rr >= N || cc >= M || rr < 0 || cc < 0 || map[rr][cc] == 0) continue;
			setIsland(rr, cc, num);
		}
	}
}
