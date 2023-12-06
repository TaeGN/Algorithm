package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17406_G4_배열돌리기4_노태균2 {
	static int N, M, K;
	static boolean[] isSelected;
	static int[] numbers;
	static int[][] arr, rotateArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(input[j]);
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
		numbers = new int[K];
		permutation(0);
		getValue(arr);

	}

	// nPn
	private static void permutation(int cnt) {
		if (cnt == 6) {
			rotate(numbers);
			return;
		}

		for (int i = 0; i < K; i++) {
			if (isSelected[i])
				continue;
			numbers[cnt] = i;
			isSelected[i] = true;
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}

	private static void rotate(int[] iArr) {
		int r, c, s;
		for (int i : iArr) {
			r = rotateArr[i][0];
			c = rotateArr[i][1];
			s = rotateArr[i][2];
		}
	}

	// 현재 배열의 값 구하기
	private static int getValue(int[][] arr) {
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				sum += arr[i][j];
			}
			min = Math.min(min, sum);
		}
		return min;
	}

}
