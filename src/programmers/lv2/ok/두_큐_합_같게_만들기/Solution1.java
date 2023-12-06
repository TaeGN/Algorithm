package programmers.lv2.ok.두_큐_합_같게_만들기;


public class Solution1 {
    public int solution(int[] queue1, int[] queue2) {
    	int len1 = queue1.length, len2 = queue2.length;
    	int p1 = 0, p2 = 0;
    	long sum1 = 0, sum2 = 0;;
    	for (int num : queue1) {
			sum1 += num;
		}
    	for (int num : queue2) {
    		sum2 += num;
    	}
    	if(sum1 == sum2) return 0;
    	long half = sum1 + sum2;
    	if(half % 2 == 1) return -1;
    	half /= 2;
    	while(p1 != len1 || p2 != len2) {
    		if(sum1 > half) {
    			sum1 -= queue1[p1++];
    		} else if(sum1 < half) {
    			sum1 += queue2[p2++];
    		} else {
    			return p1 + p2;
    		}
    	}
    	return -1;
    }
}
