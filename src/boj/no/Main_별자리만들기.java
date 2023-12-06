package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_별자리만들기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 별들의 위치 저장
		Star[] stars = new Star[N];
		for (int i = 0; i < N; i++) {
			stars[i] = new Star(br.readLine().split(" "));
		}
		
		// 거리 저장
		double[][] cost = new double[N][N];
		for (int from = 0; from < N - 1; from++) {
			for (int to = from + 1; to < N; to++) {
				cost[from][to] 
					= cost[to][from] 
					= Math.sqrt(Math.pow(stars[from].x - stars[to].x, 2) + Math.pow(stars[from].y - stars[to].y, 2));
			}
		}
		
		System.out.println(Arrays.deepToString(cost));
		
		PriorityQueue<Line> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		// 시작지점 0
		for (int i = 1; i < N; i++) {
			pq.offer(new Line(i, cost[0][i]));
		}
		visited[0] = true;
		
		double res = 0;
		int cnt = 1;
		while(cnt < N) {
			
			// 현재 갈 수 있는 경로들 중 cost가 가장 낮은거 뽑기
			Line line = pq.poll();
			
			int from = line.idx;
			// 이미 방문한 경우 다시 뽑기
			if(visited[from]) continue;
			
			// 방문체크
			visited[from] = true;
			cnt++;
			
			// cost 더해주기
			res += line.cost;
			
			// pq에 갈 수 있는 경로 추가
			for (int to = 0; to < N; to++) {
				if(visited[to] || from == to) continue;
				pq.offer(new Line(to, cost[from][to]));
			}
		}
		
		br.close();
		System.out.println(res);
	}
	
	static class Line implements Comparable<Line> {
		private int idx;
		private double cost;
		
		public Line(int idx, double cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Line o) {
			return Double.compare(this.cost, o.cost);
		}
	}
	
	static class Star {
		private double x, y;

		public Star(String[] input) {
			this.x = Double.parseDouble(input[0]);
			this.y = Double.parseDouble(input[1]);
		}
	}
}
