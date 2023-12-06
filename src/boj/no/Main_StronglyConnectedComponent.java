package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_StronglyConnectedComponent {
	
	static final int INF = Integer.MAX_VALUE;
	static final int VISIT = -1;
	static int cnt, startIdx, size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		int V = Integer.parseInt(input[0]);
		int E = Integer.parseInt(input[1]);
		
		ArrayList<Integer>[] connect = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			connect[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < E; i++) {
			input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			connect[from].add(to);
		}
		
		int[] visited = new int[V + 1];
		
		for (int i = 1; i <= V; i++) {
			if(visited[i] != 0) continue;
			dfs(i, visited, connect, 1);
			System.out.println(Arrays.toString(visited));
		}
		
		System.out.println(Arrays.toString(visited));
	}
	
	static boolean dfs(int from, int[] visited, ArrayList<Integer>[] connect, int flag) {
		System.out.println(from + " : " + flag);
		if(visited[from] > 0) {
			cnt++;
			startIdx = visited[from];
			size = flag - startIdx;
			return true;
		}
		// 방문 체크
		visited[from] = flag;
		
		boolean isEnd = false;
		for (int to : connect[from]) {
			if(visited[to] < 0) continue;
			if(dfs(to, visited, connect, flag + 1)) {
				isEnd = true;
				break;
			}
		}
//		System.out.println(startIdx + " : " + flag);
		if(isEnd && startIdx <= flag) {
			visited[from] = -cnt * 10000;
//			System.out.println("????");
		} else {
			visited[from] = 0;
		}
		return isEnd;
	}
}
