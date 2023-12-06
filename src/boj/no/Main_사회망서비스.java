package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main_사회망서비스 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] tree = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			String[] input = br.readLine().split(" ");
			Integer from = Integer.parseInt(input[0]);
			Integer to = Integer.parseInt(input[1]);
			tree[from].add(to);
			tree[to].add(from);
		}
		
		// left node 찾기
		Set<Integer> leafList = new HashSet<>();
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		queue.offer(1);
		visited[1] = true;
		
		while(!queue.isEmpty()) {
			Integer idx = queue.poll();
			boolean isLeaf = true;
			for (Integer next : tree[idx]) {
				if(visited[next]) continue;
				visited[next] = true;
				queue.offer(next);
				isLeaf = false;
			}
			
			if(isLeaf) leafList.add(idx);
		}
		
		for (Integer idx : leafList) {
			queue.offer(idx);
//			visited[idx] = false;
		}
		
		Set<Integer> set = new HashSet<>();
		
		int cnt = 0, res = 0;
		while(!queue.isEmpty()) {
			System.out.println(cnt + " : " + queue);
			int size = queue.size();
			int n = 0;
			for (int i = 0; i < size; i++) {
				Integer idx = queue.poll();
				if(!set.add(idx)) continue;
				visited[idx] = false;
				n++;
				for (Integer next : tree[idx]) {
					if(!visited[next]) continue;
					queue.offer(next);
				}
			}
			System.out.println(n);
			if(++cnt % 2 == 0) res += n;
			set.clear();
		}
		
		System.out.println(res);
	}
}
	
