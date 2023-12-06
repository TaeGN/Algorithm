package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_3109_G2_빵집_노태균 {
	static int R, C;
	static boolean[][] visited;
	static boolean tf;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[];
		input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		visited = new boolean[R + 2][C];
		Arrays.fill(visited[0], true);
		Arrays.fill(visited[R + 1], true);
		for(int i = 1; i <= R; i++) {
			int j = 0;
			for(char c : br.readLine().toCharArray()) {
				if(c == 'x') visited[i][j] = true;
				j++;
			}
		}
		br.close();
		int cnt = 0; 
		for(int i = 1; i <= R; i++) {
			if(!visited[i][1] && connect(i,1)) cnt++;
		}
		
		System.out.println(cnt);
	}

	private static boolean connect(int r, int c) {
		if(c == C - 2) return true;
		
		for(int i = -1; i <= 1; i++) {
			if(visited[r + i][c + 1]) continue;
			visited[r + i][c + 1] = true;
			if(connect(r + i, c + 1)) return true;
		}
		
		return false;
	}
}
