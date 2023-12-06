package swea.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_모의_벌꿀채취_노태균2 {
	static int N, M, C;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] input;
		int T = Integer.parseInt(br.readLine());
		int[][] map;
		List<Point> list = new ArrayList<>();
		for(int tc = 1; tc <= T; tc++) {
			input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			C = Integer.parseInt(input[2]);
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				int j = 0;
				for(String s : br.readLine().split(" ")) {
					map[i][j] = Integer.parseInt(s);
					j++;
				}
			}
			
			int[][] revenue = getRevenueArr(map); 
			PriorityQueue<Point> queue = new PriorityQueue<>();
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N - M + 1; c++) {
					queue.offer(new Point(r, c, revenue[r][c]));
				}
			}
			int result = 0;
			Point point;
			list.add(queue.poll());
			while(!queue.isEmpty()) {
				point = queue.poll();
				for(int i = 0; i < list.size(); i++) {
					if(list.get(i).r != point.r || Math.abs(list.get(i).c - point.c) >= M) {
						result = Math.max(result, list.get(i).value + point.value);
					}
				}
				list.add(point);
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
			list.clear();
		}
		br.close();
		System.out.println(sb);
	}
	
	
	// 수익 배열 만들기
	private static int[][] getRevenueArr(int[][] map) {
		int[][] revenue = new int[N][N - M + 1];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N - M + 1; c++) {
				revenue[r][c] = getRevenue(map[r], c, c + M);
			}
		}
		return revenue;
	}
	
	// 수익 구하기
	private static int getRevenue(int[] numbers, int start, int end) {
		int sum = 0, result = 0;
		for(int i = start;  i < end; i++) {
			sum += numbers[i];
		}
		if(sum > C) return getMaxRevenue(0, start, end, numbers, new boolean[N]);
		for(int i = start;  i < end; i++) result += (numbers[i] * numbers[i]);
		return result;
	}
	
	// 최대 수익
	private static int getMaxRevenue(int sum, int idx, int end, int[] numbers, boolean[] isSelected) {
		if(idx == end) {
			int result = 0;
			int i = 0;
			for(boolean tf : isSelected) {
				if(tf) result += numbers[i] * numbers[i];
				i++;
			}
			return result;
		}
		
		int result = 0;
		if(sum + numbers[idx] <= C) {
			isSelected[idx] = true;
			result = Math.max(result, getMaxRevenue(sum + numbers[idx], idx + 1, end, numbers, isSelected));
			isSelected[idx] = false;
		}
		result = Math.max(result, getMaxRevenue(sum, idx + 1, end, numbers, isSelected));
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
			if(this.value != o.value) return Integer.compare(this.value, o.value);
			return Integer.compare(this.c, o.c);
		}
		
	}
}
