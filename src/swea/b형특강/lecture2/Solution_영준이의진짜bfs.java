package swea.b형특강.lecture2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_영준이의진짜bfs {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		final int MAX_N = 100000;
		NodePool nodePool = new NodePool(MAX_N);
		Queue queue = new Queue(MAX_N);
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// init
			nodePool.init();
			queue.init();
			
			int N = Integer.parseInt(br.readLine());
			String[] input = br.readLine().split(" ");
			nodePool.set(1, 0, null);
			for (int child = 2; child <= N; child++) {
				int parent = Integer.parseInt(input[child - 2]);
				Node parentNode = nodePool.get(parent);
				Node childNode = nodePool.set(child, parentNode.h + 1, parentNode);
				parentNode.addChild(childNode);
			}
			
			int res = 0;
			Node prev = nodePool.get(1);
			queue.offer(prev);
			while(!queue.isEmpty()) {
				for (int i = 0, size = queue.size(); i < size; i++) {
					Node next = queue.poll();
					res += getLen(prev, next);
					prev = next;
					if(next.children.isEmpty()) continue;
					Collections.sort(next.children);
					for (Node node : next.children) {
						queue.offer(node);
					}
				}
			}
			
			sb.append(res).append("\n");
		}
		br.close();
		System.out.println(sb);
	}
	
	private static int getLen(Node prev, Node next) {
		int len = 0;
		if(prev.h > next.h) {
			while(prev.h != next.h) {
				prev = prev.parent;
				len++;
			}
		} else {
			while(prev.h != next.h) {
				next = next.parent;
				len++;
			}
		}
		
		while(prev.val != next.val) {
			prev = prev.parent;
			next = next.parent;
			len += 2;
		}
		return len;
	}
	
	static class Queue {
		Node[] queue;
		int inIdx, outIdx;
		public Queue(int size) {
			this.queue = new Node[size];
			init();
		}
		public void init() {
			this.outIdx = -1;
			this.inIdx = -1;
		}
		public void offer(Node node) {
			this.queue[++inIdx] = node;
		}
		public Node poll() {
			return this.queue[++outIdx];
		}
		public int size() {
			return inIdx - outIdx;
		}
		public boolean isEmpty() {
			return inIdx <= outIdx;
		}
	}

	static class Node implements Comparable<Node> {
		int val, h;
		Node parent;
		List<Node> children;
		public Node() {
			this.children = new ArrayList<>();
		}
		public void addChild(Node childNode) {
			this.children.add(childNode);
		}
		public void set(int val, int h, Node parent) {
			this.val = val;
			this.h = h;
			this.parent = parent;
			this.children.clear();
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.val, o.val);
		}
	}
	
	static class NodePool {
		Node[] pool;
		int idx;
		public NodePool(int size) {
			this.pool = new Node[size + 1];
			for (int i = 1; i <= size; i++) {
				this.pool[i] = new Node();
			}
		}
		public void init() {
			this.idx = 0;
		}
		public Node get(int idx) {
			return this.pool[idx];
		}
		public Node set(int val, int h, Node parent) {
			Node node = this.pool[val];
			node.set(val, h, parent);
			return node;
		}
	}
}
