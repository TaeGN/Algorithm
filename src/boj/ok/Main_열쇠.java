package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Main_열쇠 {
	
	static int N, M, documentCnt;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static final char WALL = '*';
	static final char GROUND = '.';
	static final char DOCUMENT = '$';
	static char[][] map;
	static boolean[][] visited;
	static boolean repeat;
	static List<Integer> entrance;
	static ArrayList<Integer>[] doorList;
	static Queue<Integer> queue;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		final int MAX_N = 100;
		final int MAX_M = 100;
		final int MAX_KEY_CNT = 26;
		map = new char[MAX_N][MAX_M];
		visited = new boolean[MAX_N][MAX_M];
		entrance = new ArrayList<>();
		queue = new ArrayDeque<>();
		doorList = new ArrayList[MAX_KEY_CNT];
		for (int i = 0; i < MAX_KEY_CNT; i++) {
			doorList[i] = new ArrayList<Integer>();
		}
		
		for (int tc = 0; tc < T; tc++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			
			// init
			entrance.clear();
			documentCnt = 0;
			
			// door위치 리스트 초기화
			for (int i = 0; i < MAX_KEY_CNT; i++) {
				doorList[i].clear();
			}
			
			// 입력 받기
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					char c = str.charAt(j);
					map[i][j] = c;
					if(c == WALL) continue;
					
					// 문
					if(Character.isUpperCase(c)) {
						doorList[c - 'A'].add(i * M + j);
					} 
					
					// 가장자리 저장
					if(i == 0 || j == 0 || i == N - 1 || j == M - 1) {
						entrance.add(i * M + j);
					}
				}
			}
			
			// 가장자리 열쇠, 문서 처리
			int r, c;
			for (int rc : entrance) {
				r = rc / M;
				c = rc % M;
				if(Character.isLowerCase(map[r][c])) {
					open(map[r][c]);
					map[r][c] = GROUND;
				} else if(map[r][c] == '$') {
					documentCnt++;
					map[r][c] = GROUND;
				}
			}
			
			// 이미 갖고 있는 열쇠
			String str = br.readLine();
			if(!str.equals("0")) {
				for (int i = 0; i < str.length(); i++) {
					open(str.charAt(i));
				}
			}
			
			repeat = true;
			while(repeat) {
				repeat = false;
				
				for (int rc : entrance) {
					r = rc / M;
					c = rc % M;
					if(map[r][c] != GROUND) continue;
					visitedInit();
					bfs(r, c);
//					print();
				}
			}
			sb.append(documentCnt).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static void print() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (int i = 0; i < N; i++) {
			sb.append(String.valueOf(map[i])).append("\n");
		}
		System.out.println(sb);
	}
	
	static void visitedInit() {
		// visited 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], 0, M, false);
		}
	}

	static void open(char c) {
		for (int rc : doorList[c - 'a']) {
			map[rc / M][rc % M] = GROUND;
		}
		doorList[c - 'a'].clear();
		repeat = true;
	}

	static void bfs(int r, int c) {
		queue.offer(r * M + c);
		visited[r][c] = true;
		int rr, cc, rc;
		char val;
		
		while(!queue.isEmpty()) {
			rc = queue.poll();
			r = rc / M;
			c = rc % M;
			
			for (int i = 0; i < 4; i++) {
				rr = r + dr[i];
				cc = c + dc[i];
				
				// 경계선 바깥 제외
				if(!isValid(rr, cc) || visited[rr][cc]) continue;
				
				val = map[rr][cc];
				// 벽 & 열 수 없는 문 제외
				if(val == WALL || Character.isUpperCase(val)) continue;
				
				// 열쇠 획득
				if(Character.isLowerCase(val)) {
					map[rr][cc] = GROUND;
					open(val);
				}
				
				// 문서 획득
				else if(val == DOCUMENT) {
					map[rr][cc] = GROUND;
					documentCnt++;
				}
				
				queue.offer(rr * M + cc);
				visited[rr][cc] = true;
			}
		}
		
	}
	
	static boolean isValid(int r, int c) {
		return r < N && c < M && r >= 0 && c >= 0;
	}
}
	
