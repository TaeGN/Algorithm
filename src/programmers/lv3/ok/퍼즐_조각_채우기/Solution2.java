package programmers.lv3.ok.퍼즐_조각_채우기;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution2 {
	public static void main(String[] args) {
		int[][] game_board = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
		int[][] table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};
		System.out.println(Solution(game_board, table));
	}
	
	static int cnt, puzzleNum = 1, boardR, boardC;
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};
    static Point[] piece = new Point[6];
    static Map<Puzzle, Integer> board = new HashMap<>();
    public static int Solution(int[][] game_board, int[][] table) {
    	boardR = game_board.length;
    	boardC = game_board[0].length;
    	Puzzle curPuzzle;
    	// board에 퍼즐 모양과 개수 채우기
    	int i = 0, j;
    	for(int[] arr : game_board) {
    		j = 0;
    		for(int a : arr) {
    			table[i][j] = table[i][j] == 1 ? 0 : 1;
    			if(a == 0) {
    				cnt = 0; puzzleNum++;
    				getPuzzleShape(i, j, game_board);
    				curPuzzle = new Puzzle(cnt, getShape(game_board));
    				if(board.containsKey(curPuzzle)) board.replace(curPuzzle, board.get(curPuzzle) + 1);
    				else board.put(curPuzzle, 1);
    			}
    			j++;
    		}
    		i++;
    	}
    	int answer = 0;
    	i = 0;
    	for(int[] arr : table) {
    		j = 0;
    		for(int a : arr) {
    			if(a == 0) {
    				cnt = 0; puzzleNum++;
    				getPuzzleShape(i, j, table);
    				curPuzzle = new Puzzle(cnt, getShape(table));
    				if(board.containsKey(curPuzzle)) {
    					answer++;
    					int num = board.get(curPuzzle);
    					if(num == 1) board.remove(curPuzzle);
    					else board.replace(curPuzzle, num - 1);
    				}
    			}
    			j++;
    		}
    		i++;
    	}
    	
    	System.out.println(board);
    	
        return answer;
    }
    
    // 보드의 빈 부분들 탐색
    private static void getPuzzleShape(int r, int c, int[][] board) {
    	board[r][c] = puzzleNum;
    	piece[cnt++] = new Point(r, c);
    	for(int i = 0; i < 4; i++) {
    		int rr = r + dr[i];
    		int cc = c + dc[i];
    		if(rr < 0 || cc < 0 || rr >= boardR || cc >= boardC || board[rr][cc] != 0) continue;
    		getPuzzleShape(rr, cc, board);
    	}
    }
    
    private static int[][] getShape(int[][] game_board) {
    	Arrays.sort(piece, 0, cnt);
    	int minC = Integer.MAX_VALUE, maxC = Integer.MIN_VALUE;
    	int minR = piece[0].r, maxR = piece[cnt - 1].r;
    	for(int i = 0; i < cnt; i++) {
    		minC = Math.min(minC, piece[i].c);
    		maxC = Math.max(maxC, piece[i].c);
    	}
    	int rLen = maxR - minR + 1;
    	int cLen = maxC - minC + 1;
    	int[][] shape = new int[rLen][cLen];
    	for(int i = 0; i < rLen; i++) {
    		shape[i] = Arrays.copyOfRange(game_board[minR + i], minC, maxC + 1);
    	}
//    	for(int i = 0; i < cnt; i++) {
//    		shape[piece[0].r - minR][piece[0].c - minC] = puzzleNum;
//    	}
    	return shape;
    }
    
    static class Point implements Comparable<Point>{
    	int r;
    	int c;
    	
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Point o) {
			if(this.r != o.r) return this.r - o.r;
			else return this.c - o.c;
		}
    }
    
	static class Puzzle implements Comparable<Puzzle> {
    	int puzzleSize;	// 퍼즐의 크기
    	int space;		// 퍼즐이 차지하는 공간 (2 x 3 = 6)	
    	Set<boolean[][]> shape;
    	

		public Puzzle(int size, int[][] shape) {
			super();
			this.puzzleSize = size;
			this.space = shape.length * shape[0].length;
			this.shape = new HashSet<>();
			this.setting(shape);
		}
		
		private void setting(int[][] shape) {
			boolean[][] shape2 = new boolean[shape.length][shape[0].length];
			int i = 0, j = 0;
			for(int[] arr : shape) {
				j = 0;
				for(int a : arr) {
					if(a != 1) shape2[i][j] = true;
					j++;
				}
				i++;
			}
			boolean[][] newShape = rotate90(shape2);
			this.shape.add(shape2);
			this.shape.add(rotate180(shape2));
			this.shape.add(newShape);
			this.shape.add(rotate180(newShape));
			return;
		}
		
 
		private boolean[][] rotate180(boolean[][] shape) {
			int rLen = shape.length;
			int cLen = shape[0].length;
			boolean[][] newShape = new boolean[rLen][cLen];
			for(int r = 0; r < rLen; r++) {
				for(int c = 0; c < cLen; c++) {
					newShape[rLen - 1 - r][cLen - 1 - c] = shape[r][c];
				}
			}
			return newShape;
		}

		private boolean[][] rotate90(boolean[][] shape) {
			int rLen = shape.length;
			int cLen = shape[0].length;
			boolean[][] newShape = new boolean[cLen][rLen];
			for(int r = 0; r < rLen; r++) {
				for(int c = 0; c < cLen; c++) {
					newShape[c][rLen - 1 - r] = shape[r][c];
				}
			}
			return newShape;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + puzzleSize;
			result = prime * result + ((shape == null) ? 0 : shape.hashCode());
			result = prime * result + space;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Puzzle other = (Puzzle) obj;
			if (puzzleSize != other.puzzleSize)
				return false;
			if (space != other.space)
				return false;
			if (shape == null) {
				if (other.shape != null)
					return false;
			} else if (!shape.equals(other.shape))
				return false;
			return true;
		}

		@Override
		public int compareTo(Puzzle o) {
			if(this.puzzleSize != o.puzzleSize) return this.puzzleSize - o.puzzleSize;
			else return this.space - o.space;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Puzzle [puzzleSize=").append(puzzleSize).append(", space=").append(space).append(", shape=").append("\n");
			for(boolean[][] a : shape) {
				for(boolean[] b : a) {
					for(boolean c : b) {
						if(c) builder.append(1).append(" ");
						else builder.append(0).append(" ");
					}
					builder.append("\n");
				}
			}
			builder.append("]");
			return builder.toString();
		}
		
		
    }
    
}
