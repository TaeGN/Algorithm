package programmers.lv3.ok.입국심사;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution1 {
	public static void main(String[] args) {
		System.out.println(solution(6, new int[] {7, 10}));
	}
    public static long solution(int n, int[] times) {
    	int len = times.length;
    	PriorityQueue<Time> pq = new PriorityQueue<>(len);
    	long time = 0;
    	Arrays.sort(times);
    	int m = n;
    	while((m / len) != 0) {
    		time += (m / len) * times[0];
    		m = n;
    		for(int i = 0; i < len; i++) {
    			m -= (int) (time / times[i]);
    		}
    	}
    	
    	if(m == 0) return time;
    	
    	for(int i = 0; i < len; i++) {
    		int cnt = (int) (time / times[i]);
    		pq.offer(new Time(i, times[i] * ++cnt));
    	}
    	
    	Time cur;
    	for(int i = 0; i < m - 1; i++) {
    		cur = pq.poll();
    		cur.time += times[cur.idx];
    		pq.offer(cur);
    	}
    	
        return pq.peek().time;
    }
    
    static class Time implements Comparable<Time> {
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

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Time [idx=").append(idx).append(", time=").append(time).append("]");
			return builder.toString();
		}
		
		
    }
}
