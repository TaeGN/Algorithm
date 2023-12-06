package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main_2239_G4_스도쿠_노태균 {
	static int N;
	static int[][] map;
	static Sudoku sudoku = new Sudoku();
	static List<Point> pointList = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		map = new int[9][9];
		int num;
		Point point;
		for(int i = 0; i < 9; i++) {
			int j = 0;
			for(char c : br.readLine().toCharArray()) {
				num = c - '0';
				point = new Point(i, j);
				if(num == 0) pointList.add(point);
				else sudoku.add(point, num);
				map[i][j++] = num;
			}
		}
		br.close();
		N = pointList.size();
		dfs(0);
		for(int[] a : map) {
			for(int b : a) {
				sb.append(b);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static boolean dfs(int idx) {
		if(idx == N) return true;
		
		Point cur = pointList.get(idx);
		for(int i = 1; i <= 9; i++) {
			if(sudoku.add(cur, i)) {
				map[cur.r][cur.c] = i;
				if(dfs(idx + 1)) return true; 
				sudoku.remove(cur, i);
			}
		}
		
		return false;
	}

	static class Sudoku {
		Set<Integer>[] r;
		Set<Integer>[] c;
		Set<Integer>[] b;
		
		public Sudoku() {
			this.r = new HashSet[9];
			this.c = new HashSet[9];
			this.b = new HashSet[9];
			for(int i = 0; i < 9; i++) {
				this.r[i] = new HashSet<>();
				this.c[i] = new HashSet<>();
				this.b[i] = new HashSet<>();
			}
		}
		
		public boolean add(Point point, int num) {
			if(this.r[point.r].contains(num) 
					|| this.c[point.c].contains(num) 
					|| this.b[(point.r / 3) * 3 + (point.c / 3)].contains(num)) return false;
			this.r[point.r].add(num);
			this.c[point.c].add(num);
			this.b[(point.r / 3) * 3 + (point.c / 3)].add(num);
			return true;
		}
		
		public void remove(Point point, int num) {
			this.r[point.r].remove(num);
			this.c[point.c].remove(num);
			this.b[(point.r / 3) * 3 + (point.c / 3)].remove(num);
		}
	}
	
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
