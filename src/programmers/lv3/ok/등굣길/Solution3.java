package programmers.lv3.ok.등굣길;


public class Solution3 {
	public static void main(String[] args) {
		int m = 4;
		int n = 3;
		int[][] puddles = {{2,2}};
		System.out.println(Solution(m, n, puddles));
	}

	private static int Solution(int m, int n, int[][] puddles) {
		int min = Math.min(m, n);
		long[][] cnt = new long[n + 2][m + 2];
		for(int i = 1; i <= n; i++) {
			cnt[i][1] = 1;
		}
		for(int i = 1; i <= m; i++) {
			cnt[1][i] = 1;
		}
		for(int[] arr : puddles) {
			cnt[arr[1]][arr[0]] = -1;
		}
		long prev;
		long current;
		for(int i = 2; i <= n; i++) {
			prev = cnt[i][1];
			for(int j = 1; j < Math.min(i, min); j++) {
				current = cnt[i - j][1 + j];
				if(cnt[i - j + 1][1 + j] != -1) {
					cnt[i - j + 1][1 + j] = prev + current;
				} else {
					cnt[i - j + 1][1 + j] = 0;
				}
				prev = current;
			}
		}
		
		for(int i = 2; i < m; i++) {
			prev = cnt[n][i];
			for(int j = 1; j < Math.min(m - i + 1, min); j++) {
				current = cnt[n - j][i + j];
				if(cnt[n - j + 1][i + j] != -1) {
					cnt[n - j + 1][i + j] = prev + current;
				} else {
					cnt[n - j + 1][i + j] = 0;
				}
				prev = current;
			}			
		}
		
		return (int) cnt[n][m] % 1000000007;
	}
}
