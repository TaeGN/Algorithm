package swea.b형특강.lecture1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_이진수표현 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int M = Integer.parseInt(input[1]);
			sb.append("#").append(tc).append(" ").append(getResult(N, M)).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}

	private static String getResult(int n, int m) {
		return (((1 << n) - 1) | m) == m ? "ON" : "OFF";
	}
}
