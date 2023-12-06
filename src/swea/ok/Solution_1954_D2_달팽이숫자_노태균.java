package swea.ok;

import java.util.Scanner;

public class Solution_1954_D2_달팽이숫자_노태균 {
	static int[][] snailArr;
	static int num;
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		for(int i = 1; i <= T; i++) {
			N = sc.nextInt();
			num = 1;
			snailArr = new int[N][N];
			
			sb.append("#").append(i).append("\n");
			for(int[] arr : snail(0, N)) {
				for(int a : arr) {
					sb.append(a).append(" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	private static int[][] snail(int start, int n) {
		if(n < 1) {
			return snailArr;
		}
		
		int x = start;
		int y = start;
		
		snailArr[x][y] = num++;
		for(int i = 0; i < n - 1; i++) {
			snailArr[x][++y] = num++;
		}
		for(int i = 0; i < n - 1; i++) {
			snailArr[++x][y] = num++;
		}
		for(int i = 0; i < n - 1; i++) {
			snailArr[x][--y] = num++;
		}
		for(int i = 0; i < n - 2; i++) {
			snailArr[--x][y] = num++;
		}
		
		return snail(start + 1, n - 2);
	}

}
