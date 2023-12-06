package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main_11438_LCA2 {
	
	static final int ROOT = 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] tree = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			String[] input = br.readLine().split(" ");
			int A = Integer.parseInt(input[0]);
			int B = Integer.parseInt(input[1]);
			tree[A].add(B);
			tree[B].add(A);
		}
		
		final int MAX_I = (int) Math.ceil((Math.log(N) / Math.log(2)));
		int[][] parent = new int[N + 1][MAX_I + 1];
		int[] depth = new int[N + 1];
		
		bfs(N, tree, parent, depth);
		
		for (int j = 0; j < MAX_I; j++) {
			for (int i = 1; i <= N; i++) {
				parent[i][j + 1] = parent[parent[i][j]][j];
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		
		int temp, diff;
		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			int A = Integer.parseInt(input[0]);
			int B = Integer.parseInt(input[1]);
			
			if(depth[A] < depth[B]) {
				temp = A;
				A = B;
				B = temp;
			}
			
			// depth를 같게 맞춰주기
			diff = depth[A] - depth[B];
			if(diff != 0) {
				for (int j = MAX_I; j >= 0; j--) {
					if((diff & (1 << j)) != 0) A = parent[A][j];
				}
			}
			
			// 가장 가까운 공통조상 찾기
			if(A != B) {
				for (int j = MAX_I; j >= 0; j--) {
					if(parent[A][j] != parent[B][j]) {
						A = parent[A][j];
						B = parent[B][j];
					}
				}
				A = parent[A][0];
			}
			
			sb.append(A).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static void bfs(int N, ArrayList<Integer>[] tree, int[][] parent, int[] depth) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		
		queue.offer(ROOT);
		visited[ROOT] = true;
		
		int cur;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			
			for (int next : tree[cur]) {
				if(visited[next]) continue;
				visited[next] = true;
				parent[next][0] = cur;
				depth[next] = depth[cur] + 1;
				queue.offer(next);
			}
		}
	}
}
