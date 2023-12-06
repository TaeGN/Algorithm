package swea.b형특강.lecture2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution_프로세서연결하기 {
	
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static int N, coreSize, maxCoreCnt, minLen;
	static List<Point> coreList;
	static boolean[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		coreList = new ArrayList<>();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// init
			maxCoreCnt = 0;
			minLen = Integer.MAX_VALUE;
			coreList.clear();
			
			N = Integer.parseInt(br.readLine());
			map = new boolean[N + 2][N + 2];
			
			// map : 0 -> true, 1 or 가장자리 -> false
			for (int i = 1; i <= N; i++) {
				String[] input = br.readLine().split(" ");
				for (int j = 1; j <= N; j++) {
					if(input[j - 1].equals("0")) {
						map[i][j] = true;
					} else {
						// 가장자리 제외
						if(isBoundary(i, j)) continue;
						coreList.add(new Point(i, j));
					}
				}
			}
			
			coreSize = coreList.size();
			process(0, 0, 0);
			sb.append(minLen).append("\n");
		}
		br.close();
		System.out.println(sb);
	}
	
	public static void process(int coreIdx, int coreCnt, int len) {
		// 현 시점에서 계속 진행해도 maxCoreCnt에 도달하지 못할 경우 배제
		if(coreCnt + (coreSize - coreIdx) < maxCoreCnt) return;
		
		// 탈출조건
		if(coreIdx == coreSize) {
			if(coreCnt > maxCoreCnt) {
				maxCoreCnt = coreCnt;
				minLen = len;
			} else {
				minLen = Math.min(minLen, len);
			}
			return;
		}
		
		Point core = coreList.get(coreIdx);
		int r = core.r;
		int c = core.c;
		for (int d = 0; d < 4; d++) {
			// 연결 가능할 경우
			if(connectCheck(r, c, d)) {
				process(coreIdx + 1, coreCnt + 1, len + connect(r, c, d));
				disconnect(r, c, d);
			} 
		}
		// 연결 안하는 경우
		process(coreIdx + 1, coreCnt, len);
	}
	
	public static boolean connectCheck(int r, int c, int d) {
		while(!isBoundary(r, c)) {
			if(!map[r += dr[d]][c += dc[d]]) return false;
		}
		return true;
	}
	
	public static int connect(int r, int c, int d) {
		int len = 0;
		while(!isBoundary(r, c)) {
			map[r += dr[d]][c += dc[d]] = false;
			len++;
		}
		return len;
	}
	
	public static void disconnect(int r, int c, int d) {
		while(!isBoundary(r, c)) map[r += dr[d]][c += dc[d]] = true;
	}
	
	public static boolean isBoundary(int r, int c) {
		return r == 1 || c == 1 || r == N || c == N;
	}
	
	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
}
