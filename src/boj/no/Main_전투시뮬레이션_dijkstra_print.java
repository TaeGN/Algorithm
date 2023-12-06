package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_전투시뮬레이션_dijkstra_print {
	
	static final int INF = Integer.MAX_VALUE;
	static final int[] dr = {0,-1,0,1};
	static final int[] dc = {1,0,-1,0};
	static int N, H, W, M;
	static int[][] map;
	static Unit[][] unitMap;
	static Unit[] unitList;
	static PriorityQueue<Edge> pq;
	static int[][] dp; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = readInt(input[0]);
		H = readInt(input[1]);
		W = readInt(input[2]);
		
		map = new int[H][W];
		unitMap = new Unit[H][W];
		
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
		
		pq = new PriorityQueue<>();
		dp = new int[H][W]; 
		
		// 이동 명령
		int K = readInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
//			unitList[readInt(input[0])].move(readInt(input[1]), readInt(input[2]));
			Unit unit = unitList[readInt(input[0])];
			unit.move(readInt(input[1]), readInt(input[2]));
			System.out.println(i + "번째 종료");
			System.out.println("r : " + unit.r + ", c : " + unit.c);
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
	
	static void dpInit() {
		for (int i = 0; i < H; i++) {
			Arrays.fill(dp[i], INF);
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
			// dijkstra
			if(!this.move(this.r, this.c, er, ec)) {
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

		public boolean move(int r, int c, int er, int ec) {
			dpInit();
			dp[r][c] = 0;
			pq.clear();
			pq.offer(new Edge(r, c, 0));
			
			dijkstra:while(!pq.isEmpty()) {
				Edge edge = pq.poll();
				r = edge.r;
				c = edge.c;
				// 유효한 값이 아니면 다시 뽑기
				if(edge.mobility > dp[r][c]) continue;
				
				// test용
				this.r = r;
				this.c = c;
				print();
				
				// 목표지점 도착
				System.out.println(r + " : " + c + " - " + er + " : " + ec);
				if(r == er & c == ec) return true;
				
				int rr, cc;
				if(!this.fight) {
					for (int i = 0; i < 4; i++) {
						rr = r + dr[i];
						cc = c + dc[i];
						if(!isValid(rr, cc)) continue;
						Unit unit = unitMap[rr][cc];
						if(unit != null && this.team != unit.team) continue dijkstra;
					}
				} else {
					this.fight = false;
				}
				
				for (int i = 0; i < 4; i++) {
					rr = r + dr[i];
					cc = c + dc[i];
					
					// 경계너머, 이동력 초과 제외
					if(!isValid(rr, cc) || map[rr][cc] + dp[r][c] > this.mobility) continue;
					
					// 다른팀 통과 제외
					if(unitMap[rr][cc] != null && unitMap[rr][cc].team != this.team) continue;
					
					// dp값에 변화가 없으면 제외
					if(dp[rr][cc] <= dp[r][c] + map[rr][cc]) continue;
					
					dp[rr][cc] = dp[r][c] + map[rr][cc];
					pq.offer(new Edge(rr, cc, dp[rr][cc]));
				}
			}
			
			return false;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int r, c, mobility;

		public Edge(int r, int c, int mobility) {
			this.r = r;
			this.c = c;
			this.mobility = mobility;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.mobility, o.mobility);
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
