package programmers.lv3.no.셔틀버스;

import java.util.PriorityQueue;

public class Solution3 {
	public static void main(String[] args) {
		System.out.println(solution(10, 25, 1,new String[] { "09:00", "09:10" ,"09:20" ,"09:30" ,"09:40" ,"09:50",
				"10:00", "10:10" ,"10:20" ,"10:30" ,"10:40" ,"10:50"}));
		System.out.println(solution(2,10,2,new String[] {"09:10", "09:09", "08:00"}));
		System.out.println(solution(1,1,5,new String[] {"08:00", "08:01", "08:02", "08:03"}));
	}
    public static String solution(int n, int t, int m, String[] timetable) {
    	int len = timetable.length;
    	
    	PriorityQueue<Integer> pq = new PriorityQueue<>(len);
    	for(String s : timetable) {
    		pq.offer(getTime(s));
    	}
    	
    	int curTime = 9 * 60 - t, cnt = 0, prev = 0, result = curTime + t * n;
    	while(!pq.isEmpty() && cnt < n) {
    		cnt++;
    		int i = 0;
    		curTime += t;
    		while(pq.peek() <= curTime) {
    			prev = pq.poll();
    			if(++i < m) {
    				if(pq.isEmpty()) {
        				result = curTime + t * (n - cnt);
        				break;
    				}
    			} else {
    				result = prev - 1;
    				pq.offer(prev);
    				break;
    			}
    		}
    	}
        return conversion(result);
    }

	private static String conversion(int result) {
		StringBuilder sb = new StringBuilder();
		String s = String.valueOf(result / 60);
		if(s.length() == 1) sb.append(0);
		sb.append(s).append(":");
		s = String.valueOf(result % 60);
		if(s.length() == 1) sb.append(0);
		sb.append(s);
		
		return sb.toString();
	}

	private static int getTime(String s) {
		return Integer.valueOf(s.substring(0, 2)) * 60 + Integer.valueOf(s.substring(3, 5));
	}
    
}
