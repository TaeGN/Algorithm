package programmers.lv2.no.디펜스_게임;

import java.util.PriorityQueue;

public class Solution3 {
    public int solution(int n, int k, int[] enemy) {
    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	int len = enemy.length;
    	for (int i = 0; i < len; i++) {
			pq.offer(enemy[i]);
			if(i >= k) {
				n -= pq.poll();
				if(n < 0) return i;
			}
		}
    	
        return len;
    }
}
