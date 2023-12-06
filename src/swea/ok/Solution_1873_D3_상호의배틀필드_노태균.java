package swea.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1873_D3_상호의배틀필드_노태균 {
	
	static char[][] map;
	static int pos, curR, curC, H, W;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] input;
		int T = Integer.parseInt(br.readLine());
		int N;
		char[] command;
		char[] tank = {'^','v','<','>'};
		for(int tc = 1; tc <= T; tc++) {
			input = br.readLine().split(" ");
			H = Integer.parseInt(input[0]);
			W = Integer.parseInt(input[1]);
			map = new char[H][];
			for(int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			N = Integer.parseInt(br.readLine());
			command = br.readLine().toCharArray();
			
			// 전차 위치와 방향 찾기
			a:for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					switch(map[i][j]) {
					case '^':
						curR = i; curC = j;
						pos = 0;
						break a;
					case 'v':
						curR = i; curC = j;
						pos = 1;
						break a;
					case '<':
						curR = i; curC = j;
						pos = 2;
						break a;
					case '>':
						curR = i; curC = j;
						pos = 3;
						break a;
					}
				}
			}
			
			map[curR][curC] = '.';
			for(char c : command) {
				battleField(c);
			}
			map[curR][curC] = tank[pos];
			
			sb.append("#").append(tc).append(" ");
			for(char[] arr : map) {
				sb.append(String.valueOf(arr)).append("\n");
			}
		}
		
		br.close();
		System.out.println(sb);
	}
	
	// 명령어 수행
	private static void battleField(char c) {
		switch(c) {
		case 'U':
			if(curR - 1 >= 0 && map[curR - 1][curC] == '.') curR--;
			pos = 0;
			return;
		case 'D':
			if(curR + 1 < H && map[curR + 1][curC] == '.') curR++;
			pos = 1;
			return;
		case 'L':
			if(curC - 1 >= 0 && map[curR][curC - 1] == '.') curC--;
			pos = 2;
			return;
		case 'R':
			if(curC + 1 < W && map[curR][curC + 1] == '.') curC++;
			pos = 3;
			return;
		case 'S':
			int nr = curR + dr[pos];
			int nc = curC + dc[pos];
			while(nr >= 0 && nc >= 0 && nr < H && nc < W) {
				switch(map[nr][nc]) {
				case '*':
					map[nr][nc] = '.';
				case '#':
					return;
				}
				nr += dr[pos];
				nc += dc[pos];
			}
			return;
		}
	}
}
