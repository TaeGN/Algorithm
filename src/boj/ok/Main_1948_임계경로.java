package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main_1948_임계경로 {
	static int N, M;
	static List<Road>[] roadArr;
	static class Road implements Comparable<Road>{
		int to, time;

		public Road(int next, int time) {
			super();
			this.to = next;
			this.time = time;
		}

		@Override
		public int compareTo(Road o) {
			return Integer.compare(o.time, this.time);
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		roadArr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			roadArr[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			int time = Integer.parseInt(input[2]);
			roadArr[from].add(new Road(to, time));
		}
		String[] input = br.readLine().split(" ");
		int start = Integer.parseInt(input[0]);
		int end = Integer.parseInt(input[1]);
		PriorityQueue<Road> pq = new PriorityQueue<>();
		pq.addAll(roadArr[start]);
		int[] D = new int[N + 1];
		Road cur;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			if(cur.time <= D[cur.to]) continue;
			
		}
		
	}
}
