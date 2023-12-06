package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main_2239_G4_스도쿠_노태균3 {
	static int N;
	static int[][] map;
	static Sudoku sudoku = new Sudoku();
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		map = new int[9][9];
		int num;
		for(int i = 0; i < 9; i++) {
			int j = 0;
			for(char c : br.readLine().toCharArray()) {
				num = c - '0';
				if(num != 0) sudoku.add(i, j, num);
				else N++;
				map[i][j++] = num;
			}
		}
		br.close();
		dfs(0, 0, 0);
		for(int[] a : map) {
			for(int b : a) {
				sb.append(b);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static boolean dfs(int i, int j, int cnt) {
		if(cnt == N) return true;
		
		int r = i - 1, c = j - 1;
		a:while(++r < 9) {
			while(++c < 9) {
				if(map[r][c] == 0) break a;
			}
			c = -1;
		}
		if(r == 9) return false;
		
		for(int n = 1; n <= 9; n++) {
			if(sudoku.add(r, c, n)) {
				map[r][c] = n;
				if(dfs(r, c, cnt + 1)) return true; 
				sudoku.remove(r, c, n);
			}
		}
		
		map[r][c] = 0;
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
				this.r[i] = new HashSet<>(9);
				this.c[i] = new HashSet<>(9);
				this.b[i] = new HashSet<>(9);
			}
		}
		
		public boolean add(int i, int j, int num) {
			int k = (i / 3) * 3 + (j / 3);
			if(this.r[i].contains(num) 
					|| this.c[j].contains(num) 
					|| this.b[k].contains(num)) return false;
			this.r[i].add(num);
			this.c[j].add(num);
			this.b[k].add(num);
			return true;
		}
		
		public void remove(int i, int j, int num) {
			int k = (i / 3) * 3 + (j / 3);
			this.r[i].remove(num);
			this.c[j].remove(num);
			this.b[k].remove(num);
		}
	}
}
