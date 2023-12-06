package programmers.lv2.ok.숫자_변환하기;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution3 {
	
    public int solution(int x, int y, int n) {
    	Queue<Integer> queue = new ArrayDeque<>();
    	Set<Integer> visited = new HashSet<>();
    	queue.offer(x);
    	int cur, nx, cnt = 0;
    	while(!queue.isEmpty()) {
    		cnt++;
    		for(int i = 0, size = queue.size(); i < size; i++) {
    			cur = queue.poll();
    			
    			nx = cur + n;
    			if(nx == y) return cnt;
    			if(nx < y && visited.add(nx)) queue.offer(nx);
    			
    			nx = cur * 2;
    			if(nx == y) return cnt;
    			if(nx < y && visited.add(nx)) queue.offer(nx);
    			
    			nx = cur * 3;
    			if(nx == y) return cnt;
    			if(nx < y && visited.add(nx)) queue.offer(nx);
    		}
    	}
        return -1;
    }
}
