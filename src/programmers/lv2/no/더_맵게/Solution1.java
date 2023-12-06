package programmers.lv2.no.더_맵게;

import java.util.PriorityQueue;

public class Solution1 {
	public static void main(String[] args) {
		int[] scoville = {1, 2, 3, 9, 10, 12};
		int K = 7;
		System.out.println(Solution(scoville, K));
	}

	private static int Solution(int[] scoville, int K) {
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		for(int num : scoville) {
			heap.offer(num);
		}
		
		int cnt = 0;
		int len = scoville.length;
		while(heap.peek() < K) {
			if(++cnt == len) return -1;
			heap.offer(heap.poll() + heap.poll() * 2);
		}
		
		return cnt;
	}
	
}

//while(heap.peek() < K) {
//	if(++cnt == len) return -1;
//	min = heap.poll();
//	heap.offer(min + heap.poll() * 2);
//}
