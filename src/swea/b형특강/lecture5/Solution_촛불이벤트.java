package swea.b형특강.lecture5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_촛불이벤트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			long N = Long.parseLong(br.readLine());
			long K = (long) (Math.sqrt(2 * N + 0.25) - 0.5);
			if(K * (K + 1) == 2 * N) sb.append(K);
			else sb.append(-1);
			sb.append("\n");
		}
		System.out.println(Long.MAX_VALUE);
		
		br.close();
		System.out.print(sb);
	}	
}
