package programmers.lv3.ok.네트워크;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution1 {
	public static void main(String[] args) {
		int n = 3;
		int[][] computers = {
				{1, 1, 0},
				{1, 1, 1},
				{0, 1, 1}
		};
		System.out.println(Solution(n, computers));
	}
	
	static boolean[] visited;
	static Queue<Integer> queue;
	static int N, cnt;
	private static int Solution(int n, int[][] computers) {
		queue = new ArrayDeque<>();
		visited = new boolean[n];
		N = n;
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			queue.offer(i);
			visited[i] = true;
			network(computers);
			cnt++;
		}
		return cnt;
	}
	private static void network(int[][] computers) {
		int current;
		while(!queue.isEmpty()) {
			current = queue.poll();
			for(int i = 0; i < N; i++) {
				if(computers[current][i] == 1 && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
	}
}
