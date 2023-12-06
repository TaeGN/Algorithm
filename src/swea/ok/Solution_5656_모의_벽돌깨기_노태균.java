package swea.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_5656_모의_벽돌깨기_노태균 {
	static int N, W, H, max, brickCnt;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] input;
		int T = Integer.parseInt(br.readLine());
		int[][] map;
		for(int tc = 1; tc <= T; tc++) {
			input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			W = Integer.parseInt(input[1]);
			H = Integer.parseInt(input[2]);
			map = new int[H][W];
			brickCnt = 0;
			for(int i = 0; i < H; i++) {
				input = br.readLine().split(" ");
				for(int j = 0; j < W; j++) {
					if((map[i][j] = Integer.parseInt(input[j])) != 0) brickCnt++;
				}
			}
			
			max = 0;
			brickBreaker(0, 0, map);
			sb.append("#").append(tc).append(" ").append(brickCnt - max).append("\n");
		}
		br.close();
		System.out.println(sb);
	}
	
	
	private static void brickBreaker(int n, int cnt, int[][] map) {
		if(cnt == brickCnt || n == N) {
			max = Math.max(max, cnt);
			return;
		}
		
		for(int c = 0; c < W; c++) {	// 모든 칼럼 반복
			int r = -1;
			while(++r < H && map[r][c] == 0);	// 맨 위 블록 구하기
			if(r == H) continue;
			int[][] newMap = deepCopy(map);
			brickBreaker(n + 1, cnt + shot(r, c, newMap), newMap);
		}
		
	}

	// 벽돌 깨기
	private static int shot(int r, int c, int[][] curMap) {
		if(curMap[r][c] == 1) {
			curMap[r][c] = 0;
			return 1;
		}
		
		Queue<Brick> queue = new ArrayDeque<>();
		queue.offer(new Brick(r, c, curMap[r][c]));
		curMap[r][c] = 0;
		int rr, cc, len, cnt = 1;
		Brick brick;
		while(!queue.isEmpty()) {
			for(int i = 0, size = queue.size(); i < size; i++) {
				brick = queue.poll();
				r = brick.r;
				c = brick.c;
				len = brick.num - 1;
				for(int j = 0; j < 4; j++) {
					rr = r;
					cc = c;
					for(int k = 0; k < len; k++) {
						rr += dr[j];
						cc += dc[j];
						if(rr >= H || cc >= W || rr < 0 || cc < 0) break;
						if(curMap[rr][cc] == 0) continue;
						if(curMap[rr][cc] != 1) queue.offer(new Brick(rr, cc, curMap[rr][cc]));
						curMap[rr][cc] = 0;
						cnt++;
					}
				}
			}
		}
		dropBricks(curMap);
		return cnt;
	}
	
	// 빈 공간 메꾸기
	private static void dropBricks(int[][] curMap) {
		for(int c = 0; c < W; c++) {
			int rr = H - 1;
			for(int r = H - 1; r >= 0; r--) {
				if(curMap[r][c] != 0) curMap[rr--][c] = curMap[r][c];
			}
			while(rr >= 0) curMap[rr--][c] = 0;
		}
	}
	
	// deepCopy
	static int[][] deepCopy(int[][] curMap) {
		int[][] newMap = new int[H][];
		for(int i = 0; i < H; i++) {
			newMap[i] = curMap[i].clone();
		}
		return newMap;
	}
	
	static class Brick {
		int r;
		int c;
		int num;
		
		public Brick(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}
}
