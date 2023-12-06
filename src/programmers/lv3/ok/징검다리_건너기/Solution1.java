package programmers.lv3.ok.징검다리_건너기;

import java.util.PriorityQueue;

public class Solution1 {
    public int solution(int[] stones, int k) {
    	PriorityQueue<Point> pq = new PriorityQueue<>(k);
    	for(int i = 0; i < k; i++) {
    		pq.offer(new Point(i, stones[i]));
    	}
    	
    	int min = pq.peek().num;
    	for(int i = 0, len = stones.length - k; i < len; i++) {
    		pq.offer(new Point(i + k, stones[i + k]));
    		while(pq.peek().idx <= i) pq.poll();
    		min = Math.min(min, pq.peek().num);
    	}
    	
        return min;
    }
    
    class Point implements Comparable<Point>{
    	int idx, num;

		public Point(int idx, int num) {
			super();
			this.idx = idx;
			this.num = num;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(o.num, this.num);
		}
    }
}
