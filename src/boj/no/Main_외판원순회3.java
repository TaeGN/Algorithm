package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_외판원순회3 {
	
	static int N;
	static double dp[][], distance[][];
	static City[] cities;
	static final int INF = 10000000;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cities = new City[N];
		distance = new double[N][N];
		dp = new double[N][1 << N];
		
		for (int i = 0; i < N; i++) {
			cities[i] = new City(br.readLine().split(" "));
		}
		
		br.close();
		System.out.println(tsp(0, 1));
	}	
	
	private static double tsp(int from, int visited) {
		
		// 이미 구해놓은 dp값이 있으면 연산 필요 없이 리턴
		if(dp[from][visited] != 0) return dp[from][visited];
		
		// 전부 방문했으면 시작지점 0으로 되돌아가기
		if(visited == (1 << N) - 1) return dp[from][visited] = getDistance(from, 0); 
		
		// dp에 from -> 도착지까지의 최소값 저장
		dp[from][visited] = INF;
		for (int to = 0; to < N; to++) {
			if((visited & (1 << to)) != 0) continue;
			dp[from][visited] = Math.min(dp[from][visited], tsp(to, visited | (1 << to)) + getDistance(from, to));
		}
		
		return dp[from][visited];
	}

	public static double getDistance(int idx1, int idx2) {
		// 이미 구해놓은 값 있으면 리턴
		if(distance[idx1][idx2] != 0) return distance[idx1][idx2];
		City c2 = cities[idx2];
		City c1 = cities[idx1];
		// 거리 값을 distance에 저장하면서 리턴
		return distance[idx1][idx2] 
				= distance[idx2][idx1] 
				= Math.sqrt(Math.pow(c1.r - c2.r, 2) + Math.pow(c1.c - c2.c, 2));
	}
	
	static class City {
		int r, c;

		public City(String[] input) {
			this.r = Integer.parseInt(input[0]);
			this.c = Integer.parseInt(input[1]);
		}
		
	}
}
