package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main_최소비용구하기2 {
	
	static int[] visitArr;
	static int visitCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		EdgePool edgePool = new EdgePool(M * 4);
		ArrayList<Edge>[] bus = new ArrayList[N + 1];
		ArrayList<Edge>[] reverseBus = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			bus[i] = new ArrayList<>();
			reverseBus[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			int weight = Integer.parseInt(input[2]);
			bus[from].add(edgePool.get(to, weight));
			reverseBus[to].add(edgePool.get(from, weight));
		}
		
		String[] input = br.readLine().split(" ");
		int start = Integer.parseInt(input[0]);
		int end = Integer.parseInt(input[1]);
		
		br.close();
		
		PriorityQueue<Edge> pq = new PriorityQueue<>(M);
		List<Integer> visitList = new ArrayList<>();
		int[] D = new int[N + 1];
		Arrays.fill(D, Integer.MAX_VALUE);
		D[start] = 0;
		pq.offer(edgePool.get(start, 0));
		
		int cnt = 1;
		while(cnt <= N) {
			
			// 현재 갈 수 있는 지점들 중 비용이 최소인 곳 선택
			Edge cur = pq.poll();
			
			// 유효한 값이 아니면 다시뽑기
			if(cur.weight > D[cur.idx]) continue;
			
			// 방문체크
			visitList.add(cur.idx);
			
			// 도착점에 도달하면 종료
			if(cur.idx == end) break;
			
			// 새롭게 방문 가능하게 된 지점을 pq에 추가
			for (Edge next : bus[cur.idx]) {
				if(D[next.idx] > D[cur.idx] + next.weight) {
					D[next.idx] = D[cur.idx] + next.weight;
					pq.offer(edgePool.get(next.idx, D[next.idx]));
				}
			}
			
			cnt++;
		}
		
		visitArr = new int[visitList.size()];
		dfs(end, start, reverseBus, D);
		
		StringBuilder sb = new StringBuilder();
		sb.append(D[end]).append("\n");
		sb.append(visitCnt + 1).append("\n");
		for (int i = visitCnt; i >= 0 ; i--) {
			sb.append(visitArr[i]).append(" ");
		}
		System.out.println(sb);
	}
	
	static boolean dfs(int idx, int end, ArrayList<Edge>[] reverseBus, int[] D) {
		visitArr[visitCnt] = idx;
		if(idx == end) return true;
		visitCnt++;
		
		for (Edge next : reverseBus[idx]) {
			if(D[idx] == D[next.idx] + next.weight) {
				if(dfs(next.idx, end,  reverseBus, D)) return true;
			}
		}
		
		visitCnt--;
		return false;
	}
	
	static class Edge implements Comparable<Edge> {
		int idx, weight;
		public Edge() {}
		public void set(int to, int weight) {
			this.idx = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
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
		public Edge get(int to, int weight) {
			Edge edge = this.pool[++idx];
			edge.set(to, weight);
			return edge;
		}
	}
}
