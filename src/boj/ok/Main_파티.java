package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_파티 {
	
	static EdgePool edgePool;
	static int N, M, X;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		X = Integer.parseInt(input[2]);
		
		edgePool = new EdgePool(M * 4);
		ArrayList<Edge>[] forward = new ArrayList[N + 1];
		ArrayList<Edge>[] reverse = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			forward[i] = new ArrayList<>();
			reverse[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			int time = Integer.parseInt(input[2]);
			forward[from].add(edgePool.get(to, time));
			reverse[to].add(edgePool.get(from, time));
		}
		
		int[] D_forward = new int[N + 1];
		int[] D_reverse = new int[N + 1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dijkstra(D_forward, pq, forward);
		dijkstra(D_reverse, pq, reverse);
		int res = 0;
		for (int i = 1; i <= N; i++) {
			res = Math.max(res, D_forward[i] + D_reverse[i]);
		}
		System.out.println(res);
	}
	
	static void dijkstra(int[] D, PriorityQueue<Edge> pq, ArrayList<Edge>[] list) {
		Arrays.fill(D, Integer.MAX_VALUE);
		pq.clear();
		D[X] = 0;
		pq.offer(edgePool.get(X, 0));
		
		int cnt = 0;
		while(cnt < N) {
			// 방문하지 않은 최소 D[i]값 뽑기
			Edge cur = pq.poll();
			
			// 유효하지 않은 값 배제
			if(D[cur.idx] < cur.time) continue;
			
			for (Edge next : list[cur.idx]) {
				if(D[next.idx] > D[cur.idx] + next.time) {
					D[next.idx] = D[cur.idx] + next.time;
					pq.offer(edgePool.get(next.idx, D[next.idx]));
				}
			}
			
			cnt++;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int idx, time;
		public Edge() {}
		public void set(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.time, o.time);
		}
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Edge [idx=").append(idx).append("]");
			return builder.toString();
		}
	}
	
	static class EdgePool {
		Edge[] pool;
		int idx;
		public EdgePool(int size) {
			this.pool = new Edge[size];
			for (int i = 0; i < size; i++) {
				this.pool[i] = new Edge();
			}
			this.idx = -1;
		}
		public Edge get(int idx, int time) {
			Edge edge = this.pool[++this.idx];
			edge.set(idx, time);
			return edge;
		}
	}
}
