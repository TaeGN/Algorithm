package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_KUBCLeagueSmall {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N][1 << N];
		boolean[][] map = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = input[j].equals("1");
			}
		}
		br.close();
		
		// 최대 길이의 꼬리를 무는 선수 나열 저장
		int[] next = new int[N];
		Arrays.fill(next, -1);
		
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		tsp(0, 1, N, map, dp, next, -1000000);
		for (int i = 0; i != -1; i = next[i]) {
			sb.append(i + 1).append(" ");
			cnt++;
		}
		System.out.println(Arrays.toString(next));
		System.out.println(cnt);
		System.out.println(sb);
	}	
	
	private static int tsp(int from, int visited, int N, boolean[][] map, int[][] dp, int[] next, final int INF) {
		
		// 이미 구해놓은 dp값이 있으면 연산 필요 없이 리턴
		if(dp[from][visited] != 0) return dp[from][visited] == INF ? 0 : dp[from][visited];
		
		// 전부 방문했으면 시작지점 0으로 되돌아가기
		if(visited == (1 << N) - 1) return dp[from][visited] = 0; 
		
		// dp에 from -> 도착지까지의 최소값 저장
		int curDp = INF;
		for (int to = 0; to < N; to++) {
			if((visited & (1 << to)) != 0 || !map[from][to]) continue;
			int nextDp = tsp(to, visited | (1 << to), N, map, dp, next, INF) + 1;
			if(curDp < nextDp) {
				next[from] = to;
				curDp = nextDp;
			}
		}
		
		return dp[from][visited] = curDp;
	}
}
