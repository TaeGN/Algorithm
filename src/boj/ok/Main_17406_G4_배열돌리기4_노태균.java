package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17406_G4_배열돌리기4_노태균 {
	static int N, M, K, min = Integer.MAX_VALUE;
	static boolean[] isSelected;
	static int[][] arr, rotateArr;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		arr = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(input[j - 1]);
			}
		}

		rotateArr = new int[K][3];
		for (int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < 3; j++) {
				rotateArr[i][j] = Integer.parseInt(input[j]);
			}
		}
		isSelected = new boolean[K];
		permutation(0, arr);
		System.out.println(min);
	}

	// nPn
	private static void permutation(int cnt, int[][] curArr) {
		if (cnt == K) {
			min = Math.min(min, getValue(curArr));
			return;
		}

		for (int i = 0; i < K; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			permutation(cnt + 1, rotate(i, deepCopy(curArr)));
			isSelected[i] = false;
		}
	}
	
	// 2차원 배열의 deep copy
	private static int[][] deepCopy(int[][] curArr) {
		int[][] newArr = new int[N + 1][];
		for(int i = 1; i <= N; i++) {
			newArr[i] = curArr[i].clone();
		}
		return newArr;
	}

	// 들어온 인덱스에 해당하는 회전 수행
	private static int[][] rotate(int i, int[][] curArr) {
		int r, c, s;
		r = rotateArr[i][0];
		c = rotateArr[i][1];
		s = rotateArr[i][2];
		
		int temp;
		for(int j = 1; j <= s; j++) {
			int a = r - j;
			int b = c - j;
			temp = curArr[a][b];
			for(int k = 0; k < 4; k++) {
				for(int l = 0; l < 2 * j; l++) {
					curArr[a][b] = curArr[(a += dr[k])][(b += dc[k])];
				}
			}
			curArr[a][b + 1] = temp;
		}
		return curArr;
	}

	// 현재 배열의 값 구하기
	private static int getValue(int[][] curArr) {
		int minValue = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= M; j++) {
				sum += curArr[i][j];
			}
			minValue = Math.min(minValue, sum);
		}
		return minValue;
	}

}
