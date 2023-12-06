package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2563_S5_색종이_노태균2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		boolean[][] paper = new boolean[101][101];	// true : black
		int N = Integer.parseInt(br.readLine());
		int x, y, area = 0;
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			x = Integer.parseInt(input[0]);
			y = Integer.parseInt(input[1]);
			for(int j = 0; j < 10; j++) {
				for(int k = 0; k < 10; k++) {
					paper[y + j][x + k] = true;
				}
			}
		}
		for(int j = 1; j <= 100; j++) {
			for(int k = 1; k <= 100; k++) {
				if(paper[j][k]) area++;
			}
		}
		System.out.println(area);
		br.close();
	}
}
