package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17471_G4_게리맨더링_노태균 {
	static int[] set, population;
	static boolean[] numbers;
	static boolean[][] map;
	static int N, min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		N = Integer.parseInt(br.readLine());
		population = new int[N + 1];
		numbers = new boolean[N + 1];
		map = new boolean[N + 1][N + 1];
		int i = 1;
		for(String s : br.readLine().split(" ")) {
			population[i++] = Integer.parseInt(s);
		}
		
		for(i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for(int j = 1, len = Integer.parseInt(input[0]); j <= len; j++) {
				int k = Integer.parseInt(input[j]);
				map[i][k] = map[k][i] = true;
			}
		}

		br.close();
		set = new int[N + 1];
		subSet(1);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	private static void subSet(int idx) {
		if(idx == N + 1) {
			makeSet(N);
			for(int i = 1; i < N; i++) {
				for(int j = i + 1; j <= N; j++) {
					if(!map[i][j]) continue;
					if(numbers[i]) {
						if(numbers[j]) union(i, j); 
					} else 
						if(!numbers[j]) union(i, j);
				}
			}
			
			if(isTwoUnion()) {
				int diff = 0;
				for(int i = 1; i <= N; i++) {
					if(numbers[i]) diff += population[i];
					else diff -= population[i];
				}
				min = Math.min(Math.abs(diff), min);
			}
			return;
		}
		
		numbers[idx] = true;
		subSet(idx + 1);
		numbers[idx] = false;
		subSet(idx + 1);
	}

	static void makeSet(int n) {
		for(int i = 1; i <= n; i++) {
			set[i] = i;
		}
	}
	
	static int findSet(int v) {
		if(v == set[v]) return v;
		return set[v] = findSet(set[v]);
	}
	
	static boolean union(int a, int b) {
		int r1 = findSet(a);
		int r2 = findSet(b);
		if(r1 == r2) return false;
		set[r1] = r2;
		return true;
	}
	
	static boolean isTwoUnion() {
		int cnt = 0;
		for(int i = 1; i <= N; i++) {
			if(i == set[i]) cnt++;
		}
		return cnt == 2;
	}
}
