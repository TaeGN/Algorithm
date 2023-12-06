package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main_거의최단경로_dijkstra {
	
	static final int INF = Integer.MAX_VALUE >> 2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int MAX_N = 500;
		final int MAX_M = 10000;
		int len, minLen;
		
		Edge[] pool = new Edge[MAX_M];
		for (int i = 0; i < MAX_M; i++) {
			pool[i] = new Edge();
		}
		
		ArrayList<Edge>[] list = new ArrayList[MAX_N];
		for (int i = 0; i < MAX_N; i++) {
			list[i] = new ArrayList<Edge>();
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] dp = new int[MAX_N];
		ArrayList<Integer>[] route = new ArrayList[MAX_N];
		for (int i = 0; i < MAX_N; i++) {
			route[i] = new ArrayList<Integer>();
		}
		
		while(true) {
			String[] input = br.readLine().split(" ");
			int N = readInt(input[0]);
			int M = readInt(input[1]);	
			
			if(N == 0 && M == 0) break;
			
			// init 
			for (int i = 0; i < N; i++) {
				list[i].clear();
			}
			
			input = br.readLine().split(" ");
			int S = readInt(input[0]);
			int D = readInt(input[1]);
			
			for (int i = 0; i < M; i++) {
				input = br.readLine().split(" ");
				list[readInt(input[0])].add(pool[i].set(readInt(input[1]), readInt(input[2])));
			}
			

			dijkstra(S, D, dp, route, pq, list, N);
			removeRoute(S, D, route, list);
			sb.append(dijkstra(S, D, dp, route, pq, list, N)).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static void dijkstraInit(int S, int N, int[] dp, ArrayList<Integer>[] route, PriorityQueue<Edge> pq) {
		for (int i = 0; i < N; i++) {
			dp[i] = INF;
			route[i].clear();
		}
		pq.clear();
		dp[S] = 0;
		pq.offer(new Edge(S, dp[S]));
	}
	
	static int dijkstra(int S, int D, int[] dp, ArrayList<Integer>[] route, PriorityQueue<Edge> pq, ArrayList<Edge>[] list, int N) {
		// init
		dijkstraInit(S, N, dp, route, pq);
		
		while(!pq.isEmpty()) {
			
			Edge cur = pq.poll();
			
			// 유효한 값이 아니면 다시 뽑기
			if(cur.weight > dp[cur.idx]) continue;
			
			// 도착점에 도달하면 종료
			if(cur.idx == D) return dp[D];
			
			for (Edge next : list[cur.idx]) {
				if(dp[next.idx] > dp[cur.idx] + next.weight) {
					dp[next.idx] = dp[cur.idx] + next.weight;
					route[next.idx].clear();
					route[next.idx].add(cur.idx);
					pq.offer(new Edge(next.idx, dp[next.idx]));
				} else if(dp[next.idx] == dp[cur.idx] + next.weight) {
					route[next.idx].add(cur.idx);
				}
			}
		}
		
		return -1;
	}
	
	static void removeRoute(int S, int D, ArrayList<Integer>[] route, ArrayList<Edge>[] list) {
		if(S == D) return;
		
		for (int idx : route[D]) {
			for (int j = 0, size = list[idx].size(); j < size; j++) {
				if(list[idx].get(j).idx == D) {
					list[idx].remove(j);
					removeRoute(S, idx, route, list);
					break;
				}
			}
		}
	}
	
	static int readInt(String s) {
		return Integer.parseInt(s);
	}
	
	static class Edge implements Comparable<Edge> {
		int idx, weight;
		public Edge() {}
		public Edge(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
		public Edge set(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
			return this;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
}
