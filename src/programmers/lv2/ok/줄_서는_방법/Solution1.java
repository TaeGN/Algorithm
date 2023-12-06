package programmers.lv2.ok.줄_서는_방법;

import java.util.Arrays;

public class Solution1 {
	public static void main(String[] args) {
		int n = 3;
		int k = 5;
		System.out.println(Arrays.toString(Solution(n, k)));
	}

	private static int[] Solution(int n, int k) {
		numbers = new int[n];
		isSelected = new boolean[n + 1];
		N = n; K = k;
		line(0);
		return numbers;
	}
	
	static int N, K;
	static int[] numbers;
	static boolean[] isSelected;
	static boolean tf;
	private static void line(int n) {
		if(n == N) {
			if(--K == 0) {
				tf = true;
			} 
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(isSelected[i]) continue;
			
			numbers[n] = i;
			isSelected[i] = true;
			line(n + 1);
			if(tf) return;
			isSelected[i] = false;
		}
	}
}
