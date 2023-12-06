package programmers.lv1.ok.과일_장수;

import java.util.Arrays;

public class Solution1 {
	public static void main(String[] args) {
		int k = 3; 
		int m = 4;
		int[] score = {1, 2, 3, 1, 2, 3, 1};
		int answer = Solution(k, m, score);
		System.out.println(answer);
	}

	private static int Solution(int k, int m, int[] score) {
		int result = 0;
		Arrays.sort(score);
		for(int i = score.length - m; i >= 0; i -= m) {
			result += score[i] * m;
		}
		
		return result;
	}
}
