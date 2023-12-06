package programmers.lv2.ok.삼각달팽이;

import java.util.Arrays;

public class Solution3 {
	public static void main(String[] args) {
		int n = 4;
		System.out.println(Arrays.toString(Solution(n)));
	}
	
	static int number;
	static int[][] snailArr;
	private static int[] Solution(int n) {
		snailArr = new int[n][];
		for(int i = 0; i < n; i++) {
			snailArr[i] = new int[i + 1];
		}
		
		int[] result = new int[(n + 1) * n / 2];
		int idx = 0;
		for(int[] arr : snail(n, 0, 0, 0)) {
			for(int num : arr) {
				result[idx++] = num;
			}
		}
		return result;
	}
	
	private static int[][] snail(int n, int cnt, int sr, int sc) {
		if(n <= 0) return snailArr;
		int r = sr - 1, c = sc;
		for(int i = 0; i < n; i++) snailArr[++r][c] = ++number;
		for(int i = 0; i < n - 1; i++) snailArr[r][++c] = ++number;
		for(int i = 0; i < n - 2; i++) snailArr[--r][--c] = ++number;
		return snail(n - 3, cnt + 1, sr + 2, sc + 1);
	}
}
