package swea.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_2115_모의_벌꿀채취_노태균 {
	static int N, M, C;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		List<Point> list = new ArrayList<>();
		PriorityQueue<Point> queue = null;
		String[] input;
		int[][] map;
		for(int tc = 1; tc <= T; tc++) {
			input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			C = Integer.parseInt(input[2]);
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				int j = 0;
				for(String s : br.readLine().split(" ")) {
					map[i][j++] = Integer.parseInt(s);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(getResult(getRevenueQueue(queue, map), list)).append("\n");
			list.clear();
		}
		br.close();
		System.out.println(sb);
	}

	// 모든 경우의 최대 수익들을 우선순위 큐에 넣기
	private static PriorityQueue<Point> getRevenueQueue(PriorityQueue<Point> queue, int[][] map) {
		queue = new PriorityQueue<Point>(N * (N - M + 1));
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N - M + 1; c++) {
				queue.offer(new Point(r, c, getRevenue(map[r], c, c + M)));
			}
		}
		return queue;
	}
	
	// 선택된 벌통의 최대 수익 구하기
	private static int getRevenue(int[] numbers, int start, int end) {
		int sum = 0, result = 0;
		for(int i = start;  i < end; i++) {
			sum += numbers[i];
		}
		if(sum > C) return getMaxRevenue(0, start, end, numbers, 0);	// 일부 채취 가능
		for(int i = start;  i < end; i++) result += (numbers[i] * numbers[i]);		// 전부 채취 가능
		return result;
	}
	
	// 꿀의 채취량이 C를 넘지 않게 선택했을 때 얻을 수 있는 최대 수익
	private static int getMaxRevenue(int sum, int idx, int end, int[] numbers, int revenue) {
		if(idx == end) return revenue;
		
		int result = 0;
		if(sum + numbers[idx] <= C) {
			result = getMaxRevenue(sum + numbers[idx], idx + 1, end, numbers, revenue + numbers[idx] * numbers[idx]);
		}
		return Math.max(result, getMaxRevenue(sum, idx + 1, end, numbers, revenue));
	}
	
	// 결과 값 구하기
	private static int getResult(PriorityQueue<Point> queue, List<Point> list) {
		Point a = queue.poll();
		Point b = queue.poll();
		if(a.r != b.r || Math.abs(a.c - b.c) >= M) return a.value + b.value;
		Point c = queue.poll();
		if(a.r != c.r || Math.abs(a.c - c.c) >= M) return a.value + c.value;
		list.add(a); 
		list.add(b); 
		list.add(c);
		b = queue.poll();
		while(a.r == b.r && Math.abs(a.c - b.c) < M) {
			list.add(b);
			b = queue.poll();
		}
		int result = a.value + b.value;
		for(int i = 1, size = list.size(); i < size - 1; i++) {
			a = list.get(i);
			for(int j = i + 1; j < size; j++) {
				b = list.get(j);
				if(a.r != b.r || Math.abs(a.c - b.c) >= M) result = Math.max(result, a.value + b.value);
			}
		}
		return result;
	}
	
	static class Point implements Comparable<Point>{
		int r, c, value;
		
		public Point(int r, int c, int value) {
			this.r = r;
			this.c = c;
			this.value = value;
		}
		
		@Override
		public int compareTo(Point o) {
			return Integer.compare(o.value, this.value);
		}
	}
}
