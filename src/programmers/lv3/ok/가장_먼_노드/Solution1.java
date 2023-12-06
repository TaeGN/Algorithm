package programmers.lv3.ok.가장_먼_노드;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution1 {
	public static void main(String[] args) {
		int n = 6;
		int[][] edge = {
				{3,6},	
				{4,3},	
				{3,2},	
				{1,3},	
				{1,2},	
				{2,4},	
				{5,2}
		};
		System.out.println(Solution(n,edge));
	}
	
	private static int Solution(int n, int[][] edge) {
		Node[] graph = new Node[n + 1];
		int a, b;
		for(int[] arr : edge) {
			a = arr[0];
			b = arr[1];
			graph[a] = new Node(b, graph[a]);
			graph[b] = new Node(a, graph[b]);
		}
		
		boolean[] visited = new boolean[n + 1];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(1);
		visited[1] = true;
		int size = 0, cur;
		Node curLink;
		while(!queue.isEmpty()) {
			size = queue.size();
			for(int i = 0; i < size; i++) {
				cur = queue.poll();
				curLink = graph[cur];
				while(curLink != null) {
					cur = curLink.vertex;
					if(!visited[cur]) {
						queue.offer(cur);
						visited[cur] = true;
					}
					curLink = curLink.link;
				}
			}
		}
			
		return size;
	}

	static class Node {
		int vertex;
		Node link;
		
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}
	}
}
