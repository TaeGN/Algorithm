package programmers.lv1.ok.체육복;

import java.util.Arrays;

public class Solution1 {
    public int solution(int n, int[] lost, int[] reserve) {
    	int[] isLost = new int[n + 2];
    	for(int idx : lost) {
    		isLost[idx] = 1;
    	}
    	
    	for(int idx : reserve) {
    		isLost[idx] *= -1;
    	}
    	
    	Arrays.sort(reserve);
    	for(int idx : reserve) {
    		if(isLost[idx] == -1) continue;
    		if(isLost[idx - 1] == 1) isLost[idx - 1] = 0;
    		else if(isLost[idx + 1] == 1) isLost[idx + 1] = 0;
    	}
    	int result = -2;
    	for(int num : isLost) {
    		if(num != 1) result++;
    	}
        return result;
    }
}
