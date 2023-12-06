package programmers.lv3.ok.입국심사;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution3 {
	public static void main(String[] args) {
		System.out.println(solution(6, new int[] {7, 10}));
	}
	
    public static long solution(int n, int[] times) {
    	long cnt, curTime = 0, minTime = 0, maxTime = (long) Math.pow(10, 18);
    	
    	while(minTime < maxTime) {
    		curTime = (minTime + maxTime) / 2;
    		cnt = 0;
    		for(int time : times) {
    			cnt += curTime / time;
    		}
    		if(cnt == n) break;
    		if(cnt > n) maxTime = curTime - 1;
    		else minTime = curTime + 1;
    	}
    	
    	long min = Long.MAX_VALUE;
		for(int time : times) {
			min = Math.min(min, curTime - (curTime / time) * time);
		}
    	
        return curTime - min;
    }
    
}
