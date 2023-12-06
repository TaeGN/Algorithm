package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main_11438_LCA2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] nodePair = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			nodePair[i] = new ArrayList<>();
		}
		// nodePair 채우기
		for (int i = 0; i < N - 1; i++) {
			String[] input = br.readLine().split(" ");
			int A = Integer.parseInt(input[0]);
			int B = Integer.parseInt(input[1]);
			nodePair[A].add(B);
			nodePair[B].add(A);
		}
		
		Node[] tree = new Node[N + 1];
		tree[1] = new Node(1, 1, 0);
		dfs(1, 1, nodePair, tree);
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			int A = Integer.parseInt(input[0]);
			int B = Integer.parseInt(input[1]);
			Node aNode = tree[A];
			Node bNode = tree[B];
			while(aNode.h != bNode.h) {
				if(aNode.h > bNode.h) aNode = tree[aNode.parent];
				else bNode = tree[bNode.parent];
			}
			while(aNode != bNode) {
				aNode = tree[aNode.parent];
				bNode = tree[bNode.parent];
			}
			sb.append(aNode.idx).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}

	private static void dfs(int from, int h, ArrayList<Integer>[] nodePair, Node[] tree) {
		ArrayList<Integer> list = nodePair[from];
		nodePair[from] = null;
		for (int to : list) {
			if(nodePair[to] == null) continue;
			tree[to] = new Node(from, to, h);
			dfs(to, h + 1, nodePair, tree);
		}
	}
	
	static class Node {
		int h, parent, idx;
		public Node(int parent, int idx, int h) {
			super();
			this.parent = parent;
			this.h = h;
			this.idx = idx;
		}
	}
}
