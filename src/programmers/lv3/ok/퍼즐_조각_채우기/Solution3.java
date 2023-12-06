package programmers.lv3.ok.퍼즐_조각_채우기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution3 {
	public static void main(String[] args) {
		int[][] game_board = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
		int[][] table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};
		System.out.println(Solution(game_board, table));
	}
	
	static int rLen, cLen, cnt;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int[][] coordinates = new int[6][2];
	static List<String> puzzleList = new ArrayList<>();
	public static int Solution(int[][] game_board, int[][] table) {
		rLen = game_board.length;
		cLen = game_board[0].length;
		
		// puzzleList에 game_board의 퍼즐 모양 넣기
		for(int r = 0; r < rLen; r++) {
			for(int c = 0; c < cLen; c++) {
				table[r][c] = table[r][c] == 1 ? 0 : 1;	// table의 1 <=> 0의 값 바꾸기
				if(game_board[r][c] == 0) {
					cnt = 0;
					getCoodinates(r, c, game_board);
					puzzleList.add(getPuzzle());
				}
			}
		}
		
		// puzzleList에 있는 퍼즐 모양과 table의 퍼즐 모양이 같으면 삭제 후 퍼즐크기 더해주기 
		String puzzle; int result = 0;
		for(int r = 0; r < rLen; r++) {
			for(int c = 0; c < cLen; c++) {
				if(table[r][c] == 0) {
					cnt = 0;
					getCoodinates(r, c, table);
					puzzle = getPuzzle();
					for(int d = 0; d < 4; d++) {	// 퍼즐을 회전시켜가며 같은 모양인지 비교한다.
						if(puzzleList.remove(rotate(puzzle, d))) {
							result += puzzle.replace(" ", "").replace("0", "").length();
							break;
						}
					}
				}
			}
		}
		return result;
	}
	
	// puzzle을 회전해서 return
	private static String rotate(String puzzle, int d) {
		if(d == 0) return puzzle;
		
		String[] sArr = puzzle.split(" ");
		char[][] cArr = new char[sArr.length][]; 
		char[][] nArr = null;
		int i = 0;
		for(String s : puzzle.split(" ")) {
			cArr[i++] = s.toCharArray();
		}
		int rLen = sArr.length;
		int cLen = sArr[0].length();
		
		switch(d) {
		case 1:		// 90도 회전
			nArr = new char[cLen][rLen];
			for(int r = 0; r < rLen; r++) {
				for(int c = 0; c < cLen; c++) {
					nArr[c][rLen - r - 1] = cArr[r][c];
				}
			}
			break;
		case 2:		// 180도 회전 
			nArr = new char[rLen][cLen];
			for(int r = 0; r < rLen; r++) {
				for(int c = 0; c < cLen; c++) {
					nArr[rLen - r - 1][cLen - c - 1] = cArr[r][c];
				}
			}
			break;
		case 3:		// 270도 회전
			nArr = new char[cLen][rLen];
			for(int r = 0; r < rLen; r++) {
				for(int c = 0; c < cLen; c++) {
					nArr[cLen - c - 1][r] = cArr[r][c];
				}
			}
			break;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int r = 0; r < nArr.length; r++) {
			sb.append(String.valueOf(nArr[r])).append(" ");
		}
		return sb.toString();
	}

	// puzzle모양 String으로 저장
	private static String getPuzzle() {
		int minR, minC, maxR, maxC, r, c;
		minR = minC = Integer.MAX_VALUE;
		maxR = maxC = Integer.MIN_VALUE;
		for(int i = 0; i < cnt; i++) {
			r = coordinates[i][0];
			c = coordinates[i][1];
			minR = Math.min(minR, r);
			minC = Math.min(minC, c);
			maxR = Math.max(maxR, r);
			maxC = Math.max(maxC, c);
		}
		
		StringBuilder sb = new StringBuilder();
		char[][] cArr = new char[maxR - minR + 1][maxC - minC + 1];
		for(int i = 0; i <= maxR - minR; i++) {
			Arrays.fill(cArr[i], '0');
		}
		for(int i = 0; i < cnt; i++) {
			cArr[coordinates[i][0] - minR][coordinates[i][1] - minC] = '1';
		}
		for(int i = 0; i <= maxR - minR; i++) {
			sb.append(String.valueOf(cArr[i])).append(" ");
		}
		return sb.toString();
	}


	// 빈칸의 좌표들 구하기
	private static void getCoodinates(int r, int c, int[][] game_board) {
		game_board[r][c] = 1;	// 방문체크
		coordinates[cnt][0] = r;
		coordinates[cnt++][1] = c;
		
		for(int d = 0; d < 4; d++) {
			int rr = r + dr[d];
			int cc = c + dc[d];
			if(rr < 0 || cc < 0 || rr >= rLen || cc >= cLen || game_board[rr][cc] == 1) continue;
			getCoodinates(rr, cc, game_board);
		}
		
	}
}
