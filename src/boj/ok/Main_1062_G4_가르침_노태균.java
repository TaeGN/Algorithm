package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main_1062_G4_가르침_노태균 {
	static int N, K, M, max;
	static Character[] allWords;
	static boolean[][] words;
	static int[] numbers;
	static Set<Character> all = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]) - 5;
		words = new boolean[N][26];
		for(int i = 0; i < N; i++) {
			for(char c : br.readLine().toCharArray()) {
				words[i][c - 'a'] = true;
				all.add(c);
			}
		}
		all.removeAll(Arrays.asList('a', 'n', 't', 'i', 'c'));
		allWords = all.toArray(new Character[all.size()]);
		M = allWords.length;
		if(K < all.size()) {
			K = all.size() - K;
			numbers = new int[K];
			combi(0, 0);
		} else {
			max = N;
		}
		System.out.println(max);
	}
	
	private static void combi(int cnt, int idx) {
		if(cnt == K) {
			int n = N;
			a:for(int i = 0; i < N; i++) {
				for(int j = 0; j < K; j++) {
					if(words[i][allWords[numbers[j]] - 'a']) {
						n--;
						continue a;
					}
				}
			}
			max = Math.max(max, n);
			return;
		}
		
		if(idx == M) return;
		numbers[cnt] = idx;
		combi(cnt + 1, idx + 1);
		combi(cnt, idx + 1);
	}
}
