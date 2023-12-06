package programmers.lv3.no.셔틀버스;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2 {
    public String solution(int n, int t, int m, String[] timetable) {
    	int len = timetable.length;
    	PriorityQueue<String> pq = new PriorityQueue<>(len, (o1, o2) -> {
    		int n1 = Integer.valueOf(o1.substring(0, 2));
    		int n2 = Integer.valueOf(o2.substring(0, 2));
    		if(n1 != n2) return Integer.compare(n1, n2);
    		return Integer.compare(Integer.valueOf(o1.substring(3, 5)), Integer.valueOf(o2.substring(3, 5)));
    	});
    	for(String s : timetable) {
    		pq.offer(s);
    	}
    	
    	int curTime = 9 * 60, cnt = 0, prev = 0, result = 0;
    	a:while(!pq.isEmpty() && cnt < m) {
    		cnt++;
    		int i = 0;
    		while(pq.peek() <= curTime)) {
    			prev = pq.poll();
    			if(++i == m) continue a;
    		}
    		if(prev != pq.peek()) result = pq.peek() - 1;
    	}
        return answer;
    }
    
	private int getTime(String s) {
		return Integer.valueOf(s.substring(0, 2)) * 60 + Integer.valueOf(s.substring(3, 5));
	}
    
}
