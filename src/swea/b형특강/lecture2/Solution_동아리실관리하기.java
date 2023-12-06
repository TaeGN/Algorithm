package swea.b형특강.lecture2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_동아리실관리하기 {
	
	static final int N = 1 << 4;
	static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int[][] D = new int[2][N];
		int[] temp = null;
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			String input = br.readLine();
			int len = input.length();
			int prev = input.charAt(0) - 'A', next = 0;
			for (int i = 0; i < N; i++) {
				if((i & (1 << prev)) != 0) D[0][i] = 1;
				else D[0][i] = 0;
			}
			System.out.println(Arrays.toString(D[0]));
			
			for (int i = 1; i < len; i++) {
				next = input.charAt(i) - 'A';
				// next
				for (int j = 0; j < N; j++) {
					// next 책임자 없을 경우
					if((j & (1 << next)) == 0) continue;
					// next에 prev 책임자 없을 경우
					System.out.println("계산 전");
					System.out.println(Arrays.toString(D[1]));
					if((j & (1 << prev)) == 0) {
						System.out.println("next에 prev 책임자 없을 경우");
						subSet(0, 0, prev, next, D);
						System.out.println(Arrays.toString(D[1]));
					} else { // next에 prev 책임자 있을 경우
						System.out.println("next에 prev 책임자 있을 경우");
						subSet(0, 1 << prev, prev, next, D);
						System.out.println(Arrays.toString(D[1]));
					}
				}
				System.out.println("????????????");
				System.out.println(Arrays.toString(D[0]));
				System.out.println(Arrays.toString(D[1]));
				swap(D[0], D[1], temp);
				prev = next;
			}
			int res = 0;
			for (int i = 0; i < N; i++) {
				res = (res + D[0][i]) % MOD;
			}
			sb.append(res).append("\n");
			
		}
		br.close();
		System.out.println(sb);
	}
	
	private static void swap(int[] prev, int[] next, int[] temp) {
		temp = prev;
		prev = next;
		next = temp;
		Arrays.fill(next, 0);
	}

	private static void subSet(int idx, int flag, int prev, int next, int[] dPrev, int[] dNext) {
		if(idx == 4) {
//			System.out.println(Integer.toBinaryString(flag));
			if(flag != 0) {
				int prevFlag = flag | (1 << prev);
				int nextFlag = flag | (1 << next);
//				System.out.println(Integer.toBinaryString(prevFlag) + " : " + Integer.toBinaryString(nextFlag));
				for (int i = 0; i < N; i++) {
//					System.out.println(Integer.toBinaryString(i) + " : " + Integer.toBinaryString(prevFlag) + " = " + Integer.toBinaryString(i & prevFlag));
					if((i & prevFlag) == prevFlag) {
						System.out.println(dNext[nextFlag] + " : " + dPrev[i]);
						dNext[nextFlag] = (dNext[nextFlag] + dPrev[i]) % MOD;
					}
				}
			} 
			System.out.println("~~~~~~~~~~~~~~~~~~~");
			System.out.println(Arrays.toString(dNext));
			return;
		}
		
		if(idx != next && idx != prev) subSet(idx + 1, flag | (1 << idx), prev, next, dPrev, dNext);
		subSet(idx + 1, flag, prev, next, dPrev, dNext);
	}
	private static void subSet(int idx, int flag, int prev, int next, int[][] D) {
		if(idx == 4) {
//			System.out.println(Integer.toBinaryString(flag));
			if(flag != 0) {
				int prevFlag = flag | (1 << prev);
				int nextFlag = flag | (1 << next);
//				System.out.println(Integer.toBinaryString(prevFlag) + " : " + Integer.toBinaryString(nextFlag));
				for (int i = 0; i < N; i++) {
//					System.out.println(Integer.toBinaryString(i) + " : " + Integer.toBinaryString(prevFlag) + " = " + Integer.toBinaryString(i & prevFlag));
					if((i & prevFlag) == prevFlag) {
						System.out.println(D[1][nextFlag] + " : " + D[0][i]);
						D[1][nextFlag] = (D[1][nextFlag] + D[0][i]) % MOD;
						System.out.println(D[1][nextFlag]);
					}
				}
			} 
			System.out.println("~~~~~~~~~~~~~~~~~~~");
			System.out.println(Arrays.toString(D[1]));
			return;
		}
		
		if(idx != next && idx != prev) subSet(idx + 1, flag | (1 << idx), prev, next, D);
		subSet(idx + 1, flag, prev, next, D);
	}
}
