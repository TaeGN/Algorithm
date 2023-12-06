package swea.b형특강.lecture3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_힙 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		final int MAX_N = 100000;
		MaxHeap maxHeap = new MaxHeap(MAX_N);
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			int N = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; i++) {
				String[] input = br.readLine().split(" ");
				if(input[0].equals("1")) {
					maxHeap.offer(Integer.parseInt(input[1]));
				} else {
					sb.append(maxHeap.poll()).append(" ");
				}
			}
			
			sb.append("\n");
			maxHeap.init();
		}
		br.close();
		System.out.println(sb);
	}
	
	static class MaxHeap {
		int[] heap;
		int size, temp;
		public MaxHeap(int size) {
			this.heap = new int[size + 1];
		}
		public void init() {
			this.size = 0;
		}
		public boolean isEmpty() {
			return this.size == 0;
		}
		public void offer(int val) {
			this.heap[++size] = val;
			this.bottomUpSort();
		}
		public int poll() {
			if(this.isEmpty()) return -1;
			int res = this.heap[1];
			this.heap[1] = this.heap[size--];
			this.topDownSort();
			return res;
		}
		private void bottomUpSort() {
			int idx = this.size;
			while(idx > 1 && this.heap[idx] > this.heap[idx / 2]) {
				this.swap(idx, idx / 2);
				idx /= 2;
			}
		}
		private void topDownSort() {
			int idx = 1, next = 1;
			while(idx * 2 <= this.size) {
				if(this.heap[next] < this.heap[idx * 2]) {
					next = idx * 2;
				}
				if(idx * 2 + 1 <= this.size && this.heap[next] < this.heap[idx * 2 + 1]) {
					next = idx * 2 + 1;
				}
				if(next == idx) return;
				this.swap(idx, next);
				idx = next;
			}
		}
		private void swap(int a, int b) {
			this.temp = this.heap[a];
			this.heap[a] = this.heap[b];
			this.heap[b] = this.temp;
		}
	}
}
