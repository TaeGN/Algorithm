package programmers.lv3.ok.입국심사;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution2 {
    public long solution(int n, int[] times) {
    	int len = times.length;
    	PriorityQueue<Time> pq = new PriorityQueue<>(len);
    	Arrays.sort(times);
    	long time = (n / len) * times[0];
    	int cnt;
    	for(int i = 0; i < len; i++) {
    		cnt = (int) (time / times[i]);
    		n -= cnt;
    		pq.offer(new Time(i, times[i] * ++cnt));
    	}
    	
    	Time cur;
    	for(int i = 0; i < n - 1; i++) {
    		cur = pq.poll();
    		cur.time += times[cur.idx];
    		pq.offer(cur);
    	}
    	
        return pq.peek().time;
    }
    
    class Time implements Comparable<Time> {
    	int idx;
    	long time;

		public Time(int idx, long time) {
			super();
			this.idx = idx;
			this.time = time;
		}

		@Override
		public int compareTo(Time o) {
			return Long.compare(this.time, o.time);
		}
    }
}
