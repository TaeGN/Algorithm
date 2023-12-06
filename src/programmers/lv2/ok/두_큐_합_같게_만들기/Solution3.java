package programmers.lv2.ok.두_큐_합_같게_만들기;


public class Solution3 {
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
    	while(p1 != len || p2 != len) {
    		if(sum1 > half) {
    			sum1 -= queue[p1++];
    		} else if(sum1 < half) {
    			sum1 += queue[p2++];
    		} else {
    			return p1 + p2 - len1;
    		}
    	}
    	return -1;
    }
}
