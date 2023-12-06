package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_17143_G1_낚시왕_노태균 {
	static Shark[] sharkArr;
	static int[][] map;
	static int R, C, M;
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		int r, c;
		Shark shark;
		input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		M = Integer.parseInt(input[2]);
		map = new int[R + 2][C + 2];
		sharkArr = new Shark[M + 1];
		for(int i = 1; i <= M; i++) {
			input = br.readLine().split(" ");
			r = Integer.parseInt(input[0]);
			c = Integer.parseInt(input[1]);
			if(map[r][c] == 0)map[r][c] = i;
			else map[r][c] = fightShark(map[r][c], i);
			sharkArr[i] = new Shark(r, c, input[2], input[3], input[4]);
		}
		int result = 0;
		for(c = 1; c <= C; c++) {
			for(r = 1; r <= R; r++) {
				if(map[r][c] != 0) {
					result += sharkArr[map[r][c]].size;
					sharkArr[map[r][c]] = null;
					map[r][c] = 0;
					break;
				}
			}
			
			reset(map);
			relocation(sharkArr);
			
			for(int i = 1; i <= M; i++) {
				if(sharkArr[i] == null) continue;
				shark = sharkArr[i].move();
				if(map[shark.r][shark.c] == 0) map[shark.r][shark.c] = i;
				else map[shark.r][shark.c] = fightShark(map[shark.r][shark.c], i);
			}
		}
		br.close();
		System.out.println(result);
	}
	
	// sharkArr 빈 공간 재배치 및 M 수정
	private static void relocation(Shark[] sharkArr) {
		int j = 0;
		for(int i = 1; i <= M; i++) {
			if(sharkArr[i] != null) sharkArr[++j] = sharkArr[i];
		}
		M = j;
	}

	// map 초기화
	private static void reset(int[][] map) {
		for(int r = 1; r <= R; r++) {
			Arrays.fill(map[r], 0);
		}
	}

	// 같은 칸에 상어가 겹쳤을 경우
	private static int fightShark(int s1, int s2) {
		Shark sh1 = sharkArr[s1];
		Shark sh2 = sharkArr[s2];
		if (sh1.size > sh2.size) {
			sharkArr[s2] = null;
			return s1;
		}
		sharkArr[s1] = null;
		return s2;
	}

	static class Shark {
		int r, c, speed, d, size;

		public Shark(int r, int c, String s, String d, String z) {
			super();
			this.r = r;
			this.c = c;
			this.speed = Integer.parseInt(s);
			this.d = Integer.parseInt(d);
			this.size = Integer.parseInt(z);
		}
		
		public Shark move() {
			int len = getLen();
			for(int i = 0; i < len; i++) {
				if(isBoundary(r + dr[d], c + dc[d])) d = changeDirection();
				r += dr[d];
				c += dc[d];
			}
			return this;
		}
		
		private int getLen() {
			switch(d) {
			case 1: case 2:
				return speed % (2 * R - 2);
			default:
				return speed % (2 * C - 2);
			}
		}

		private boolean isBoundary(int r, int c) {
			return r > R || c > C || r < 1 || c < 1;
		}
		
		private int changeDirection() {
			switch(d) {
			case 1:
				return 2;
			case 2:
				return 1;
			case 3:
				return 4;
			default:
				return 3;
			}
		}
	}
}
