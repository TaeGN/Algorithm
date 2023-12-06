package programmers.lv2.ok.요격_시스템;

import java.util.Arrays;

public class Solution1 {
	class Solution {
	    public int solution(int[][] targets) {
	    	Arrays.sort(targets, (o1, o2) -> {
	    		if(o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
	    		return Integer.compare(o1[1], o2[1]);
	    	});
	    	
	    	int prevEnd = 0, answer = 0;
	    	for (int i = 0, end = targets.length; i < end; i++) {
				if(targets[i][0] < prevEnd) {
					prevEnd = Math.min(prevEnd, targets[i][1]);
				} else {
					prevEnd = targets[i][1];
					answer++;
				}
			}
	        return answer;
	    }
	}
}
