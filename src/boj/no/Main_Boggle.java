package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main_Boggle {
	
	static final long POWER = 26;
	static final long MOD = 10_0000_0007;
	static final int BOARD_SIZE = 4;
	static final Map<Integer, Integer> SCORE = new HashMap<>();
	static Set<Integer> wordSet, findSet;
	static boolean[][] visited;
	static Map<Integer, String> wordMap;
	static char[][] board = new char[BOARD_SIZE][];
	static int[] dr = {0,0,1,-1,1,1,-1,-1};
	static int[] dc = {1,-1,0,0,1,-1,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int w = Integer.parseInt(br.readLine());
		findSet = new HashSet<>();
		wordSet = new HashSet<>(w);
		wordMap = new HashMap<>(w);
		SCORE.put(1, 0);
		SCORE.put(2, 0);
		SCORE.put(3, 1);
		SCORE.put(4, 1);
		SCORE.put(5, 2);
		SCORE.put(6, 3);
		SCORE.put(7, 5);
		SCORE.put(8, 11);
		
		for (int i = 0; i < w; i++) {
			String s = br.readLine();
			long hash = 0;
			for (int j = 0, len = s.length(); j < len; j++) {
				char c = s.charAt(j);
				hash = (((hash * POWER) % MOD) + (c - 'A')) % MOD;
				if(j < len - 1) wordSet.add((int) hash);
			}
			wordMap.put((int) hash, s);
		}
		
		// 빈 칸
		br.readLine();
		
		int b = Integer.parseInt(br.readLine());
		for (int i = 0; i < b; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[j] = br.readLine().toCharArray();
			}
			
//			System.out.println(i + "번째 시작 : " + Arrays.deepToString(board));
			
			// 시작 지점 바꿔가면서 Boggle 실행
			for (int rc = 0; rc < BOARD_SIZE * BOARD_SIZE; rc++) {
//				System.out.println(rc + "번째 실행 ----");
				Boggle(rc, 1, 0, 0);
			}
			
			int maxScore = 0;
			int longWordLen = 0;
			String longWord = "";
			for (int hash : findSet) {
				String str = wordMap.get(hash);
				int len = str.length();
				if(longWordLen < len) {
					longWord = str;
					longWordLen = len;
				} else if(longWordLen == len) {
					if(longWord.compareTo(str) > 0) {
						longWord = str;
					}
				}
				System.out.print(str + " ");
				maxScore += SCORE.get(len);
			}
			System.out.println();
			int wordCnt = findSet.size();
			findSet.clear();
			sb.append(maxScore).append(" ").append(longWord).append(" ").append(wordCnt).append("\n");
			
			// 빈 칸
			if(i < b - 1) br.readLine();
		}
		
		br.close();
		System.out.println(sb);
	}

	private static void Boggle(int rc, int len, long hash, int visited) {
		// 방문 체크
		visited |= (1 << rc);
		
		int r = rc / BOARD_SIZE;
		int c = rc % BOARD_SIZE;
		
		// hash 구하기
		hash = (((hash * POWER) % MOD) + (board[r][c] - 'A')) % MOD;
		
		// 단어를 찾으면 findSet에 저장 
		if(wordMap.containsKey((int)hash)) {
			findSet.add((int)hash);
		}
		
		// 최대 길이 8
		if(len == 8) return;
		
		// 가능한 단어가 아니면 return
		if(!wordSet.contains((int)hash)) return;
		
		// 사방탐색
		int rr, cc, rrcc;
		for (int d = 0; d < 8; d++) {
			rr = r + dr[d];
			cc = c + dc[d];
			
			// 경계선 바깥 배제
			if(!isValid(rr, cc)) continue;
			
			// 이미 방문한 곳 배제
			rrcc = rr * BOARD_SIZE + cc;
			if((visited & (1 << rrcc)) != 0) continue;
			
			Boggle(rrcc, len + 1, hash, visited);
		}
		
	}

	private static boolean isValid(int rr, int cc) {
		return rr < BOARD_SIZE && cc < BOARD_SIZE && rr >= 0 && cc >= 0;
	}
}
