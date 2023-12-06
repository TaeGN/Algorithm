package boj.ok;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main_가운데를말해요 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		MidHeap midHeap = new MidHeap(N);
		for (int i = 0; i < N; i++) {
			midHeap.offer(Integer.parseInt(br.readLine()));
			sb.append(midHeap.peek()).append("\n");
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static class MidHeap {
		PriorityQueue<Integer> bottom, top;
		public MidHeap(int size) {
			this.bottom = new PriorityQueue<>(size / 2 + 5, Collections.reverseOrder());
			this.top = new PriorityQueue<>(size / 2 + 5);
		}
		public void init() {
			this.bottom.clear();
			this.top.clear();
		}
		public void offer(int val) {
			if(!this.top.isEmpty() && this.top.peek() <= val) {
				this.top.offer(val);
			} else {
				this.bottom.offer(val);
			}
		}
		public int peek() {
			while(this.bottom.size() - this.top.size() > 1) {
				this.top.offer(this.bottom.poll());
			}
			while(this.top.size() - this.bottom.size() > 0) {
				this.bottom.offer(this.top.poll());
			}
			return this.bottom.peek();
		}
	}
}
