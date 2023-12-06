package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_도시분할계획 {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		// pq에 M개의 길 넣기 (유지비가 적은 순으로 정렬)
		PriorityQueue<Road> pq = new PriorityQueue<>(M);
		for (int i = 0; i < M; i++) {
			pq.offer(new Road(br.readLine().split(" ")));
		}
		
		// N개 도로의 union 정보 저장
		parent = makeSet(N);
		System.out.println(pq);
		int res = 0;
		int cnt = 0;
		while(cnt < N - 2) {
			// 최소 유지비의 도로를 뽑기
			Road road = pq.poll();
			// 이미 연결되어 있는 도로 제외
			if(!union(road.A, road.B)) continue;
			
			res += road.C;
			cnt++;
		}
		br.close();
		System.out.println(res);
	}
	
	static int[] makeSet(int N) {
		int[] parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		return parent;
	}
	
	static int findSet(int a) {
		if(parent[a] == a) return a;
		return parent[a] = findSet(parent[a]);
	}
	
	static boolean union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if(a == b) return false;
		
		parent[a] = b;
		return true;
	}

	
	static class Road implements Comparable<Road> {
		int A, B, C;

		public Road(String[] input) {
			A = Integer.parseInt(input[0]);
			B = Integer.parseInt(input[1]);
			C = Integer.parseInt(input[2]);
		}

		@Override
		public int compareTo(Road o) {
			return Integer.compare(this.C, o.C);
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Road [A=").append(A).append(", B=").append(B).append(", C=").append(C).append("]");
			return builder.toString();
		}
		
		
	}
}
