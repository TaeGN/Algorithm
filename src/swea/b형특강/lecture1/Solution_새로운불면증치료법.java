package swea.b형특강.lecture1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_새로운불면증치료법 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			sb.append("#").append(tc).append(" ").append(getResult(N)).append("\n");
		}
		br.close();
		System.out.println(sb);
	}

	private static int getResult(int n) {
		int flag = 0;
		int res = 0;
		while(flag != (1 << 10) - 1) {
			res++;
			int m = n * res;
			while(m > 0) {
				flag |= (1 << m % 10);
				m /= 10;
			}
		}
		return n * res;
	}
}
