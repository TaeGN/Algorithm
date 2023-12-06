package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_외판원순회 {
	
	static int N, map[][], dp[][];
	static final int INF = 10000000;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		dp = new int[N][1 << N];
		
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			int[] line = map[i];
			for (int j = 0; j < N; j++) {
				line[j] = Integer.parseInt(input[j]);
			}
		}
		
		br.close();
		System.out.println(dfs(0, 1));
	}	
	
	public static int dfs(int from, int visited) {
		
		// 이미 존재하는 값이면 리턴
		if(dp[from][visited] != 0) return dp[from][visited];
		
		// 전부 방문했으면 0으로 되돌아가기
		if(visited == (1 << N) - 1) {
			return dp[from][visited] = map[from][0] != 0 ? map[from][0] : INF;
		}
		
		// 현재 위치에서 도착지까지의 최소비용 구하기 -> dp[from][visited]
		dp[from][visited] = INF;
		
		for (int to = 0; to < N; to++) {
			// 이미 방문 or 길이 없는 경우 제외
			if((visited & (1 << to)) != 0 || map[from][to] == 0) continue;
			dp[from][visited] = Math.min(dp[from][visited], dfs(to, visited | (1 << to)) + map[from][to]);
		}
		
		return dp[from][visited];
	}
	
}
