package programmers.lv2.ok.두_큐_합_같게_만들기;


public class Solution2_fin {
    public int solution(int[] queue1, int[] queue2) {
    	int len1 = queue1.length, len2 = queue2.length;
    	int p = 0, len = len1 + len2;
    	long sum1 = 0, sum2 = 0;;
    	int[] queue = new int[len];
    	for (int num : queue1) {
			sum1 += num;
			queue[p++] = num;
		}
    	for (int num : queue2) {
    		sum2 += num;
    		queue[p++] = num;
    	}
    	if(sum1 == sum2) return 0;
    	long half = sum1 + sum2;
    	if(half % 2 == 1) return -1;
    	half /= 2;
    	
    	int p1 = 0, p2 = len1;
    	while(true) {
    		while(sum1 > half) {
    			if(p1 == len) return -1;
    			sum1 -= queue[p1++];
    		} 
    		while(sum1 < half) {
    			if(p1 == len) return -1;
    			sum1 += queue[p2++];
    		} 
    		if(sum1 == half) {
    			return p1 + p2 - len1;
    		}
    	}
    }
}
