package programmers.lv3.ok.등굣길;


public class Solution1 {
	public static void main(String[] args) {
		int m = 4;
		int n = 3;
		int[][] puddles = {{2,2}};
		System.out.println(Solution(m, n, puddles));
	}

	private static int Solution(int m, int n, int[][] puddles) {
		int min = Math.min(m, n);
		long[][] cnt = new long[n + 2][m + 2];
		cnt[1][1] = 1;
		for(int[] arr : puddles) {
			cnt[arr[1]][arr[0]] = -1;
		}
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j < Math.min(i, min); j++) {
				if(cnt[i - j][1 + j] != -1) {
					cnt[i - j][1 + j] = cnt[i - j - 1][1 + j] + cnt[i - j][j];
				} else {
					cnt[i - j][1 + j] = 0;
				}
			}
		}
		
		for(int i = 2; i <= m; i++) {
			for(int j = 0; j < Math.min(m - i + 1, min); j++) {
				if(cnt[n - j][i + j] != -1) {
					cnt[n - j][i + j] = cnt[n - j - 1][i + j] + cnt[n - j][i + j - 1];
				} else {
					cnt[n - j][i + j] = 0;
				}
			}			
		}
		
		return (int) cnt[n][m] % 1000000007;
	}
}
