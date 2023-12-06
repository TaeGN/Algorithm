package boj.no;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_수열과쿼리1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		// N개의 데이타 tree에 add
		String[] input = br.readLine().split(" ");
		SegmentTree tree = new SegmentTree(N);
		tree.add(input);
		
		int M = Integer.parseInt(br.readLine());
		
		// M번의 쿼리 실행
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int left = Integer.parseInt(input[0]);
			int right = Integer.parseInt(input[1]);
			int K = Integer.parseInt(input[2]);
			sb.append(tree.getResult(left, right, K)).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}

	static class SegmentTree {
		private long[] minTree;
		private long[] maxTree;
		private int dataSize;
		
		public SegmentTree(int size) {
			this.dataSize = size;
			int h = (int) Math.ceil(Math.log(size) / Math.log(2));
			int treeSize = (int) Math.pow(2, h + 1);
			this.minTree = new long[treeSize];
			this.maxTree = new long[treeSize];
		}
		
		public long add(String[] data, int node, int start, int end, long[] tree) {
			if(start == end) return tree[node] = Integer.parseInt(data[start - 1]);
			return tree[node] = 
					compare(add(data, node * 2, start, (start + end) / 2, tree),
							add(data, node * 2 + 1, (start + end) / 2 + 1, end, tree), tree);
		}
		
		private long compare(long leftChild, long rightChild, long[] tree) {
			if(tree == this.minTree) return Math.min(leftChild, rightChild);
			return Math.max(leftChild, rightChild);
		}

		public void add(String[] data) {
			this.add(data, 1, 1, this.dataSize, this.minTree);
			this.add(data, 1, 1, this.dataSize, this.maxTree);
		}
		
		public int getResult(int node, int start, int end, int left, int right, int K) {
			// 해당 구간에 포함하지 않으면 0리턴
			if(end < left || right < start) return 0;
			
			// 해당 구간에 포함될 경우
			if(left <= start && end <= right) {
				// 현 지점 MAX값이 K보다 작거나 같으면 0 리턴
				if(this.maxTree[node] <= K) return 0;
				// 현 지점 MIN값이 K보다 클 경우 하위 노드 개수 리턴
				else if(this.minTree[node] > K) return end - start + 1;
				// leaf노드에서 K보다 작을 경우 0 리턴
				else if(start == end) return 0;
			}
			// 나머지 경우 자식 노드 탐색
			return getResult(node * 2, start, (start + end) / 2, left, right, K)
					+ getResult(node * 2 + 1, (start + end) / 2 + 1, end, left, right, K);
		}
		
		public int getResult(int left, int right, int K) {
			return getResult(1, 1, this.dataSize, left, right, K);
		}
	}
}
