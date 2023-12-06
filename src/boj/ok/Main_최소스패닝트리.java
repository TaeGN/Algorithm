package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main_최소스패닝트리 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int V = Integer.parseInt(input[0]);
		int E = Integer.parseInt(input[1]);
		
		// 입력 데이터를 넣은 arrayList배열 생성 & 초기화
		List[] list = new ArrayList[V + 1];
		for (int i = 0; i <= V; i++) {
			list[i] = new ArrayList<Node>();
		}
		
		for (int i = 0; i < E; i++) {
			input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			int weight = Integer.parseInt(input[2]);
			list[from].add(new Node(to, weight));
			list[to].add(new Node(from, weight));
		}
		br.close();
		
		// pq & visited배열 초기화
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];
		
		// 1부터 시작 -> pq에 미리 넣기
		pq.addAll(list[1]);
		visited[1] = true;
		int cnt = 0;
		long res = 0;
		while(cnt < V - 1) {
			Node cur = pq.poll();
			
			// 이미 방문한 idx면 다시 뽑기
			if(visited[cur.idx]) continue;
			
			// 방문 체크
			visited[cur.idx] = true;
			
			pq.addAll(list[cur.idx]);
			res += cur.weight;
			
			cnt++;
		}
		
		System.out.println(res);
	}
	
	
	static class Node implements Comparable<Node> {
		int idx;
		int weight;
		
		public Node(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
}
