package programmers.lv2.ok.전력망을_둘로_나누기;

import java.util.Arrays;

public class Solution1 {
	public static void main(String[] args) {
		int n = 9;
		int[][] wires = {
				{1,3},
				{2,3},
				{3,4},
				{4,5},
				{4,6},
				{4,7},
				{7,8},
				{7,9}
		};
		System.out.println(Solution(n, wires));
	}
	
	private static int Solution(int n, int[][] wires) {
		connect = new boolean[n + 1][n + 1];
		isSelected = new boolean[n + 1];
		N = n;
		int a, b;
		for(int[] arr : wires) {
			a = arr[0];
			b = arr[1];
			connect[a][b] = connect[b][a] = true;
		}
		
		int i = 0;
		int[] diff = new int[n - 1];
		for(int[] arr : wires) {
			a = arr[0];
			b = arr[1];
			connect[a][b] = connect[b][a] = false;
			diff[i++] = Math.abs((2 * getConnectCnt(a)) - n);
			connect[a][b] = connect[b][a] = true;
			Arrays.fill(isSelected, false);
		}
		
		int min = Integer.MAX_VALUE;
		for(int num : diff) {
			min = Math.min(num, min);
		}
		return min;
	}
	
	static boolean[][] connect;
	static boolean[] isSelected;
	static int N;
	private static int getConnectCnt(int cur) {
		isSelected[cur] = true;
		int cnt = 1;
		for(int i = 1; i <= N; i++) {
			if(!connect[cur][i] || isSelected[i]) continue;
			cnt += getConnectCnt(i);
		}
		return cnt;
	}
}
