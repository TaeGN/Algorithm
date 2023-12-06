package programmers.lv3.no.셔틀버스;

import java.util.PriorityQueue;

public class Solution1 {
    public String solution(int n, int t, int m, String[] timetable) {
    	int len = timetable.length;
    	PriorityQueue<Integer> pq = new PriorityQueue<>(len);
    	for(String s : timetable) {
    		pq.offer(getTime(s));
    	}
    	
    	int curTime = 9 * 60 - t, cnt = 0, prev = 0, result = 0;
    	a:while(!pq.isEmpty() && cnt < m) {
    		cnt++;
    		int i = 0;
    		curTime += t;
    		while(pq.peek() <= curTime) {
    			prev = pq.poll();
    			if(++i == m) continue a;
    		}
    		if(prev != pq.peek()) result = pq.peek() - 1;
    	}
        return conversion(result);
    }

	private String conversion(int result) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(result / 60)).append(String.valueOf(result % 60));
		if(sb.length() == 0) sb.setCharAt(0, '0');
		return sb.toString();
	}

	private int getTime(String s) {
		return Integer.valueOf(s.substring(0, 2)) * 60 + Integer.valueOf(s.substring(3, 5));
	}
    
}
