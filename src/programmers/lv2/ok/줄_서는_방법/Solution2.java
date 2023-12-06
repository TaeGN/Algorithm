package programmers.lv2.ok.줄_서는_방법;

import java.util.Arrays;

public class Solution2 {
	public static void main(String[] args) {
		int n = 3;
		long k = 6;
		System.out.println(Arrays.toString(Solution(n, k)));
	}

	private static int[] Solution(int n, long k) {
		long cnt = 1;
		long[] totalCnt = new long[n - 1];
		int[] numbers = new int[n];
		boolean[] isSelected = new boolean[n + 1];
		
		// 순열의 총 경우의 수 배열
		for(int i = 1; i < n; i++) {
			cnt *= i;
			totalCnt[n - i - 1] = cnt;
		}
		
		int a = 0;
		int b = 0;
		for(int i = 0; i < n - 1; i++) {
			if(k == 0) {
				a = n + 1;
				for(int j = i; j < n; j++) {
					while(isSelected[--a]) {} // false인것 찾기
					numbers[j] = a;
					isSelected[a] = true;
				}
				return numbers;
			}
			
			if(k >= totalCnt[i]) {
				a = 0;
				b = (int) ((k - 1) / totalCnt[i]) + 1;
				while(++a < n) {
					if(!isSelected[a]) b--;
					if(b == 0) break;
				}
				
				k %= totalCnt[i];
			} else {
				a = 0;
				while(isSelected[++a]) {}
			}
			
			numbers[i] = a;
			isSelected[a] = true;
		}
		a = 0;
		while(isSelected[++a]) {}
		numbers[n - 1] = a;
		return numbers;
	}

}
