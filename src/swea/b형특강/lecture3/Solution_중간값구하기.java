package swea.b형특강.lecture3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_중간값구하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		final int MAX_N = 200000;
		final int MOD = 20171109;
		MidHeap midHeap = new MidHeap(MAX_N * 2 + 1);
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			int res = 0;
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			midHeap.offer(Integer.parseInt(input[1]));
			for (int i = 0; i < N; i++) {
				input = br.readLine().split(" ");
				midHeap.offer(Integer.parseInt(input[0]));
				midHeap.offer(Integer.parseInt(input[1]));
				System.out.println();
				print(midHeap.maxHeap.heap, 10);
				print(midHeap.minHeap.heap, 10);
				res = res + (midHeap.peek()) % MOD;
				print(midHeap.maxHeap.heap, 10);
				print(midHeap.minHeap.heap, 10);
			}
			
			sb.append(res).append("\n");
			midHeap.init();
		}
		br.close();
		System.out.println(sb);
	}
	
	static void print(int[] arr, int N) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(arr[i]).append(" ");
		}
		System.out.println(sb);
	}
	
	static class MidHeap {
		Heap maxHeap, minHeap;
		public MidHeap(int size) {
			this.maxHeap = new MaxHeap(size / 2 + 5);
			this.minHeap = new MinHeap(size / 2 + 5);
		}
		public void init() {
			this.maxHeap.init();
			this.minHeap.init();
		}
		public void offer(int val) {
			if(val > this.minHeap.peek()) {
				this.minHeap.offer(val);
			} else {
				this.maxHeap.offer(val);
			} 
		}
		public int peek() {
			if(this.minHeap.size - this.maxHeap.size > 1) {
				this.maxHeap.offer(this.minHeap.poll());
			} else if(this.minHeap.size - this.maxHeap.size < 1) {
				this.minHeap.offer(this.maxHeap.poll());
			}
			return this.minHeap.peek();
		}
	}
	
	static class MinHeap extends Heap {

		public MinHeap(int size) {
			super(size);
		}

		@Override
		public boolean compare(int a, int b) {
			return a < b;
		}
		
	}
	
	static class MaxHeap extends Heap {

		public MaxHeap(int size) {
			super(size);
		}

		@Override
		public boolean compare(int a, int b) {
			return a > b;
		}
		
	}
	
	static abstract class Heap {
		int[] heap;
		int size, temp;
		public Heap(int size) {
			this.heap = new int[size + 1];
		}
		public void init() {
			this.size = 0;
		}
		public int size() {
			return this.size;
		}
		public boolean isEmpty() {
			return this.size == 0;
		}
		public void offer(int val) {
			this.heap[++size] = val;
			this.bottomUpSort();
		}
		public int peek() {
			if(this.isEmpty()) return -1;
			return this.heap[1];
		}
		public int poll() {
			if(this.isEmpty()) return -1;
			int res = this.heap[1];
			this.heap[1] = this.heap[size--];
			this.topDownSort();
			return res;
		}
		private void topDownSort() {
			int idx = 1, child, next = 1;
			while(this.getLeftChild(idx) <= this.size) {
				child = this.getLeftChild(idx);
				if(this.compare(this.heap[child], this.heap[idx])) {
					next = child;
				}
				child = this.getRightChild(idx);
				if(child <= this.size && this.compare(this.heap[child], this.heap[next])) {
					next = child;
				}
				if(idx == next) return;
				this.swap(next, idx);
				idx = next;
			}
		}
		private void bottomUpSort() {
			int idx = this.size, parent;
			while(idx > 1) {
				parent = this.getParent(idx);
				if(this.compare(this.heap[idx], this.heap[parent])) {
					this.swap(idx, parent);
					idx = parent;
				} else return;
			}
		}
		private void swap(int a, int b) {
			this.temp = this.heap[a];
			this.heap[a] = this.heap[b];
			this.heap[b] = this.temp;
		}
		private int getParent(int idx) {
			return idx / 2;
		}
		private int getLeftChild(int idx) {
			return idx * 2;
		}
		private int getRightChild(int idx) {
			return idx * 2 + 1;
		}
		public abstract boolean compare(int a, int b);
	}
}
