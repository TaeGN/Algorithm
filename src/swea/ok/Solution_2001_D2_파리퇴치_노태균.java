package swea.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_2001_D2_파리퇴치_노태균 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 변수 설정
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int N, M, num, max, lMin, mMin;
		String[] sArr;
		int[][] flies;
		
		// 테스트 케이스 T번 반복
		for(int i = 1; i <= T; i++) {
			sArr = br.readLine().split(" ");
			N = Integer.parseInt(sArr[0]);
			M = Integer.parseInt(sArr[1]);
			flies = new int[N - M + 1][N - M + 1];
			
			// N x N영역을 M x M씩 묶어서 (N - M + 1) x (N - M + 1)영역으로 바꿈
			for(int j = 0; j < N; j++) {
				sArr = br.readLine().split(" ");
				for(int k = 0; k < N; k++) {
					num = Integer.parseInt(sArr[k]);
					lMin = (j - M + 1) >= 0 ? j - M + 1 : 0;
					mMin = (k - M + 1) >= 0 ? k - M + 1 : 0;
					for(int l = (j >= N - M ? N - M : j); l >= lMin ; l--) {
						for(int m = (k >= N - M ? N - M : k); m >= mMin ; m--) {
							flies[l][m] += num;
						}
					}
				}
			}
			
			max = Integer.MIN_VALUE;
			for(int[] flyArr : flies) {
				for(int fly : flyArr) {
					max = Math.max(max, fly);
				}
			}
			sb.append("#").append(i).append(" ").append(max).append("\n");
		}
		
		System.out.println(sb);
	}
}
