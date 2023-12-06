package programmers.lv3.no.연속_펄스_부분_수열의_합;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution1 {
	public static void main(String[] args) {
		int[] sequence = {2, 3, -6, 1, 3, -1, 2, 4};
		System.out.println(solution(sequence));
	}
	
    public static long solution(int[] sequence) {
    	int len = sequence.length;
    	PriorityQueue<Long> max = new PriorityQueue<>(len, (o1, o2) -> Long.compare(o2, o1));
    	int idx = 0;
    	long sum = 0;
    	long[] sumArr = new long[len + 1];
    	for(int num : sequence) {
    		if(++idx % 2 == 1) {
    			sum += num;
    		} else {
    			sum -= num;
    		}
    		sumArr[idx] = sum;
    	}
    	
    	long cur;
    	for(int i = 0; i < len - 1; i++) {
    		cur = sumArr[i];
    		if(i % 2 == 0) {
    			for(int j = i + 1; j < len; j++) {
    				max.offer(sumArr[j] - cur);
    			}
    		} else {
    			for(int j = i + 1; j < len; j++) {
    				max.offer(cur - sumArr[j]);
    			}
    		}
    	}
        return max.poll();
    }
}
