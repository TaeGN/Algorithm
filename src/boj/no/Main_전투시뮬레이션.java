package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_전투시뮬레이션 {
	
	static final int INF = Integer.MAX_VALUE;
	static final int[] dr = {0,-1,0,1};
	static final int[] dc = {1,0,-1,0};
	static int N, H, W, M;
	static int[][] map;
	static Unit[][] unitMap;
	static Unit[] unitList;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = readInt(input[0]);
		H = readInt(input[1]);
		W = readInt(input[2]);
		
		map = new int[H][W];
		unitMap = new Unit[H][W];
		visited = new boolean[H][W];
		
		// 맵 생성
		for (int i = 0; i < H; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < W; j++) {
				map[i][j] = readInt(input[j]);
			}
		}
		
		// 험준도 배열 생성
		int[] nArr = new int[N + 1];
		input = br.readLine().split(" ");
		for (int i = 1; i <= N; i++) {
			nArr[i] = readInt(input[i - 1]);
			if(nArr[i] == -1) nArr[i] = INF;
		}
		
		// 맵에 험준도 적용
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = nArr[map[i][j]];
			}
		}
		
		// 유닛 리스트 생성 & 유닛 맵 채우기
		M = readInt(br.readLine());
		unitList = new Unit[M + 1];
		
		for (int i = 1; i <= M; i++) {
			input = br.readLine().split(" ");
			unitList[i] = new Unit(readInt(input[0]), readInt(input[1]), readInt(input[2]), readInt(input[3]));
			unitMap[unitList[i].r][unitList[i].c] = unitList[i];
		}
		
//		int r, c;
//		// 교전 중 표시
//		for (int i = 1; i <= M; i++) {
//			Unit unit = unitList[i];
//			if(unit.fight) continue;
//			for (int j = 0; j < 4; j++) {
//				r = unit.r + dr[j];
//				c = unit.c + dc[j];
//				if(!isValid(r, c)) continue;
//				Unit other = unitMap[r][c];
//				if(other == null || unit.team == other.team) continue;
//				unit.fight = other.fight = true;
//			}
//		}
		
		// 이동 명령
		int K = readInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			unitList[readInt(input[0])].move(readInt(input[1]), readInt(input[2]));
			visitedInit();
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= M; i++) {
			Unit unit = unitList[i];
			sb.append(unit.r).append(" ").append(unit.c).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static int readInt(String s) {
		return Integer.parseInt(s);
	}
	
	static boolean isValid(int r, int c) {
		return r < H && c < W && r >= 0 && c >= 0;
	}
	
	static void visitedInit() {
		for (int i = 0; i < H; i++) {
			Arrays.fill(visited[i], false);
		}
	}
	
	static class Unit {
		int mobility, team, r, c;
		boolean fight;

		public Unit(int mobility, int team, int r, int c) {
			super();
			this.mobility = mobility;
			this.team = team;
			this.r = r;
			this.c = c;
		}
		
		public boolean move(int er, int ec) {
			// 이동 불가 지형, 목표 지점에 다른 유닛 있는 경우 제외
			if(map[er][ec] > this.mobility || unitMap[er][ec] != null) return false;
					
			// 현재 교전중인 상태인지 구하기
			int rr, cc;
			for (int i = 0; i < 4; i++) {
				rr = this.r + dr[i];
				cc = this.c + dc[i];
				if(!isValid(rr, cc)) continue;
				Unit unit = unitMap[rr][cc];
				if(unit != null && this.team != unit.team) {
					this.fight = true;
					break;
				}
			}
			
			int r = this.r;
			int c = this.c;
			// dfs
			if(!this.move(this.r, this.c, er, ec, this.mobility)) {
				this.r = r;
				this.c = c;
				return false;
			}
			unitMap[this.r][this.c] = null;
			this.r = er;
			this.c = ec;
			unitMap[this.r][this.c] = this;
			return true;
		}

		public boolean move(int r, int c, int er, int ec, int mobility) {
			this.r = r;
			this.c = c;
			visited[r][c] = true;
			print();
			
			// 목표지점 도착
			if(r == er & c == ec) return true;
			
			int rr, cc;
			if(!this.fight) {
				for (int i = 0; i < 4; i++) {
					rr = r + dr[i];
					cc = c + dc[i];
					if(!isValid(rr, cc)) continue;
					Unit unit = unitMap[rr][cc];
					if(unit != null && this.team != unit.team) return false;
				}
			} else {
				this.fight = false;
			}
			
			for (int i = 0; i < 4; i++) {
				rr = r + dr[i];
				cc = c + dc[i];
				if(!isValid(rr, cc) || visited[rr][cc] || map[rr][cc] > mobility) continue;
				if(unitMap[rr][cc] != null && unitMap[rr][cc].team != this.team) continue;
				if(move(rr, cc, er, ec, mobility - map[rr][cc])) return true;
			}
			
			visited[r][c] = false;
			return false;
		}
	}
	static void print() {
		StringBuilder sb = new StringBuilder();
		int[][] map = new int[H][W];
		for (int i = 1; i <= M; i++) {
			map[unitList[i].r][unitList[i].c] = i;
		}
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
