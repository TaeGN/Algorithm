package boj.no;

import java.io.BufferedReader;
/**
 * 
3 5 5
1 1 1 3 3
1 1 1 1 3
1 1 1 1 1
3 1 1 1 1
3 3 1 1 1
1 0 0
3
2 0 0 0
2 1 2 1
2 1 2 3
3
1 2 0
1 2 2
1 2 4

Answer
2 0
2 1
2 3

------

3 3 3
1 2 3
2 3 2
1 2 3
1 2 3
1
9 1 0 0
1
1 2 2

Answer

2 2
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_전투시뮬레이션_dijkstra {
	
	static final int INF = Integer.MAX_VALUE;
	static final int[] dr = {0,-1,0,1};
	static final int[] dc = {1,0,-1,0};
	static int N, H, W, M;
	static int[][] map;
	static boolean[][] isOk;
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
		
		isOk = new boolean[H][W];
		pq = new PriorityQueue<>();
		dp = new int[H][W]; 
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
		
		// 이동 명령
		int K = readInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			unitList[readInt(input[0])].move(readInt(input[1]), readInt(input[2]));
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
	
	static void dpInit(int r, int c, int param) {
		for (int i = r - 20 < 0 ? 0 : r - 20, end = (r + 20 >= H - 1 ? H - 1 : r + 20); i <= end; i++) {
			for (int j = c - 20 < 0 ? 0 : c - 20, jEnd = (c + 20 >= H - 1 ? H - 1 : c + 20); j <= jEnd; j++) {
				dp[i][j] = param;
			}
		}
	}
	

	static void isOkInit(int r, int c, int team) {
		for (int i = r - 20 < 0 ? 0 : r - 20, end = (r + 20 >= H - 1 ? H - 1 : r + 20); i <= end; i++) {
			for (int j = c - 20 < 0 ? 0 : c - 20, jEnd = (c + 20 >= H - 1 ? H - 1 : c + 20); j <= jEnd; j++) {
				isOk[i][j] = true;
			}
		}
		
		int rr, cc;
		for (int i = r - 20 < 0 ? 0 : r - 20, end = (r + 20 >= H - 1 ? H - 1 : r + 20); i <= end; i++) {
			for (int j = c - 20 < 0 ? 0 : c - 20, jEnd = (c + 20 >= H - 1 ? H - 1 : c + 20); j <= jEnd; j++) {
				if(unitMap[i][j] == null) continue;
				if(unitMap[i][j].team != team) {
					for (int k = 0; k < 4; k++) {
						rr = i + dr[k];
						cc = j + dc[k];
						if(!isValid(rr, cc)) continue;
						isOk[i][j] = false;
					}
				}
			}
		}
	}
	
	
	static class Unit {
		int mobility, team, r, c;

		public Unit(int mobility, int team, int r, int c) {
			this.mobility = mobility;
			this.team = team;
			this.r = r;
			this.c = c;
		}
		
		public boolean move(int er, int ec) {
			// 이동 불가 지형, 목표 지점에 다른 유닛 있는 경우 제외
			if(map[er][ec] > this.mobility || unitMap[er][ec] != null) return false;
			
			// 목표지점 도착
			if(this.r == er && this.c == ec) return true;
			
			// dijkstra
			if(!this.move(this.r, this.c, er, ec)) return false;
			unitMap[this.r][this.c] = null;
			this.r = er;
			this.c = ec;
			unitMap[this.r][this.c] = this;
			return true;
		}

		public boolean move(int r, int c, int er, int ec) {
			dpInit(r, c, this.mobility + 1);
			isOkInit(r, c, this.team);
			dp[r][c] = 0;
			pq.clear();
			pq.offer(new Edge(r, c, dp[r][c]));

			int rr, cc;
			while(!pq.isEmpty()) {
				Edge edge = pq.poll();
				r = edge.r;
				c = edge.c;
				
				// 유효한 값이 아니면 다시 뽑기
				if(edge.mobility > dp[r][c]) continue;
				
				for (int i = 0; i < 4; i++) {
					rr = r + dr[i];
					cc = c + dc[i];
					
					// 경계너머, 이동 불가 지역 제외
					if(!isValid(rr, cc) || map[rr][cc] == INF) continue;
					
					// 다른팀 통과 제외
					if(unitMap[rr][cc] != null && unitMap[rr][cc].team != this.team) continue;
					
					// dp값에 변화가 없으면 제외
					if(dp[rr][cc] <= dp[r][c] + map[rr][cc]) continue;
					
					// 목표지점 도착
					if(rr == er && cc == ec) return true;
					
					// 교전 지역 이동 불가
					if(!isOk[rr][cc]) continue;
					
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
}
